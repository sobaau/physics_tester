package rewind.physics;

import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;

public class Quadtree {

    private Rectangle bounds;
    private Quadtree[] nodes;
    private List<Rectangle2D> objects;

    private final int level;
    private final int NE = 0;
    private final int NW = 1;
    private final int SE = 2;
    private final int SW = 3;

    private boolean subdivided;

    /**
     * Initialises the bounds and level of the tree, and initialises the nodes and object array
     * @param bounds    the size of the boundary for initial quadtree and then the nodes
     * @param level     the level of the tree, starting with 0 for the root
     */
    public Quadtree(Rectangle bounds, int level) {
        this.bounds = bounds;
        this.level = level;
        nodes = new Quadtree[4];
        objects = new ArrayList<>();
        this.subdivided = false;
    }

    /**
     * Clears the quadtree
     */
    public void clear() {
        objects.clear();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
        this.subdivided = false;
    }

    /**
     * Subdivides the initial boundary into four nodes, and those nodes into four nodes and so on
     */
    private void subdivide() {
        double x = bounds.getX();
        double y = bounds.getY();
        double w = bounds.getWidth() / 2;
        double h = bounds.getHeight() / 2;

        Quadtree northeast = new Quadtree(new Rectangle(x + w, y, w, h), level+1);
        nodes[NE] = northeast;
        Quadtree northwest = new Quadtree(new Rectangle(x, y, w, h), level+1);
        nodes[NW] = northwest;
        Quadtree southeast = new Quadtree(new Rectangle(x + w, y + h, w, h), level+1);
        nodes[SE] = southeast;
        Quadtree southwest = new Quadtree(new Rectangle(x, y + h, w, h), level+1);
        nodes[SW] = southwest;
    }

    /**
     * Finds the index of the object passed in and assigns it to a zone, or multiple zones if overlapping
     * the bounds of more than one node
     * @param r     The object being passed in which is only currently set for rectangles
     * @return      Returns the ArrayList containing the zone(s)
     */
    List<Integer> getIndex(Rectangle2D r) {
        List<Integer> indexes = new ArrayList<>();

        double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

        boolean isNorthern = (r.getMinY() + (r.getHeight() / 2) <= horizontalMidpoint);
        boolean isSouthern = (r.getMinY() + (r.getHeight() / 2) >= horizontalMidpoint);
        boolean isBoth = (r.getMinY() <= horizontalMidpoint && r.getMinY() + r.getHeight() >= horizontalMidpoint);

        if(isBoth) {
            isNorthern = false;
            isSouthern = false;
        }

        if (r.getMinX() <= verticalMidpoint && (r.getMinX() + r.getWidth() >= verticalMidpoint)) {
            if (isNorthern) {
                indexes.add(NW);
                indexes.add(NE);
            }
            else if (isSouthern) {
                indexes.add(SE);
                indexes.add(SW);
            }
            else if (isBoth) {
                indexes.add(NW);
                indexes.add(NE);
                indexes.add(SW);
                indexes.add(SE);
            }
        }
        else if (r.getMinX() + (r.getWidth() / 2) <= verticalMidpoint) {
            if (isNorthern) {
                indexes.add(NW);
            }
            else if (isSouthern) {
                indexes.add(SW);
            }
            else if (isBoth) {
                indexes.add(NW);
                indexes.add(SW);
            }
        }
        else if (r.getMinX() + (r.getWidth() / 2) >= verticalMidpoint) {
            if (isNorthern) {
                indexes.add(NE);
            }
            else if (isSouthern) {
                indexes.add(SE);
            }
            else if (isBoth) {
                indexes.add(NE);
                indexes.add(SE);
            }
        }
        this.subdivided = true;
        return indexes;
    }

    /**
     * Inserts the object into the zone it belongs to
     * @param r     The object being passed in to be placed in the quadtree
     */
    public void insert(Rectangle2D r) {
        final int MAX_LEVELS = 5;
        final int MAX_OBJECTS = 1;

        if (nodes[NE] != null && nodes[NW] != null && nodes[SE] != null && nodes[SW] != null) {
            List<Integer> index = getIndex(r);
            for (int i = 0; i < index.size(); i++) {
                nodes[index.get(i)].insert(r);
            }
            return;
        }

        objects.add(r);

        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            subdivide();

            for (int i = 0; i < objects.size(); i++) {
                r = objects.get(i);
                List<Integer> indexes = getIndex(r);
                for (int j = 0; j < indexes.size(); j++) {
                    nodes[indexes.get(j)].insert(r);
                    objects.remove(r);
                }
            }
        }
    }

    /**
     * Retrieves a list of possible collisions
     * @param returnObjects     returns the list of objects that are able to collide with the given object
     * @param r                 the object being passed in to check what it is near to collide with
     */
    public void retrieve(List<Rectangle2D> returnObjects, Rectangle2D r) {
        if (nodes[NE] != null && nodes[NW] != null && nodes[SE] != null && nodes[SW] != null) {
            List<Integer> index = getIndex(r);
            for (int i = 0; i < index.size(); ++i) {
                nodes[index.get(i)].retrieve(returnObjects, r);
            }
        }
        returnObjects.addAll(objects);
    }

    /**
     * Gets a list of nodes in the quadtree and returns
     * @param quadtree     a list of the tree and its leaves at the current frame
     */
    public void getQuadtree(ArrayList<Rectangle> quadtree) {
        quadtree.add(this.bounds);
        for (int i = 0; i < nodes.length; i++) {
            Quadtree node = nodes[i];
            if (node != null) {
                node.getQuadtree(quadtree);
            }
        }
    }

    /**
     * Used for troubleshooting purposes only to visualise the quadtree
     * @param gc                GraphicsContext object passed in to draw on the canvas
     * @param rendererOffset
     * @param quadtree          a list of the tree and its leaves at the current frame
     */
    public void draw(GraphicsContext gc, ArrayList<Rectangle> quadtree, PVector rendererOffset) {
        for (Rectangle r : quadtree) {
            gc.setStroke(Color.RED);
            gc.strokeRect(r.getX()-rendererOffset.x, r.getY()-rendererOffset.y, r.getWidth(), r.getHeight());
        }
    }

    Rectangle getBounds() { return this.bounds; }
    int getLevel() { return this.level; }
    boolean isSubdivided() { return this.subdivided; }
}
