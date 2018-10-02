package kg.jedi;

import kg.jedi.business.RecordReadService;
import kg.jedi.business.RecordWriteService;
import kg.jedi.business.ScheduledTask;
import kg.jedi.business.impl.RecordReadServiceImpl;
import kg.jedi.business.impl.RecordWriteServiceImpl;
import kg.jedi.model.Record;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public void startApp(String[] args) {

        if (args.length != 0 && args[0].equals("-p")) {
            RecordReadService service = new RecordReadServiceImpl();
            Collection<Record> records = service.readAll();
            records.forEach(it -> System.out.println(it.toString()));
        } else {
            RecordWriteService service = new RecordWriteServiceImpl();

            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(new ScheduledTask(service), 0, 1, TimeUnit.SECONDS);
        }
    }
}
