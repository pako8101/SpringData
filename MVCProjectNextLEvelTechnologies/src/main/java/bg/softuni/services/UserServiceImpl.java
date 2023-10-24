package bg.softuni.services;

import bg.softuni.dtos.users.UserRegisterDTO;
import bg.softuni.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean register(UserRegisterDTO registerDTO) {
 ModelMapper mapper = new ModelMapper();
        final User user = mapper.map(registerDTO, User.class);


        return false;
    }
}
