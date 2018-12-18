package app.db.dao.postgres;

import app.db.dao.Dao;
import app.db.models.Model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<M extends Model> implements Dao<M> {
    final Connection connection;

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public M save(M model) {
        try {
            PreparedStatement statement = new QueryHelper<M>(connection).insert(model);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            return instance(keys);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<M> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName()
            );
            ResultSet rs = statement.executeQuery();
            List<M> models = new LinkedList<>();
            
            while (rs.next()) {
                M model = instance(rs);
                models.add(model);
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
                    String.format("SELECT * FROM %s WHERE id = ? ", getTableName())
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return instance(rs);
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

    public abstract M instance(ResultSet rs) throws SQLException;

    String getString(ResultSet rs, String columnName) {
        try {
            return rs.getString(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    Integer getInt(ResultSet rs, String columnName) {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    Date getDate(ResultSet rs, String columnName) {
        try {
            return rs.getDate(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    Boolean getBoolean(ResultSet rs, String columnName) {
        try {
            return rs.getBoolean(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
