package megalab.cinematica.models.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_session")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Session extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @ManyToOne
    @JoinColumn(name = "id_films")
    Film film;
    @ManyToOne
    @JoinColumn(name = "id_hall")
    Hall hall;
    Date dateTime;
    double price;
    double discount;
}
