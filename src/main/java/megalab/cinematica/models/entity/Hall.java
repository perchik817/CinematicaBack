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
    @Column(unique = true, nullable = false)
    String name;
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cinema", nullable = false)
    Cinema cinema;
    @Column(nullable = false)
    int seatsCount;
}
