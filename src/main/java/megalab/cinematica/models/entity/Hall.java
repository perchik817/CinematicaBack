package megalab.cinematica.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "name", unique = true, nullable = false)
    String name;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cinema")
    Cinema cinema;
    @Column(nullable = false)
    String seatsCount;
}
