package rewind.physics.Particles;

import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import rewind.physics.PVector;

import java.util.ArrayList;
import java.util.List;

public class ParticleCreation extends ParticleSpawn {

    /**
     * Creates particle object(s) to be added to an array list
     *
     * @param x - x coordinate for drawing particle
     * @param y - y coordinate for drawing particle
     * @param particleType - the type of particle to be created
     * @return - particle array list
     */
    public List<Particle> spawn(double x, double y, String particleType) {

        List<Particle> particles = new ArrayList<>();
        int numParticles = 1; // increases particle density but degrades performance (rec. 1-3)

        for (int i = 0; i < numParticles; i++) {
            switch (particleType) {
                case "Ovals":
                    /* Creates oval particle object */
                    Particle oval = new Particle(x, y, 3.5, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.5, Color.rgb(200, 14, 23), BlendMode.ADD);
                    particles.add(oval);
                    break;
                case "Arcs":
                    /* Creates arc particle object */
                    Particle arc = new Particle(x, y, 10, 10, 90, 90, ArcType.ROUND, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.2, Color.rgb(200, 14, 23), BlendMode.ADD);
                    particles.add(arc);
                    break;
                case "Rectangles":
                    /* Creates rectangle particle object */
                    Particle rect = new Particle(x, y, 1, 5, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.5, Color.rgb(200, 14, 23), BlendMode.ADD);
                    particles.add(rect);
                    break;
                case "Rain":
                    /* Creates rain particle object */
                    Particle rain = new Particle(x, y, 2, 25, new PVector(0, 10), 0.8, Color.rgb(75, 167, 244), BlendMode.ADD);
                    particles.add(rain);
                    break;
                case "torchFire":
                    /* Creates torch fire particle object */
                    Particle torchFire = new Particle(x, y, 13, 13, 90, 90, ArcType.ROUND, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.3, Color.rgb(89, 35, 13), BlendMode.ADD);
                    particles.add(torchFire);
                    break;
                case "Snow":
                    /* Creates snow particle object */
                    Particle snow = new Particle(x, y, 3.5, new PVector(0, 2), 0.8, Color.rgb(255,255,255), BlendMode.ADD);
                    particles.add(snow);
                    break;
                case "Meteors":
                    /* Creates meteor particle object */
                    Particle meteor = new Particle(x, y, 10, 30, 180, 180, ArcType.ROUND, new PVector(0, 10), 0.3, Color.rgb(185, 14, 23), BlendMode.ADD);
                    particles.add(meteor);
                    break;
                case "cherryBlossom":
                    /* Creates cherry blossom particle object */
                    Particle cherryBlossom = new Particle(x, y, 5.5, new PVector(0, 1), 0.8, Color.rgb(255, 183, 197),BlendMode.ADD);
                    particles.add(cherryBlossom);
                    break;
                default:
                    /* Creates generic (oval) particle object */
                    Particle generic = new Particle(x, y, 3.5, new PVector(Math.random() - 0.5, Math.random() * -0.5), 0.5, Color.rgb(200, 14, 23), BlendMode.ADD);
                    particles.add(generic);
            }
        }
        return particles;
    }
}