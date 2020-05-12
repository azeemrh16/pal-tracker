package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
Map<Long, TimeEntry> repo = new HashMap<>();
private Long counter=1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
     timeEntry.setId(counter++);
     repo.put(timeEntry.getId(),timeEntry);
     return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        TimeEntry time= repo.get(id);
        return time;
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timelist= new ArrayList<>(repo.values());
        return timelist;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(repo.containsKey(id))
        {
            timeEntry.setId(id);
            repo.put(timeEntry.getId(),timeEntry);
        } else{
            timeEntry=null;
        }
        return timeEntry;
    }

    @Override
    public void delete(long id) {
    repo.remove(id);
    }
}
