package megalab.cinematica.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "tb_order")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Order extends BaseEntity {

    double totalPrice;
    int num;
}
