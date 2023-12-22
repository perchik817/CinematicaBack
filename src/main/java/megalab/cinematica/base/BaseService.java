package megalab.cinematica.base;

import java.util.List;

public interface BaseService <E extends BaseEntity>{
    E save(E e);
    E findById(Long id);
    List<E> findAll();
    E update(E e);
    boolean delete (E e);
}
