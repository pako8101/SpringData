package bg.softuni.springinitdemo.services;

import bg.softuni.springinitdemo.models.User;

public interface UserService {

    void register(String username, int age);


    User  findByUsername(String username);
}
