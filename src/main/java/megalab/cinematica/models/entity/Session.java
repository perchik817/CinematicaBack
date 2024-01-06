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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_films", nullable = false)
    Film film;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hall", nullable = false)
    Hall hall;
    Date dateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_price", nullable = false)
    Price price;
    double discount;
}
