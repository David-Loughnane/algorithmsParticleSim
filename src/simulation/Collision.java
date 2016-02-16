package simulation;

public abstract class Collision extends AbstractEvent{

    Particle[] ps;
    int[] startHit;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        super(t);
        this.ps = ps;
        startHit = new int[ps.length];
        for(int i = 0; i < ps.length; i++){
        	if(ps[i] != null){
        		startHit[i] = ps[i].collisions();
        	}
        }
    }


    /**
     * Returns true if this Collision is (still) valid.
     */
    // CHECK DEFINITION OF ISVALID - for all particles involved in collision
    public boolean isValid() {
        for (int i = 0; i < ps.length; i++) {
        	if (ps[i] != null && ps[i].collisions() != startHit[i]) {
                return false;
            }
        }
        //System.out.println(ps[0].collisions());
        return true;
    }


    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return ps;
    }
}
