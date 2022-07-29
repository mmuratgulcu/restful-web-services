package com.muratgulcu.rest.webservices.jpa;

import com.muratgulcu.rest.webservices.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonRepository personRepository;
    private final PostRepository postRepository;

    public PersonController(PersonRepository personRepository, PostRepository postRepository) {
        this.personRepository = personRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/persons")
    public List<Person> retrieveAllPersons(){
        return  personRepository.findAll();
    }

    @GetMapping("jpa/persons/{id}")
    public ResponseEntity<Person> retrievePerson(@PathVariable("id") Integer id){
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }

        return ResponseEntity.ok(person.get());
    }


    @DeleteMapping("/jpa/persons/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
       personRepository.deleteById(id);
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person){
        Person result = personRepository.save(person);

        //Location information
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("jpa/persons/{id}/posts")
    public ResponseEntity<List<Post>> retrievePost(@PathVariable("id") Integer id){
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }

        return ResponseEntity.ok(person.get().getPostList());
    }

    @PostMapping("/jpa/persons/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {

        Optional<Person> personOptional = personRepository.findById(id);

        if(!personOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        Person person = personOptional.get();

        post.setPerson(person);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
