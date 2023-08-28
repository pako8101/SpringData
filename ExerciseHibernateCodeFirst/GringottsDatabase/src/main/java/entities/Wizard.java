package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table
public class Wizard {

    @Id
    @Column
    private long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 60)
    @NonNull
    private String lastname;

    @Column(length = 1000,nullable = false)
    private String note;

    @Column
    @NonNull
    private int age;

    @OneToOne
    @JoinColumn
    private MagicWand magicWand;

    @OneToMany
    @JoinTable(name = "wiyard_deposits",
    joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "deposit_id"))
    private List<Deposit> deposits;


    public Wizard() {
        this.deposits = new ArrayList<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
