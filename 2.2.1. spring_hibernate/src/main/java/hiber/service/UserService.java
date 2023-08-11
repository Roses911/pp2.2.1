package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserAndCar(String model, int series);
    public void clearUser(Long id);

}
