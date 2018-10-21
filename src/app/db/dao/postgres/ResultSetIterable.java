package app.db.dao.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultSetIterable implements Iterable<ResultSet> {
    private final ResultSet resultSet;

    public ResultSetIterable(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Iterator<ResultSet> iterator() {
        return new Iterator<ResultSet>() {
            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                try {
                    hasNext = resultSet.next();
                    resultSet.previous();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return hasNext;
            }

            @Override
            public ResultSet next() {
                try {
                    ResultSetIterable.this.resultSet.next();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return ResultSetIterable.this.resultSet;
            }
        };
    }
}
