package dominio.Repositorios;

import dominio.Repositorios.daos.IDAO;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private IDAO<T> dao;

    public Repository(IDAO<T> dao) {
        this.dao = dao;
    }

    public void setDao(IDAO<T> dao) {
        this.dao = dao;
    }

    public boolean exist(int id){
        return this.dao.exist(id);
    }

    public T find(int id, Class<T> clase){
        return this.dao.find(id, clase);
    }

    public List<T> findAll(Class<T> clase){
        return this.dao.findAll(clase);
    }

    public void delete(T object){
        this.dao.delete(object);
    }

    public void update(T object){
        this.dao.update(object);
    }

    public void insert(T object){
        this.dao.insert(object);
    }
}