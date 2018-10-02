package kg.jedi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

    private Date timestamp;

    public Record(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(timestamp);
    }
}
