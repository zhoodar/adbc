package kg.jedi.business;

import kg.jedi.model.Record;

import java.util.Collection;

public interface RecordReadService {

    Collection<Record> readAll();
}
