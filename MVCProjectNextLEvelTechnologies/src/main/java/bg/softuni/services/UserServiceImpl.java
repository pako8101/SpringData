package bg.softuni.services;

import bg.softuni.dtos.users.UserRegisterDTO;
import bg.softuni.entities.User;
import bg.softuni.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(UserRegisterDTO registerDTO) {
 ModelMapper mapper = new ModelMapper();
        final User user = mapper.map(registerDTO, User.class);
        final boolean emailInUse = userRepository.existsByEmail(registerDTO.getEmail());

        if (emailInUse){
            return false;
        }
        userRepository.save(user);
        return true;
    }
}
