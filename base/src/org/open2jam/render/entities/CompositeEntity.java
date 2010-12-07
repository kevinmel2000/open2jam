package org.open2jam.render.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author fox
 */
public class CompositeEntity extends Entity
{
    protected LinkedList<Entity> entity_list;

    public CompositeEntity(Collection<Entity> list)
    {
        entity_list = new LinkedList<Entity>();
        entity_list.addAll(list);
    }

    public CompositeEntity(Entity ... e)
    {
        entity_list = new LinkedList<Entity>();
        Collections.addAll(entity_list, e);
    }

    protected CompositeEntity(CompositeEntity org)
    {
        super(org);
        entity_list = new LinkedList<Entity>();
        for(Entity e : org.entity_list)entity_list.add(e.copy());
    }

    public LinkedList<Entity> getEntityList()
    {
        return entity_list;
    }
    
    @Override
    public void move(long delta)
    {
        super.move(delta);
        for(Entity e : entity_list)e.move(delta);
    }

    @Override
    public void setLayer(int layer)
    {
        super.setLayer(layer);
        for(Entity e : entity_list)e.setLayer(layer);
    }

    @Override
    public void setY(double y)
    {
        super.setY(y);
        for(Entity e : entity_list)e.setY(y);
    }

    @Override
    public void setX(double x)
    {
        super.setX(x);
        for(Entity e : entity_list)e.setX(x);
    }

    @Override
    public void setXMove(double dx)
    {
        throw new UnsupportedOperationException("CompositeEntity does not support this");
    }
    
    @Override
    public void setYMove(double dy)
    {
        throw new UnsupportedOperationException("CompositeEntity does not support this");
    }

    @Override
    public void draw()
    {
        for(Entity e : entity_list)e.draw();
    }

    @Override
    public void judgment()
    {
        for(Entity e : entity_list)e.judgment();
    }

    /**
     * compositeEntity is alive as long
     * as there is a entity alive inside it
     * @return
     */
    @Override
    public boolean isAlive(){
        Iterator<Entity> i = entity_list.iterator();
        while(i.hasNext()){
            Entity e = i.next();
            if(!e.isAlive())i.remove();
        }
        return !entity_list.isEmpty();
    }


    @Override
    public void setAlive(boolean state){
        for(Entity e : entity_list)e.setAlive(state);
    }

    @Override
    public CompositeEntity copy()
    {
        return new CompositeEntity(this);
    }
}