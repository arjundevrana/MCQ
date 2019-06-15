package haj.service;

import haj.model.User;
import haj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User deleteUserById(long id) {
        return null;
    }

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        // TODO Auto-generated method stub
        User saveUser=  userRepository.save(user);
        return saveUser;
    }

    public List<User> getUser() {
        // TODO Auto-generated method stub
        return (List<User>) userRepository.findAll();
    }


    public User update(User user, long l) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    public User updatePartially(User user, long id) {
        // TODO Auto-generated method stub
        Optional<User> usr = findById(id);
        //usr.setCountry(user.getCountry());
        return userRepository.save(usr.get());
    }
}