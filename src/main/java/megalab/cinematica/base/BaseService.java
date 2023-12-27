package megalab.cinematica.base;

import megalab.cinematica.models.enums.Language;

import java.util.List;

public interface BaseService <D extends BaseDto>{
    D save(D d);
    D findById(Long id, Language lang);
    List<D> findAll();
    D update(D d);
    boolean delete (D d, Language lang);
}
