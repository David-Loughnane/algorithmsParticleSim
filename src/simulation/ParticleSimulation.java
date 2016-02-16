package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;


public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel particle_model;
    private final ParticlesView screen;
    private MinPriorityQueue<Event> pq = new MinPriorityQueue<>();
    private double t = 0.0;
    private Event next_event;
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
        screen = new ParticlesView(name, m);
        particle_model = m;
        Tick new_tick = new Tick(1);
        pq.add(new_tick);
        Iterable<Collision> initial_collisions = m.predictAllCollisions(t);
        for (Collision collision_to_add : initial_collisions) {
            pq.add(collision_to_add);
        }
    }

    /**
     * Runs the simulation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeAndWait(screen);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!pq.isEmpty()) {
            next_event = pq.remove();
            if (next_event.isValid()) {
            	double oldT = t;
                t = next_event.time();
                particle_model.moveParticles(t - oldT);

                try {
					next_event.happen(this);
					//System.out.println(next_event);
				} catch (InterruptedException e) {
					System.out.println(e);
					e.printStackTrace();
				}
                
            }
        }
    }


    @Override
    public void reactTo(Tick tick) {
        try {
            Thread.sleep(FRAME_INTERVAL_MILLIS); //throw exception
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        screen.update();
        Tick newT = new Tick(tick.time()+1.0); 
        pq.add(newT);
    }

    @Override
    public void reactTo(Collision c) {
        Iterable<Collision> new_collisions = particle_model.predictAllCollisions(t);
        for (Collision collision_to_add : new_collisions) {
            pq.add(collision_to_add);
        }
    }
}
