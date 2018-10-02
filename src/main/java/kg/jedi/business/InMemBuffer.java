package kg.jedi.business;

import kg.jedi.model.Record;

import java.util.LinkedList;
import java.util.Queue;

public class InMemBuffer {

    private static InMemBuffer INSTANCE;

    private Queue<Record> records;

    private InMemBuffer() {
        records = new LinkedList<>();
    }

    public static InMemBuffer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InMemBuffer();
        }

        return INSTANCE;
    }

    public void addRecord(Record aRecord) {
        records.add(aRecord);
    }

    public Record getFirst() {
        return records.poll();
    }
}
