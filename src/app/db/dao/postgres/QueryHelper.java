package app.db.dao.postgres;

import app.db.annotations.Column;
import app.db.annotations.Id;
import app.db.annotations.Table;
import app.db.models.Model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class QueryHelper<M extends Model> {
    private final Connection connection;

    QueryHelper(Connection connection) {
        this.connection = connection;
    }

    PreparedStatement update(int id, M newModel) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(getTableName(newModel));
        query.append(" SET ");

        List<Field> notNullColumns = getNotNullColumns(newModel);

        query.append(
                join("% = ?", notNullColumns.size())
        );
        query.append(" WHERE id = ?;");

        String queryAsString = query.toString();

        PreparedStatement statement = connection.prepareStatement(
                String.format(queryAsString, (Object[]) columnNames(notNullColumns)),
                Statement.RETURN_GENERATED_KEYS
        );
        int i = fillStatement(newModel, notNullColumns, statement);
        statement.setInt(i, id);

        return statement;
    }

    PreparedStatement insert(M newModel) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
                .append(getTableName(newModel))
                .append(" (");

        List<Field> notNullColumns = getNotNullColumns(newModel);

        String columns = join("%s", notNullColumns.size());
        String values = join("?", notNullColumns.size());
        query.append(columns)
                .append(") VALUES (")
                .append(values)
                .append(");");

        String queryAsString = query.toString();

        PreparedStatement statement = connection.prepareStatement(
                String.format(queryAsString, (Object[]) columnNames(notNullColumns)),
                Statement.RETURN_GENERATED_KEYS
        );

        fillStatement(newModel, notNullColumns, statement);

        return statement;
    }

    private String[] columnNames(List<Field> columns) {
        String[] names = new String[columns.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = columns.get(i).getAnnotation(Column.class).name();
        }
        return names;
    }

    private int fillStatement(M values, List<Field> notNullColumns, PreparedStatement statement) throws SQLException {
        int count = 1;
        for (Field f : notNullColumns) {
            f.setAccessible(true);
            try {
                statement.setObject(count, f.get(values));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            count++;
        }
        return count;
    }

    private String getTableName(M newModel) {
        return newModel.getClass().getAnnotation(Table.class).table();
    }

    private boolean isColumn(Field field) {
        return field.getAnnotation(Column.class) != null && field.getAnnotation(Id.class) == null;
    }

    private List<Field> getColumns(Class<? extends Model> clazz) {
        List<Field> columns = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (isColumn(f)) {
                columns.add(f);
            }
        }
        return columns;
    }

    private List<Field> getNotNullColumns(M instance) {
        List<Field> notNull = new ArrayList<>();

        for (Field f : getColumns(instance.getClass())) {
            f.setAccessible(true);
            try {
                if (f.get(instance) != null) {
                    notNull.add(f);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notNull;
    }

    private String join(String string, int times) {
        StringBuilder builder = new StringBuilder();
        builder.append(string);
        for (int i = 1; i < times; i++) {
            builder.append(", ");
            builder.append(string);
        }
        return builder.toString();
    }
}
