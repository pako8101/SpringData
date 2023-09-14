package bg.softuni.gamestoremappingexercise.services.User;

import bg.softuni.gamestoremappingexercise.domain.entities.User;
import bg.softuni.gamestoremappingexercise.domain.models.UserLoginDto;
import bg.softuni.gamestoremappingexercise.domain.models.UserRegisterDto;
import bg.softuni.gamestoremappingexercise.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static bg.softuni.gamestoremappingexercise.constants.Commands.*;
import static bg.softuni.gamestoremappingexercise.constants.ErrorMessages.*;

@Service

public class UserServiceImpl implements UserService {
    private User loggedInUser;
    final private UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(String[] args) {
        final int argsLength = args.length;

        final String email = argsLength > 1 ? args[1] : "";
        final String password = argsLength > 2 ? args[2] : "";
        final String confirmPassword = argsLength > 3 ? args[3] : "";
        final String fullName = argsLength > 4 ? args[4] : "";
        UserRegisterDto userRegisterDto;
        try {
            userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }
        if (this.userRepository.findFirstByEmail(userRegisterDto.getEmail()).isPresent()) {
            return EMAIL_ALREADY_EXIST;
        }
        final User user = this.modelMapper.map(userRegisterDto, User.class);
        if (this.userRepository.count()== 0){
            user.setAdmin(true);
        }else {
            user.setAdmin(false);
        }



        this.userRepository.saveAndFlush(user);

        this.loggedInUser = user;

        return userRegisterDto.successfullyRegisteredUser();

    }

    @Override
    public String LOGGINUser(String[] args) {
        if (this.loggedInUser!= null) return "User is already logged";

        final int argsLength = args.length;

        final String email = argsLength > 1 ? args[1] : "";
        final String password = argsLength > 2 ? args[2] : "";

        final Optional<User> userToLogged = this.userRepository.findFirstByEmail(email);

       if (userToLogged.isEmpty()) return  "Incorrect user.";

        final UserLoginDto userLoginDto = new UserLoginDto(email, password);

        try {
            userLoginDto.validate(userToLogged.get().getPassword());

       }catch (IllegalArgumentException exception){
           return exception.getMessage();
       }



       this.loggedInUser = userToLogged.get();

        return userLoginDto.successfullyLogged();
    }

    @Override
    public String LOGGOutUser() {
        if (this.loggedInUser == null) return "No user logged";

        this.loggedInUser = null;

        return LOGOUT_USER;

    }

    @Override
    public boolean isLoggedUserAdmin(){
        return this.loggedInUser != null && this.loggedInUser.isAdmin();
    }
}
