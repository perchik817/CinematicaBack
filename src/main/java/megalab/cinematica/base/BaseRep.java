package megalab.cinematica.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRep <E extends BaseEntity> extends JpaRepository<E, Long> {
//    @Query("SELECT e FROM E e WHERE e.id = :id")
//    E findByIdEnt(@Param("id") Long id);
}
