package app.db.dao;

import app.db.models.Model;

import java.util.List;

public interface Dao<M extends Model> {
    M save(M model);
    List<M> getAll();
    M getById(int id);
    boolean update(M newModel);
    boolean delete(M model);
    boolean deleteById(int id);
}
