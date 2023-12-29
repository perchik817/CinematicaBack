package megalab.cinematica.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_hall")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Hall extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @ManyToOne
    @JoinColumn(name = "id_cinema")
    Cinema cinema;
    int seatsCount;
}
