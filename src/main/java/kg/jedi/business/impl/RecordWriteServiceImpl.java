package kg.jedi.business.impl;

import kg.jedi.business.InMemBuffer;
import kg.jedi.business.RecordWriteService;
import kg.jedi.db.DB;
import kg.jedi.model.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RecordWriteServiceImpl implements RecordWriteService {


    @Override
    public void write(Record record) {
        Connection connection = connectDB();
        InMemBuffer memBuffer = InMemBuffer.getInstance();

        if (null != connection) {
            try(PreparedStatement prpStat = connection.prepareStatement("insert into records(a_timestamp) values(?)")) {

                Record tmpRecord;
                while ((tmpRecord = memBuffer.getFirst()) != null) {
                    prpStat.setTimestamp(1, new Timestamp(tmpRecord.getTimestamp().getTime()));
                    prpStat.executeUpdate();
                }

                prpStat.setTimestamp(1, new Timestamp(record.getTimestamp().getTime()));
                prpStat.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            memBuffer.addRecord(record);
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connectDB() {
        try {
            return DB.getConnection();
        } catch (SQLException e) {
            System.out.println("Could not connect to DB");
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.schedule(new DbConnectorTask(), 5, TimeUnit.SECONDS);
            service.shutdown();
        } catch (ClassNotFoundException e) {
            System.out.println("Did not find the driver class");
        }
        return null;
    }

    class DbConnectorTask implements Runnable {

        @Override
        public void run() {
            Connection connection = connectDB();
            InMemBuffer memBuffer = InMemBuffer.getInstance();

            if (null != connection) {
                try(PreparedStatement prpStat = connection.prepareStatement("insert into record(a_timestamp) values(?)")) {

                    Record tmpRecord;
                    while ((tmpRecord = memBuffer.getFirst()) != null) {
                        prpStat.setTimestamp(1, new Timestamp(tmpRecord.getTimestamp().getTime()));
                        prpStat.executeUpdate();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
