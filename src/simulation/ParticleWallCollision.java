package simulation;

public class ParticleWallCollision extends Collision {

    private final Wall w;

    public ParticleWallCollision(Particle p1,  Wall w, double t) {
        super(t, new Particle[] {p1} );
        this.w = w;
    }

    @Override
    public void happen(ParticleEventHandler h) {
        Particle.collide(ps[0] ,w);
        h.reactTo(this);
    }
}
