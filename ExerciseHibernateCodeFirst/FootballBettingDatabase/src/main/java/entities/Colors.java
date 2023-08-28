package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Colors {
    @Id
    @Column
    private Long id;

    @Column
    private String name;


}
