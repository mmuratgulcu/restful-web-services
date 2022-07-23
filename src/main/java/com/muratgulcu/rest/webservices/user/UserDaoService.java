package com.muratgulcu.rest.webservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Adam", LocalDate.now()));
        users.add(new User(2,"Eve", LocalDate.now()));
        users.add(new User(3,"Jack", LocalDate.now()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save (User user){
        users.add(user);
        return user;
    }

    public User findOne(Integer id){
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(Integer id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;

    }
}
