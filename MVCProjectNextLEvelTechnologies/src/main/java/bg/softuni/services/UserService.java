package bg.softuni.services;

import bg.softuni.dtos.users.UserRegisterDTO;
import org.springframework.stereotype.Service;


public interface UserService {
    boolean register (UserRegisterDTO registerDTO);
}
