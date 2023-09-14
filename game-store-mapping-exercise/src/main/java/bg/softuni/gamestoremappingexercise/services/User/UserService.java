package bg.softuni.gamestoremappingexercise.services.User;

import bg.softuni.gamestoremappingexercise.domain.entities.User;
import bg.softuni.gamestoremappingexercise.domain.models.UserLoginDto;

import java.util.Optional;

public interface UserService {

    String registerUser(String[]args);
    String LOGGINUser(String[]args);


    String LOGGOutUser();


    boolean isLoggedUserAdmin();
}
