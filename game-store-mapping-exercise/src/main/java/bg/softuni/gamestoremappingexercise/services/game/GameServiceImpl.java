package bg.softuni.gamestoremappingexercise.services.game;

import bg.softuni.gamestoremappingexercise.domain.entities.Game;
import bg.softuni.gamestoremappingexercise.domain.models.GameDto;
import bg.softuni.gamestoremappingexercise.domain.models.GameEditDto;
import bg.softuni.gamestoremappingexercise.domain.repositories.GameRepository;
import bg.softuni.gamestoremappingexercise.services.User.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final GameRepository gameRepository;
    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }


    @Override
    public String addGame(String[] args) {
        if (!this.userService.isLoggedUserAdmin()) return "Logged user is not admin.";

        int argsLength = args.length;

        final String title = argsLength > 1 ? args[1] : "";
        final BigDecimal price = argsLength > 2 ? new BigDecimal(args[2]) : BigDecimal.ZERO;
        final Double size = argsLength > 3 ? Float.parseFloat(args[3]) : 0.0;
        final String trailer = argsLength > 4 ? args[4] : "";
        final String thubnailURL = argsLength > 5 ? args[5] : "";
        final String description = argsLength > 6 ? args[6] : "";
        final LocalDate releaseDate = LocalDate.now();

        final GameDto gameDto = new GameDto(title,
                price, size, trailer, thubnailURL,
                description, releaseDate);

        final Game gameToBeSaved = this.modelMapper.map(gameDto, Game.class);

        gameRepository.saveAndFlush(gameToBeSaved);
        return gameDto.successfullyAddedGAme();
    }

    @Override
    public String deleteGame(String[] args) {

        if (!this.userService.isLoggedUserAdmin()) return "Logged user is not admin.";

        final Optional<Game> gameToByDeleted = this.gameRepository.findById(Long.valueOf(args[1]));

        if (gameToByDeleted.isEmpty()) return "No such game";

        this.gameRepository.delete(gameToByDeleted.get());

        return null;
    }

    @Override
    public String editGame(String[] args) {
        if (!this.userService.isLoggedUserAdmin()) return "Logged user is not admin.";

        final Optional<Game> gameToByEdited = this.gameRepository.findById(Long.valueOf(args[1]));

        if (gameToByEdited.isEmpty()) return "No such game";


        Map<String, String> updatingArgs = new HashMap<>();
        for (int i = 2; i < args.length; i++) {
            String[] argumentsForUpdate = args[i].split("=");
            updatingArgs.put(argumentsForUpdate[0], argumentsForUpdate[1]);
        }
        final GameEditDto gameEditDto = this.modelMapper.map(gameToByEdited.get(), GameEditDto.class);

        gameEditDto.updateFields(updatingArgs);
        final Game gameToBeSaved = this.modelMapper.map(gameEditDto, Game.class);

        this.gameRepository.saveAndFlush(gameToBeSaved);

        return "Edited " + gameEditDto.getTitle();
    }
}
