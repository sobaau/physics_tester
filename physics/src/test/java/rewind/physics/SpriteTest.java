package rewind.physics;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import rewind.physics.jfx.Sprite;

import static org.junit.Assert.assertEquals;


public class SpriteTest {

    private PVector spawn = new PVector(1, 1);
    private PVector gravity = new PVector(2, 1);

    private Sprite sprite;

    /**
     * Spawn a sprite before each test.
     */
    @Before
    public void spawnSprite() {
        sprite = new Sprite(spawn, Color.color(Math.random(), Math.random(), Math.random()), 1);
    }

    /**
     * Test teh Velocity, Acceleration and location of the sprite.
     */
    @Test
    public void spriteVectorTest() {
        assertEquals(spawn, sprite.getLocation());
        assertEquals(0, sprite.getVelocity().x, 0);
        assertEquals(0, sprite.getVelocity().y, 0);
        assertEquals(0, sprite.getAcceleration().x, 0);
        assertEquals(0, sprite.getAcceleration().y, 0);

    }

    /**
     * Test update for sprite.
     */
    @Test
    public void testUpdate() {
        sprite.applyForce(gravity);
        sprite.update();
        assertEquals(3.0, sprite.getLocation().x, 0.001);
        assertEquals(2.0, sprite.getLocation().y, 0.001);
    }

    /**
     * Test the mass to see if its updating and returning.
     */
    @Test
    public void testMass() {
        assertEquals(1, sprite.getMass(), 0);
        sprite.setMass(2);
        assertEquals(2, sprite.getMass(), 0);
    }

    @Test
    public void applyFriction() {
        PVector friction = new PVector(30, 31);
        friction = sprite.applyFriction(friction, 0.01);
        assertEquals(-0.0069542145, friction.x, 0.0001);
        assertEquals(-0.007186021, friction.y, 0.0001);
    }

    @Test
    public void applyDrag() {
        PVector drag = sprite.applyDrag(new PVector(0, 3), 0.1);
        assertEquals(0, drag.x, 0.0001);
        assertEquals(-0.90000004, drag.y, 0.0001);

    }

    @Test
    public void attraction() {
        PVector att = sprite.attraction(new PVector(2, 1), new PVector(3, 4), 1, 2);
        assertEquals(-0.010119289, att.x, 0.0001);
        assertEquals(-0.030357866, att.y, 0.0001);

    }
}
