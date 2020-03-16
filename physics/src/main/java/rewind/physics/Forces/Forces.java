/*******************************************
 *
 * Forces class that will handle interaction
 * with the player or enemies. For example;
 * water would slow player/enemy movement,
 * wind would push player/enemy backwards.
 * Separate from gravity.
 *
 *******************************************/

package rewind.physics.Forces;

import rewind.physics.PVector;

import static rewind.physics.Util.constrain;

public interface Forces {


    /**
     * Get the velocity of an object.
     *
     * @return The velocity of the object.
     */

    PVector getVelocity();

    /**
     * Get the mass of an object.
     *
     * @return The mass of the object.
     */
    double getMass();

    /**
     * Set the mass of an object.
     *
     * @param m the mass to set.
     */
    void setMass(double m);

    /**
     * Set the velocity of the object.
     *
     * @param x x-coordinate as a double.
     * @param y y-coordinate as a double.
     */
    void setVelocity(double x, double y);

    /**
     * Set the Acceleration of the object.
     *
     * @param x x-coordinate as a double.
     * @param y y-coordinate as a double.
     */
    void setAcceleration(double x, double y);

    /**
     * Get the acceleration of the object.
     *
     * @return The acceleration of the object as a PVector.
     */
    PVector getAcceleration();

    /**
     * Applies a force.
     *
     * @param force the force applied as a PVector
     */
    void applyForce(PVector force);

    /**
     * Apply friction to the provided velocity of an object.
     *
     * @param v the velocity to apply the friction to
     * @param f the friction to apply
     * @return a new PVector to be applied to the object.
     */
    default PVector applyFriction(PVector v, double f) {
        PVector friction = new PVector(v.x, v.y);
        friction.mult(-1);
        friction.normalise();
        friction.mult(f);
        return friction;
    }

    /**
     * Apply drag to the provided velocity .
     *
     * @param v the velocity to apply drag to.
     * @param d the drag to apply.
     * @return a new PVector with drag applied.
     */
    default PVector applyDrag(PVector v, double d) {
        PVector vel = new PVector(v.x, v.y);
        double speed = vel.mag();
        double dragMag = d * speed * speed;
        vel.mult(-1);
        vel.normalise();
        vel.mult(dragMag);
        return vel;
    }

    /**
     * Creates a PVector that can be used for attraction.
     *
     * @param loca  PVector of object a.
     * @param locb  PVector of object b.
     * @param mass1 Mass of object a.
     * @param mass2 Mass of object b.
     * @return A PVector for attraction.
     */
    default PVector attraction(PVector loca, PVector locb, double mass1, double mass2) {
        PVector attr = PVector.sub(loca, locb);
        double distance = attr.mag();
        distance = constrain(distance, 5.0, 25.0);
        attr.normalise();
        double G = 0.4;
        double m = (G * mass1 * mass2) / (distance * distance);
        attr.mult(m);
        return attr;
    }

}
