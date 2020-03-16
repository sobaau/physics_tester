package rewind.physics;

import javafx.geometry.Rectangle2D;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class QuadtreeTest {

    @Test
    public void testConstructor(){
        Quadtree quadtree = new Quadtree(new Rectangle(800, 600), 0);
        assertEquals(800, quadtree.getBounds().getWidth(), 0);
        assertEquals(600, quadtree.getBounds().getHeight(), 0);
        assertEquals(0, quadtree.getLevel());
        assertTrue(!quadtree.isSubdivided());
    }

    @Test
    public void testClear() {
        ArrayList<Rectangle2D> returnObjects = new ArrayList<>();
        Quadtree quadtree = new Quadtree(new Rectangle(800, 600), 0);

        Rectangle2D e1 = new Rectangle2D(10,10,5,5);
        Rectangle2D r1 = new Rectangle2D(50, 50, 5, 5);
        Rectangle2D r2 = new Rectangle2D(450, 50, 5, 5);
        Rectangle2D r3 = new Rectangle2D(50, 350, 5, 5);
        Rectangle2D r4 = new Rectangle2D(450, 350, 5, 5);

        quadtree.insert(e1);
        quadtree.insert(r1);
        quadtree.insert(r2);
        quadtree.insert(r3);
        quadtree.insert(r4);

        quadtree.clear();
        quadtree.retrieve(returnObjects, e1);
        assertEquals(0, returnObjects.size());
    }

    @Test
    public void testSubdivide() {
        Quadtree quadtree = new Quadtree(new Rectangle(800, 600), 0);

        Rectangle2D r1 = new Rectangle2D(50, 50, 10, 10);
        Rectangle2D r2 = new Rectangle2D(450, 50, 10, 10);
        Rectangle2D r3 = new Rectangle2D(50, 350, 10, 10);
        Rectangle2D r4 = new Rectangle2D(450, 350, 10, 10);


        quadtree.insert(r1);
        quadtree.insert(r2);
        quadtree.insert(r3);
        quadtree.insert(r4);

        assertTrue(quadtree.isSubdivided());
        quadtree.clear();
        assertFalse(quadtree.isSubdivided());
    }

    @Test
    public void testSingleIndex() {
        Quadtree quadtree = new Quadtree(new Rectangle(800, 600), 0);

        Rectangle2D r1 = new Rectangle2D(50, 50, 10, 10);
        Rectangle2D r2 = new Rectangle2D(450, 50, 10, 10);
        Rectangle2D r3 = new Rectangle2D(50, 350, 10, 10);
        Rectangle2D r4 = new Rectangle2D(450, 350, 10, 10);

        quadtree.insert(r1);
        quadtree.insert(r2);
        quadtree.insert(r3);
        quadtree.insert(r4);

        assertTrue(quadtree.getIndex(r1).contains(1));
        assertTrue(quadtree.getIndex(r2).contains(0));
        assertTrue(quadtree.getIndex(r3).contains(3));
        assertTrue(quadtree.getIndex(r4).contains(2));
    }

    @Test
    public void testMultipleIndexes() {
        Quadtree quadtree = new Quadtree(new Rectangle(800, 600), 0);

        Rectangle2D r1 = new Rectangle2D(395, 50, 10, 10);
        Rectangle2D r2 = new Rectangle2D(395, 295, 10, 10);

        quadtree.insert(r1);

        assertTrue(quadtree.getIndex(r1).contains(1));
        assertTrue(quadtree.getIndex(r1).contains(0));
        assertFalse(quadtree.getIndex(r1).contains(3));
        assertFalse(quadtree.getIndex(r1).contains(2));

        assertTrue(quadtree.getIndex(r2).contains(1));
        assertTrue(quadtree.getIndex(r2).contains(0));
        assertTrue(quadtree.getIndex(r2).contains(3));
        assertTrue(quadtree.getIndex(r2).contains(2));
    }

    @Test
    public void testRetrieve() {
        ArrayList<Rectangle2D> returnObjects = new ArrayList<>();
        Quadtree quadtree = new Quadtree(new Rectangle(800, 600), 0);

        Rectangle2D r1 = new Rectangle2D(50, 50, 10, 10);
        Rectangle2D r2 = new Rectangle2D(100, 100, 10, 10);
        Rectangle2D r3 = new Rectangle2D(150, 150, 10, 10);
        Rectangle2D r4 = new Rectangle2D(50, 350, 10, 10);

        quadtree.insert(r1);
        quadtree.insert(r2);
        quadtree.insert(r3);
        quadtree.insert(r4);

        quadtree.retrieve(returnObjects, r1);

        assertTrue(returnObjects.contains(r2));
        assertTrue(returnObjects.contains(r3));
        assertFalse(returnObjects.contains(r4));
    }
}
