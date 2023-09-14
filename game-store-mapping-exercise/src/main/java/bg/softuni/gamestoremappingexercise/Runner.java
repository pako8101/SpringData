package bg.softuni.gamestoremappingexercise;

import bg.softuni.gamestoremappingexercise.services.User.UserService;
import bg.softuni.gamestoremappingexercise.services.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static bg.softuni.gamestoremappingexercise.constants.Commands.*;
@Component
public class Runner implements CommandLineRunner {
   private static final Scanner SCANNER = new Scanner(System.in);
   private final UserService userService;
   private final GameService gameService;

   @Autowired
    public Runner(UserService userService, GameService gameService) {
        this.userService = userService;
       this.gameService = gameService;
   }

    @Override
    public void run(String... args) throws Exception {
        String input ;

        while (!(input=SCANNER.nextLine()).equals(CLOSE)){
          final   String [] commands = input.split("\\|");
          final String command = commands[0];
          final String output =
            switch (command){
                case REGISTER_USER ->  this.userService.registerUser(commands);
                case LOGGED_USER ->  this.userService.LOGGINUser(commands);
                case LOGOUT_USER ->  this.userService.LOGGOutUser();
                case ADD_GAME ->  this.gameService.addGame(commands);
                case EDIT_GAME ->  this.gameService.editGame(commands);
                case DELETE_GAME ->  this.gameService.deleteGame(commands);

                default -> "no command";
            };

            System.out.println(output);

        }

    }

}
