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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order", nullable = false)
    Order order;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_session", nullable = false)
    Session session;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_seats", nullable = false)
    Seats seats;
    double price;
    int num;
}
