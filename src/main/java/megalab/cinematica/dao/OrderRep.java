package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRep extends BaseRep<Order> {
    @Query("select od.num from OrderDetails od where od.num = :num")
    int findOrderDetailsByNum(@Param("num") int num);
}
