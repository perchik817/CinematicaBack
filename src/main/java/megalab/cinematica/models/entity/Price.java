package megalab.cinematica.models.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;
import megalab.cinematica.models.enums.Ticket;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_price")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price extends BaseEntity {

    double price;
    @Enumerated(EnumType.STRING)
    Ticket type;
}
