package app.db.dao.postgres;

import app.db.dao.Dao;
import app.db.models.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<M extends Model> implements Dao<M> {
    protected final Connection connection;

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(M model) {
        try {
            PreparedStatement statement = new QueryHelper<M>(connection).insert(model);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<M> getAll() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName()
            );
            ResultSet rs = statement.executeQuery();
            M model = instance(rs);
            LinkedList<M> models = new LinkedList<>();
            while (model != null) {
                models.add(model);
                model = instance(rs);
            }
            return models;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @Override
    public M getById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    String.format("SELECT * FROM %s WHERE id = ?", getTableName())
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return instance(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(M newModel) {
        try {
            PreparedStatement statement = new QueryHelper<>(connection).update(newModel.getId(), newModel);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    String.format("DELETE FROM %s WHERE id = ?", getTableName())
            );
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(M model) {
        return deleteById(model.getId());
    }

    public abstract String getTableName();

    protected abstract M instance(ResultSet resultSet) throws SQLException;
}
