package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "magic_wand")
public class MagicWand {


    @Id
    @Column
    private Long id;

    @Column(length = 100)
    private String creator;

    @Column
    @Size
    private Long size ;


}
