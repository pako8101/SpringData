package bg.softuni.springinitdemo;

import bg.softuni.springinitdemo.models.User;
import bg.softuni.springinitdemo.services.AccountService;
import bg.softuni.springinitdemo.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {


    final private UserService userService;
   final private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService,AccountService accountService){
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = this.userService.findByUsername("first");
       this.accountService.
               depositMoney(BigDecimal.TEN,user.getAccountsId().get(0));
       this.accountService.withdrawMoney(BigDecimal.ONE,user.getAccountsId().get(0));
    }
}
