package za.co.neslotech.spring.trainspringbootinsight.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.repository.IUserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("The User with id %d was not found!", id))
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("The User with id %d was not found!", id))
        );

        userRepository.delete(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user){

        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("The User with id %d was not found!", id))
        );

        BeanUtils.copyProperties(user, existingUser);

        return userRepository.saveAndFlush(existingUser);
    }

}
