package rewind.physics.jfx;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import rewind.physics.Forces.Forces;
import rewind.physics.PVector;

/**
 * Renders a Circle on screen at the given location.
 */
public class Sprite implements Forces {

    private PVector velocity = new PVector(0, 0);
    private PVector acceleration = new PVector(0, 0);
    private PVector location;
    private double mass;
    private Color defaultColour;

    /**
     * Create a sprite at the given location.
     *
     * @param loc   location to spawn.
     * @param color colour of the sprite.
     * @param m     mass of the sprite.
     */
    public Sprite(PVector loc, Color color, double m) {
        this.mass = m;
        this.defaultColour = color;
        this.location = loc;
    }

    /**
     * Applies a force to the sprite.
     *
     * @param force the force applied as a PVector
     */
    public void applyForce(PVector force) {
        PVector f = new PVector();
        f.copy(force);
        f.div(mass);
        acceleration = acceleration.add(f);
    }

    /**
     * Check the location of the sprite and reverse its velocity if its going to go off screen.
     */
    void checkEdges() {
        if (location.x > 730) {
            location.x = 730;
            velocity.x *= -1;
        } else if (location.x < 0) {
            velocity.x *= -1;
            location.x = 0;
        }
        if (location.y > 550) {
            velocity.y *= -1;
            location.y = 550;
        }
    }

    /**
     * Update the sprites location.
     */
    public void update() {
        velocity = velocity.add(acceleration);
        location = location.add(velocity);
        acceleration.mult(0);
    }

    /**
     * Get the acceleration of the sprite.
     *
     * @return the acceleration of the sprite as PVector.
     */
    public PVector getAcceleration() {
        return acceleration;
    }

    /**
     * Get the velocity of the sprite.
     *
     * @return the velocity of the sprite as PVector.
     */
    public PVector getVelocity() {
        return velocity;
    }

    /**
     * Get the mass of the sprite.
     *
     * @return the mass of the sprite.
     */
    public double getMass() {
        return mass;
    }

    /**
     * Set the mass of the sprite.
     *
     * @param m the value as a double to set the mass to.
     */
    public void setMass(double m) {
        mass = m;
    }

    /**
     * Renders the sprite at the given location and sets its colour to the colour provided in the constructor.
     *
     * @param gc GraphicsContext.
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(this.defaultColour);
        gc.fillOval(location.x, location.y, 40, 40);
    }

    /**
     * Get the bounds of the sprite.
     * @return the bounds as a Rectangle2D.
     */
    public Rectangle2D bound() {
        return new Rectangle2D(location.x, location.y, 40, 40);
    }

    /**
     * Get the location of the sprite.
     *
     * @return the location of the sprite
     */
    public PVector getLocation() {
        return location;
    }

    /**
     * Set the velocity of the sprite
     *
     * @param x x-coordinate as a double.
     * @param y y-coordinate as a double.
     */
    public void setVelocity(double x, double y) {
        velocity = new PVector(x, y);
    }

    /**
     * Set the Acceleration of the sprite.
     *
     * @param x x-coordinate as a double.
     * @param y y-coordinate as a double.
     */
    public void setAcceleration(double x, double y) {
        acceleration = new PVector(x, y);
    }

}
