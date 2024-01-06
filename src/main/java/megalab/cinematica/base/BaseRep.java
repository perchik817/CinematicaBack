package megalab.cinematica.base;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRep <E extends BaseEntity> extends JpaRepository<E, Long> {
    @Override
    Optional<E> findById(Long aLong);

}
