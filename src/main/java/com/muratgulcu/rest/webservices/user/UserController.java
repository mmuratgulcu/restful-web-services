package com.muratgulcu.rest.webservices.user;

import org.springframework.context.MessageSource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController {

   private final UserDaoService userDaoService;

   private final MessageSource messageSource;

    public UserController(UserDaoService userDaoService, MessageSource messageSource) {
        this.userDaoService = userDaoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return  userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable("id") Integer id){
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class)
                .slash(user.getId()).withSelfRel();

        Link retrieveAllUsers =
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass())
                                .retrieveAllUsers()).withRel("retrieveAllUsers");

        Link createUser =
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass())
                                .createUser(user)).withRel("createUser");

        /*
        HATEOAS code with earlier versions looks something like this:
        Resource and ControllerLinkBuilder instead of EntityModel and WebMvcLinkBuilder.
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo =
        linkTo(methodOn(this.getClass()).retrieveAllUsers());
         */

        user.add(selfLink,retrieveAllUsers,createUser);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User savedUser = userDaoService.save(user);

        //Location information
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        User user = userDaoService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("deleteUser : id : " + id);
        }
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader (name = "Accept-Language",required = false) Locale locale){
        System.out.println(locale);
        return messageSource.getMessage("good.morning.message",null ,"Default Message",locale);
    }
}
