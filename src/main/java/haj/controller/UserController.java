package haj.controller;

import java.util.List;
import java.util.Optional;

import haj.model.User;
import haj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value={"/api"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Optional<User> user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

    @PostMapping(value="/user/create")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+user.getUsername());
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/user/get")
    public List<User> getAllUser() {
        List<User> tasks=userService.getUser();
        return tasks;

    }

    @PutMapping(value="/user/update")
    public ResponseEntity<String> updateUser(@RequestBody User currentUser)
    {
        System.out.println("sd");
        Optional<User> user = userService.findById(currentUser.getId());
        if (user==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        userService.update(currentUser, currentUser.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
        Optional<User> user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/user/{id}")
    public ResponseEntity<User> updateUserPartially(@PathVariable("id") long id, @RequestBody User currentUser){
        Optional<User> user = userService.findById(id);
        if(user ==null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        User usr = userService.updatePartially(currentUser, id);
        return new ResponseEntity<User>(usr, HttpStatus.OK);
    }
}