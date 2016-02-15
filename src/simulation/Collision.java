package simulation;

public abstract class Collision extends AbstractEvent{

    Particle[] ps;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        super(t);
        this.ps = ps;
    }


    /**
     * Returns true if this Collision is (still) valid.
     */
    public boolean isValid() {
        for (int i = 0; i < ps.length; i++) {
            if (ps[i].collisions() != 0) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }


    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return ps;
    }
}
