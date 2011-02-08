package org.open2jam.render.entities;

import org.open2jam.parser.Event;
import org.open2jam.render.SpriteList;
import org.open2jam.render.Render;

/** a NoteEntity is a animated entity which moves down.
**/
public class NoteEntity extends AnimatedEntity implements TimeEntity
{
    protected Render render;

    protected Event.SoundSample sample_value;

    protected Event.Channel channel = Event.Channel.NONE;

    protected State state = State.NOT_JUDGED;

    protected double hit = 0;

    protected long time_to_hit;

    public enum State {
        NOT_JUDGED,
        LN_HEAD_JUDGE,
        JUDGE,
        TO_KILL,
        LN_HOLD
    };

    public NoteEntity(Render r, SpriteList sl, Event.Channel ch, double x, double y)
    {
        super(sl, x, y);
        this.channel = ch;
        this.render = r;
    }

    protected NoteEntity(NoteEntity org) {
        super(org);
        this.channel = org.channel;
        this.render = org.render;
        this.sample_value = org.sample_value;
        this.state = org.state;
    }
    
    public void setSample(Event.SoundSample sample){
        this.sample_value = sample;
    }

    public Event.SoundSample getSample(){ return sample_value; }

//    @Override
//    public void move(long delta)
//    {
//	setYMove(render.getNoteSpeed());
//	super.move(delta);
//    }

    public void setHit(double hit) { this.hit = hit; }
    public double getHit() { return hit; }

    public void setState(State value) { state = value; }
    public State getState() { return state; }


    public void setTime(long time){
        this.time_to_hit = time;
    }
    public long getTime() {
        return time_to_hit;
    }
    
    @Override
    public void judgment()
    {
        alive = false;
    }

    @Override
    public NoteEntity copy(){
        return new NoteEntity(this);
    }

    public double getStartY(){
        return y + height;
    }


    public Event.Channel getChannel() {
        return channel;
    }
}