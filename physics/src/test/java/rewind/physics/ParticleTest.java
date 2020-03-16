package rewind.physics;

/********************************
 *
 * Testing the Particles class
 *
 *******************************/

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import org.junit.Test;
import rewind.physics.Particles.Particle;
import rewind.physics.Particles.ParticleCreation;
import rewind.physics.Particles.ParticleSpawn;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParticleTest {

    @Test
    public void OvalTest() {

        Particle oval = new Particle(1280, 720, 5, new PVector(Math.random() - 0.5, Math.random() - 1), 0.3, Color.rgb(230, 152, 19), BlendMode.ADD);

        // Now testing oval object state
        assertEquals(1280.0, oval.getX(), 0.001);
        assertEquals(720.0, oval.getY(), 0.001);
        assertEquals(5.0, oval.getRadius(), 0.001);
        assertEquals(0.006, oval.getDecay(), 0.001);
        assertEquals(Color.rgb(230, 152, 19), oval.getColor());
        assertEquals(BlendMode.ADD, oval.getBlendMode());
        assertEquals(0.8, oval.getLife(), 0.001);

        // Now entering oval object into update method
        oval.update("Ovals");
        // Get modified life
        assertEquals(0.78, oval.getLife(), 0.001);
        assertTrue(oval.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1;
        for (int i = 0; i < numParticles; i++) {
            particles.add(oval);
        }

        assertEquals(1, particles.size());
    }

    @Test
    public void ArcTest() {

        Particle arc = new Particle(1920, 1080, 11, 7.5, 87, 67, ArcType.ROUND, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.2, Color.rgb(100, 114, 223), BlendMode.ADD);

        // Now testing arc object state
        assertEquals(1920.0, arc.getX(), 0.001);
        assertEquals(1080.0, arc.getY(), 0.001);
        assertEquals(11.0, arc.getArcWidth(), 0.001);
        assertEquals(7.5, arc.getArcHeight(), 0.001);
        assertEquals(87, arc.getStartAngle(), 0.001);
        assertEquals(67, arc.getArcExtent(), 0.001);
        assertEquals(ArcType.ROUND, arc.getClosure());
        assertEquals(0.01, arc.getDecay(), 0.001);
        assertEquals(Color.rgb(100, 114, 223), arc.getColor());
        assertEquals(BlendMode.ADD, arc.getBlendMode());
        assertEquals(0.8, arc.getLife(), 0.001);

        // Now entering arc object into update method
        arc.update("Arcs");
        // Get modified life
        assertEquals(0.77, arc.getLife(), 0.001);
        assertTrue(arc.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1;
        for (int i = 0; i < numParticles; i++) {
            particles.add(arc);
        }

        assertEquals(1, particles.size());
    }

    @Test
    public void RectangleTest() {

        Particle rect = new Particle(564, 234, 23, 12, new PVector(Math.random() - 0.5, Math.random() * -0.5), 2, Color.rgb(132, 34, 26), BlendMode.ADD);

        // Now testing rectangle object state
        assertEquals(564.0, rect.getX(), 0.001);
        assertEquals(234.0, rect.getY(), 0.001);
        assertEquals(23.0, rect.getRectWidth(), 0.001);
        assertEquals(12.0, rect.getRectHeight(), 0.001);
        assertEquals(0.001, rect.getDecay(), 0.001);
        assertEquals(Color.rgb(132, 34, 26), rect.getColor());
        assertEquals(BlendMode.ADD, rect.getBlendMode());
        assertEquals(0.8, rect.getLife(), 0.001);

        // Now entering rectangle object into update method
        rect.update("Rectangles");
        // Get modified life
        assertEquals(0.797, rect.getLife(), 0.001);
        assertTrue(rect.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1;
        for (int i = 0; i < numParticles; i++) {
            particles.add(rect);
        }

        assertEquals(1, particles.size());
    }

    @Test
    public void RainTest() {
        Canvas canvas = new Canvas(42 * 30, 20 * 30);
        Particle rain = new Particle(canvas.getWidth(), canvas.getHeight(), 2, 25, new PVector(0, 10), 0.8, Color.rgb(75, 167, 244), BlendMode.ADD);

        // Now testing rectangle object state
        assertEquals(1260.0, rain.getX(), 0.001);
        assertEquals(600.0, rain.getY(), 0.001);
        assertEquals(2.0, rain.getRectWidth(), 0.001);
        assertEquals(25.0, rain.getRectHeight(), 0.001);
        assertEquals(0.0025, rain.getDecay(), 0.001);
        assertEquals(Color.rgb(75, 167, 244), rain.getColor());
        assertEquals(BlendMode.ADD, rain.getBlendMode());
        assertEquals(0.8, rain.getLife(), 0.001);

        // Now entering rectangle object into update method
        rain.update("Rain");
        // Get modified life
        assertEquals(0.797, rain.getLife(), 0.001);
        assertTrue(rain.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1;
        for (int i = 0; i < numParticles; i++) {
            particles.add(rain);
        }

        assertEquals(1, particles.size());
    }

    @Test
    public void TorchTest() {
        Canvas canvas = new Canvas(42 * 30, 20 * 30);
        Particle torch = new Particle(canvas.getWidth(), canvas.getHeight(), 13, 13, 90, 90, ArcType.ROUND, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.3, Color.rgb(89, 35, 13), BlendMode.ADD);

        // Now testing rectangle object state
        assertEquals(1260.0, torch.getX(), 0.001);
        assertEquals(600.0, torch.getY(), 0.001);
        assertEquals(13.0, torch.getArcWidth(), 0.001);
        assertEquals(13.0, torch.getArcHeight(), 0.001);
        assertEquals(90, torch.getStartAngle(), 0.001);
        assertEquals(90, torch.getArcExtent(), 0.001);
        assertEquals(ArcType.ROUND, torch.getClosure());
        assertEquals(0.0066, torch.getDecay(), 0.001);
        assertEquals(Color.rgb(89, 35, 13), torch.getColor());
        assertEquals(BlendMode.ADD, torch.getBlendMode());
        assertEquals(0.8, torch.getLife(), 0.001);

        // Now entering rectangle object into update method
        torch.update("Rectangles");
        // Get modified life
        assertEquals(0.780, torch.getLife(), 0.001);
        assertTrue(torch.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1;
        for (int i = 0; i < numParticles; i++) {
            particles.add(torch);
        }

        assertEquals(1, particles.size());
    }

    @Test
    public void SnowTest() {
        Canvas canvas = new Canvas(42 * 30, 20 * 30);
        Particle snow = new Particle(canvas.getWidth(), canvas.getHeight(), 3.5, new PVector(0, 2), 0.8, Color.rgb(255, 255, 255), BlendMode.ADD);

        // Now testing rectangle object state
        assertEquals(1260.0, snow.getX(), 0.001);
        assertEquals(600.0, snow.getY(), 0.001);
        assertEquals(3.5, snow.getRadius(), 0.001);
        assertEquals(0.0025, snow.getDecay(), 0.001);
        assertEquals(Color.rgb(255, 255, 255), snow.getColor());
        assertEquals(BlendMode.ADD, snow.getBlendMode());
        assertEquals(0.8, snow.getLife(), 0.001);

        // Now entering rectangle object into update method
        snow.update("Snow");
        // Get modified life
        assertEquals(0.7925, snow.getLife(), 0.001);
        assertTrue(snow.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1;
        for (int i = 0; i < numParticles; i++) {
            particles.add(snow);
        }

        assertEquals(1, particles.size());
    }

    @Test
    public void MeteorsTest() {
        Canvas canvas = new Canvas(42 * 30, 20 * 30);
        Particle meteor = new Particle(canvas.getWidth(), canvas.getHeight(), 10, 30, 180, 180, ArcType.ROUND, new PVector(0, 10), 0.3, Color.rgb(185, 14, 23), BlendMode.ADD);

        // Now testing rectangle object state
        assertEquals(1260.0, meteor.getX(), 0.001);
        assertEquals(600.0, meteor.getY(), 0.001);
        assertEquals(10.0, meteor.getArcWidth(), 0.001);
        assertEquals(30.0, meteor.getArcHeight(), 0.001);
        assertEquals(180, meteor.getStartAngle(), 0.001);
        assertEquals(180, meteor.getArcExtent(), 0.001);
        assertEquals(ArcType.ROUND, meteor.getClosure());
        assertEquals(0.0066, meteor.getDecay(), 0.001);
        assertEquals(Color.rgb(185, 14, 23), meteor.getColor());
        assertEquals(BlendMode.ADD, meteor.getBlendMode());
        assertEquals(0.8, meteor.getLife(), 0.001);

        // Now entering rectangle object into update method
        meteor.update("Meteors");
        // Get modified life
        assertEquals(0.7866, meteor.getLife(), 0.001);
        assertTrue(meteor.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 2;
        for (int i = 0; i < numParticles; i++) {
            particles.add(meteor);
        }

        assertEquals(2, particles.size());
    }

    @Test
    public void CherryBlossomTest() {
        Canvas canvas = new Canvas(42 * 30, 20 * 30);
        Particle cherryBlossom = new Particle(canvas.getWidth(), canvas.getHeight(), 5.5, new PVector(0, 1), 0.8, Color.rgb(255, 183, 197), BlendMode.ADD);

        // Now testing rectangle object state
        assertEquals(1260.0, cherryBlossom.getX(), 0.001);
        assertEquals(600.0, cherryBlossom.getY(), 0.001);
        assertEquals(5.5, cherryBlossom.getRadius(), 0.001);
        assertEquals(0.0025, cherryBlossom.getDecay(), 0.001);
        assertEquals(Color.rgb(255, 183, 197), cherryBlossom.getColor());
        assertEquals(BlendMode.ADD, cherryBlossom.getBlendMode());
        assertEquals(0.8, cherryBlossom.getLife(), 0.001);

        // Now entering rectangle object into update method
        cherryBlossom.update("cherryBlossom");
        // Get modified life
        assertEquals(0.7925, cherryBlossom.getLife(), 0.001);
        assertTrue(cherryBlossom.isAlive());

        // Testing array
        List<Particle> particles = new ArrayList<>();
        int numParticles = 3;
        for (int i = 0; i < numParticles; i++) {
            particles.add(cherryBlossom);
        }

        assertEquals(3, particles.size());
    }

    @Test
    public void ExtremeParticleTest() {
        Particle oval = new Particle(1280, 720, 5, new PVector(Math.random() - 0.5, Math.random() - 1), 0.3, Color.rgb(230, 152, 19), BlendMode.ADD);
        List<Particle> particles = new ArrayList<>();
        int numParticles = 1000;
        for (int i = 0; i < numParticles; i++) {
            particles.add(oval);
        }

        assertEquals(1000, particles.size());

        for (int i = 0; i < 499; i++) {
            particles.remove(oval);
        }

        assertEquals(501, particles.size());
    }

    @Test
    public void PVectorTest() {
        PVector PVector = new PVector(5.33, -0.54);
        assertEquals(5.33, PVector.x, 0.0001);
        assertEquals(-0.54, PVector.y, 0.0001);

    }
}
