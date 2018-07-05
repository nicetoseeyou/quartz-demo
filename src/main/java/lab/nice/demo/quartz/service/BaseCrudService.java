package lab.nice.demo.quartz.service;

import java.util.List;

public interface BaseCrudService<Entity, Key> {
    void save(Entity entity);
    Entity findById(Key key);
    List<Entity> findAll();
}
