package haj.service;

import haj.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public List<User> getUser();
    public Optional<User> findById(long id);
    public User update(User user, long l);
    public User deleteUserById(long id);
    public User updatePartially(User user, long id);
}
