package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class User extends BaseEntity {
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String fullName;
    @Column
    private BigDecimal balance;


}
