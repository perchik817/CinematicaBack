package megalab.cinematica.models.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order_details")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @ManyToOne
    @JoinColumn(name = "id_order")
    Order order;
    @ManyToOne
    @JoinColumn(name = "id_session")
    Session session;
    @ManyToOne
    @JoinColumn(name = "id_seats")
    Seats seats;
    double price;
    int num;
}
