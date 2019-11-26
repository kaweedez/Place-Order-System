package lk.ijse.dep.pos.dao;

import lk.ijse.dep.pos.entity.SuperEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    private Class<T> entity;
    @PersistenceContext
    protected EntityManager entityManager;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass()))
                .getActualTypeArguments()[0]);    }

    @Override
    public void setEntityManager(EntityManager entiryManager) {
        this.entityManager = entiryManager;

    }


    @Override
    public List<T> findAll() throws Exception {
        return entityManager.createQuery("SELECT c FROM " + entity.getName() + " c", entity)
                .getResultList();    }

    @Override
    public T find(ID id) throws Exception {
        return entityManager.getReference(entity, id);
    }

    @Override
    public void save(T entity) throws Exception {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        entityManager.merge(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        entityManager.remove(entityManager.find(entity, id));

    }
}
