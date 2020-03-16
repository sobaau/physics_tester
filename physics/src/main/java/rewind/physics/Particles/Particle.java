package rewind.physics.Particles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import rewind.physics.PVector;


public class Particle extends PVector {

    private double x;
    private double y;
    private double arcWidth;
    private double arcHeight;
    private double rectWidth;
    private double rectHeight;
    private double startAngle;
    private double arcExtent;
    private ArcType closure;
    private PVector velocity;
    private double radius;
    private double life = 0.8;
    private double decay;
    private Paint color;
    private BlendMode blendMode;

    /**
     * Oval shaped particle
     *
     * @param x - x coordinate location
     * @param y - y coordinate location
     * @param radius - size of the particle
     * @param velocity - movement of the particle
     * @param expireTime - how long the particle persists
     * @param color - colour of the particle
     * @param blendMode - defines how the particles are composited into the window
     */
    public Particle(double x, double y, double radius, PVector velocity,
                    double expireTime, Paint color, BlendMode blendMode) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocity = velocity;
        this.decay = 0.002 / expireTime; // adjusts how long particles last
        this.color = color;
        this.blendMode = blendMode;
    }

    /**
     * Arc shaped particle
     *
     * @param x - x coordinate location
     * @param y - y coordinate location
     * @param w - width of the arc
     * @param h - height of the arc
     * @param startAngle - starting angle in degrees
     * @param arcExtent - angular extent in degrees
     * @param closure - type of closure (Round, Chord or Open)
     * @param velocity - movement of the particle
     * @param expireTime - how long the particle persists
     * @param color - colour of the particle
     * @param blendMode - defines how the particles are composited into the window
     */
    public Particle(double x, double y, double w, double h, double startAngle,
                    double arcExtent, ArcType closure, PVector velocity,
                    double expireTime, Paint color, BlendMode blendMode) {
        this.x = x;
        this.y = y;
        this.arcWidth = w;
        this.arcHeight = h;
        this.startAngle = startAngle;
        this.arcExtent = arcExtent;
        this.closure = closure;
        this.velocity = velocity;
        this.decay = 0.002 / expireTime;
        this.color = color;
        this.blendMode = blendMode;
    }

    /**
     * Rectangle shaped particle
     *
     * @param x - x coordinate location
     * @param y - y coordinate location
     * @param w - width of the rectangle
     * @param h - height of the rectangle
     * @param velocity - movement of the particle
     * @param expireTime - how long the particle persists
     * @param color - colour of the particle
     * @param blendMode - defines how the particles are composited into the window
     */
    public Particle(double x, double y, double w, double h, PVector velocity,
                    double expireTime, Paint color, BlendMode blendMode) {
        this.x = x;
        this.y = y;
        this.rectWidth = w;
        this.rectHeight = h;
        this.velocity = velocity;
        this.decay = 0.002 / expireTime;
        this.color = color;
        this.blendMode = blendMode;
    }

    /**
     * Renders the particles to the canvas window
     *
     * @param gc - handles drawing the particles
     * @param particleType - type of particle
     * @param rotateAngle - rotation angle of the canvas in degrees
     */
    public void render(GraphicsContext gc, String particleType, double rotateAngle) {
        gc.setGlobalAlpha(life);
        gc.setGlobalBlendMode(blendMode);
        gc.setFill(color);
        switch (particleType) {
            case "Ovals":
                gc.fillOval(x, y, radius, radius);
                break;
            case "Arcs":
                gc.fillArc(x, y, arcWidth, arcHeight, startAngle, arcExtent, closure);
                break;
            case "Rectangles":
                gc.fillRect(x, y, rectWidth, rectHeight);
                break;
            case "Rain":
                double rotateRainX = 375; // canvas centre width
                double rotateRainY = 300; // canvas centre height
                gc.save();
                gc.translate(rotateRainX, rotateRainY);
                gc.rotate(rotateAngle); // replace with 0 for no rotation
                gc.translate(-rotateRainX, -rotateRainY);
                gc.fillRect(x, y, rectWidth, rectHeight);
                gc.restore();
                break;
            case "torchFire":
                gc.fillArc(x, y, arcWidth, arcHeight, startAngle, arcExtent, closure);
                break;
            case "Snow":
                gc.fillOval(x, y, radius, radius);
                break;
            case "Meteors":
                double rotateMeteorX = 825; // canvas centre width
                double rotateMeteorY = 650; // canvas centre height
                gc.save();
                gc.translate(rotateMeteorX, rotateMeteorY);
                gc.rotate(rotateAngle); // replace with 0 for no rotation
                gc.translate(-rotateMeteorX, -rotateMeteorY);
                gc.fillArc(x, y, arcWidth, arcHeight, startAngle, arcExtent, closure);
                gc.restore();
                break;
            case "cherryBlossom":
                gc.fillOval(x, y, radius, radius);
                break;
            default:
                gc.fillOval(x, y, radius, radius);
        }
    }

    /**
     * Updates the x,y position of the particle with PVector
     * Updates the life of the particle
     *
     * @param particleType - the type of particle
     */
    public void update(String particleType) {
        x += velocity.x;
        y += velocity.y;
        // Decreasing particle decay rate greatly increases density, persistence and lowers performance
        switch (particleType) {
            case "Ovals":
                life -= decay * 3;
                break;
            case "Arcs":
                life -= decay * 3;
                break;
            case "Rectangles":
                life -= decay * 3;
                break;
            case "Rain":
                life -= decay * 1;
                break;
            case "torchFire":
                life -= decay * 3;
                break;
            case "Snow":
                life -= decay * 3;
                break;
            case "Meteors":
                life -= decay * 2;
                break;
            case "cherryBlossom":
                life -= decay * 3;
                break;
            default:
                life -= decay * 3;
        }
    }

    /**
     * Applies force by adding x,y to the PVector
     *
     * @param force - PVector x,y to be added
     */
    public void applyForce(PVector force) {
        PVector f = new PVector();
        f.copy(force);
        velocity = velocity.add(f);
    }

    /**
     * Check if a particle is still active
     *
     * @return boolean true if particle still alive, otherwise false
     */
    public boolean isAlive() {
        return life > 0;
    }


    /**
     * Numerous getters mostly used for tests
     *
     * @return the specified value from the getter
     */
    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public double getArcWidth() { return this.arcWidth; }

    public double getArcHeight() { return this.arcHeight; }

    public double getStartAngle() { return this.startAngle; }

    public double getArcExtent() { return this.arcExtent; }

    public ArcType getClosure() { return this.closure; }

    public double getRectWidth() { return this.rectWidth; }

    public double getRectHeight() { return this.rectHeight; }

    public double getRadius() { return this.radius; }

    public double getDecay() { return this.decay; }

    public Paint getColor() { return this.color; }

    public BlendMode getBlendMode() { return this.blendMode; }

    public double getLife() { return this.life; }

    public PVector getPVector() { return this.velocity; }
}