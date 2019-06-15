package haj.controller;

import haj.model.FeedBack;
import haj.model.User;
import haj.service.FeedBackService;
import haj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value={"/api"})
public class FeedbackController {
    @Autowired
    FeedBackService feedBackService;

    @GetMapping(value = "/feedBack/{id}")
    public ResponseEntity<Optional<FeedBack>> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Optional<FeedBack> feedBack = feedBackService.findById(id);
        if (feedBack == null) {
            return new ResponseEntity<Optional<FeedBack>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<FeedBack>>(feedBack, HttpStatus.OK);
    }

    @PostMapping(value="/feedBack/create")
    public ResponseEntity<FeedBack> createUser(@RequestBody FeedBack feedBack, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+feedBack.getWebFileNumber());
        feedBackService.createFeedBac(feedBack);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/feedBack/{id}").buildAndExpand(feedBack.getId()).toUri());
        return new ResponseEntity<FeedBack>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/feedBack/get")
    public List<FeedBack> getAllUser() {
        List<FeedBack> tasks=feedBackService.getFeedBack();
        return tasks;

    }

    @PutMapping(value="/feedBack/update")
    public ResponseEntity<String> updateUser(@RequestBody FeedBack currentFeedBack)
    {
        System.out.println("sd");
        Optional<FeedBack> feedBack = feedBackService.findById(currentFeedBack.getId());
        if (feedBack==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        feedBackService.update(currentFeedBack, currentFeedBack.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/feedBack/{id}")
    public ResponseEntity<FeedBack> deleteUser(@PathVariable("id") long id){
        Optional<FeedBack> feedBack = feedBackService.findById(id);
        if (feedBack == null) {
            return new ResponseEntity<FeedBack>(HttpStatus.NOT_FOUND);
        }
        feedBackService.deleteFeedBacById(id);
        return new ResponseEntity<FeedBack>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/feedBack/{id}")
    public ResponseEntity<FeedBack> updateUserPartially(@PathVariable("id") long id, @RequestBody FeedBack currentFeedBack){
        Optional<FeedBack> feedBack = feedBackService.findById(id);
        if(feedBack ==null){
            return new ResponseEntity<FeedBack>(HttpStatus.NOT_FOUND);
        }
        FeedBack updateFeedBack = feedBackService.updatePartially(currentFeedBack, id);
        return new ResponseEntity<FeedBack>(updateFeedBack, HttpStatus.OK);
    }
}