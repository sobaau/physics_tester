package rewind.physics.Particles;

import java.util.List;

public abstract class ParticleSpawn {

    /**
     * Abstract method that is implemented by the ParticleCreation class
     *
     * @param x - x coordinate for drawing particle
     * @param y - y coordinate for drawing particle
     * @param particleType - the type of particle to be created
     * @return - particle array list
     */
    public abstract List<Particle> spawn(double x, double y, String particleType);
}