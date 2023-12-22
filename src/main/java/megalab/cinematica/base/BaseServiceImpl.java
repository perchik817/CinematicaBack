package megalab.cinematica.base;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class BaseServiceImpl<E extends BaseEntity, R extends BaseRep<E>> implements BaseService<E> {
    protected final R r;

    public BaseServiceImpl(R r) {
        this.r = r;
    }

    @Override
    public E save(E e) {
        return r.save(e);
    }

    @Override
    public E findById(Long id) {
        return r.findById(id).orElseThrow(()-> new RuntimeException("mess"));
    }

    @Override
    public List<E> findAll() {
        return r.findAll();
    }

    @Override
    public E update(E e) {
        return r.saveAndFlush(e);
    }

    @Override
    public boolean delete(E e) {
        try{
            e.setActive(false);
            save(e);
            return true;
        }catch (Exception exception) {
            throw new RuntimeException("failed to delete an object");
        }
    }
}
