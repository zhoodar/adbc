package kg.jedi.business.impl;

import kg.jedi.business.RecordReadService;
import kg.jedi.db.DB;
import kg.jedi.model.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class RecordReadServiceImpl implements RecordReadService {


    @Override
    public Collection<Record> readAll() {

        Collection<Record> result = new ArrayList<>();

        try {
            Connection connection = DB.getConnection();

            if (null != connection) {

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * from records");

                while (resultSet.next()) {

                    Timestamp aTimestamp = resultSet.getTimestamp("a_timestamp");
                    result.add(new Record(aTimestamp));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Something went wrong");
        }

        return result;
    }
}
