package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository= timeEntryRepository;
    }
    private TimeEntryRepository timeEntryRepository;
    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        timeEntryToCreate= timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(timeEntryToCreate, HttpStatus.CREATED);
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
      TimeEntry timeEntry= timeEntryRepository.find(timeEntryId);
      if(timeEntry!=null){
          return new ResponseEntity(timeEntry,HttpStatus.OK);
      } else{
        return new ResponseEntity(timeEntry,HttpStatus.NOT_FOUND);
      }
    }
@GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList= timeEntryRepository.list();

        return new ResponseEntity(timeEntryList,HttpStatus.OK);

    }
@PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updatedtime= timeEntryRepository.update(timeEntryId,expected);
        if(updatedtime!=null){
            return new ResponseEntity(updatedtime,HttpStatus.OK) ;
        } else {
            return new ResponseEntity(updatedtime, HttpStatus.NOT_FOUND);
        }
    }
@DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(timeEntryId,HttpStatus.NO_CONTENT);
    }
}
