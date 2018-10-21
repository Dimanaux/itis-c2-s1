package app.db.dao.postgres;

import app.db.annotations.Column;
import app.db.annotations.Id;
import app.db.annotations.Table;
import app.db.models.Model;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class QueryHelper<M extends Model> {
    private final Connection connection;

    QueryHelper(Connection connection) {
        this.connection = connection;
    }

    PreparedStatement update(int id, M newModel) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(getTableName(newModel));
        query.append(" SET ");

        List<Field> notNullColumns = getNotNullColumns(newModel).collect(Collectors.toList());

        query.append(
                join("? = ?", ", ", notNullColumns.size())
        );

        query.append(" WHERE id = ?;");


        PreparedStatement statement = connection.prepareStatement(query.toString(), new String[]{});
        int i = fillStatement(newModel, notNullColumns, statement);
        statement.setInt(i, id);

        return statement;
    }

    PreparedStatement insert(M newModel) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
                .append(getTableName(newModel))
                .append("(");

        List<Field> notNullColumns = getNotNullColumns(newModel).collect(Collectors.toList());

        String join = join("?", ", ", notNullColumns.size());
        query.append(join)
                .append(") VALUES (")
                .append(join)
                .append(")");

        AnnotatedType idField = getIdField(newModel.getClass());
        String idColumnName = idField.getAnnotation(Column.class).name();

        Statement statement1 = connection.createStatement();

        PreparedStatement statement = connection.prepareStatement(
                query.toString(),
                Statement.RETURN_GENERATED_KEYS
        );
        fillStatement(newModel, notNullColumns, statement);

        return statement;
    }

    private AnnotatedType getIdField(Class<? extends Model> c) {
        return getColumns(c)
                .map(Field::getAnnotatedType)
                .filter(t -> t.isAnnotationPresent(Id.class))
                .findAny().get();
    }

    private int fillStatement(M newModel, List<Field> notNullColumns, PreparedStatement statement) throws SQLException {
        int i = 1;
        for (Field f : notNullColumns) {
            f.setAccessible(true);
            try {
                statement.setString(i, String.valueOf(f.get(newModel)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        return i;
    }

    private String getTableName(M newModel) {
        return newModel.getClass().getAnnotation(Table.class).table();
    }

    private Stream<Field> fields(Class mClass) {
        return Stream.of(mClass.getDeclaredFields());
    }

    private Stream<Field> getColumns(Class mClass) {
        return fields(mClass).filter(
                f -> f.getAnnotatedType().isAnnotationPresent(Column.class)
        );
    }

    private Stream<Field> getNotNullColumns(M instance) {
        return getColumns(instance.getClass()).filter(
                f -> {
                    boolean notNull = false;
                    f.setAccessible(true);
                    try {
                        notNull = f.get(instance) != null;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return notNull;
                }
        );
    }

    private String join(String string, String separator, int times) {
        StringBuilder builder = new StringBuilder();
        builder.append(string);
        for (int i = 1; i < times; i++) {
            builder.append(separator);
            builder.append(string);
        }
        return builder.toString();
    }
}
