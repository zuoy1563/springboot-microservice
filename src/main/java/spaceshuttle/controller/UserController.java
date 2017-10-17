package spaceshuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spaceshuttle.model.User;
import spaceshuttle.repository.UserRepository;

@RestController
@RequestMapping(path = "/users") // This means URL's start with /user (after Application path)

public class UserController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @RequestMapping("/hello")
    public String hello() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String username
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "Saved";
    }

    @PostMapping// Map ONLY GET Requests
    public @ResponseBody
    User addUser(@RequestBody User newUser) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return userRepository.save(newUser);
    }
}