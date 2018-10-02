package kg.jedi.business;

import kg.jedi.model.Record;

import java.time.Instant;
import java.util.Date;

public class ScheduledTask implements Runnable {

    private final RecordWriteService service;

    public ScheduledTask(RecordWriteService service) {
        this.service = service;
    }

    @Override
    public void run() {
        Record aRecord = new Record(Date.from(Instant.now()));
        service.write(aRecord);
    }
}
