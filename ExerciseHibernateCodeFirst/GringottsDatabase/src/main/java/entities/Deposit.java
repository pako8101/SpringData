package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Deposit {
    @Id
    @Column
    private Long id;

    @Column(length = 20)
    private String group ;

    @Column
    private LocalDate startDate ;


    @Column
    private Double amount ;
    @Column
    private Double interest ;
    @Column
    private Double charge;
    @Column
    private LocalDate expirationDate  ;
    @Column
    private boolean isExpired ;
}
