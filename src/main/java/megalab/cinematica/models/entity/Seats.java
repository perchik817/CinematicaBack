package megalab.cinematica.models.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;
import megalab.cinematica.models.enums.SeatsStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_seats")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seats extends BaseEntity {

    int num;
    int row;
    @Enumerated(EnumType.STRING)
    SeatsStatus status;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_hall", nullable = false)
    Hall hall;
}
