package megalab.cinematica.base;

import megalab.cinematica.exceptions.DeleteException;
import megalab.cinematica.exceptions.FindByIdException;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class BaseServiceImpl<E extends BaseEntity, R extends BaseRep<E>, D extends BaseDto, M extends BaseMapper<E, D>> implements BaseService<D> {
    protected final R repo;
    protected final M mapper;
    @Autowired
    protected CycleAvoidingMappingContext context;

    protected BaseServiceImpl(R repo, M mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public D save(D d) {
        return mapper.toDto(repo.save(mapper.toEntity(d, context)), context);
    }

    @Override
    public D findById(Long id, Language lang) {
        return mapper.toDto(repo.findById(id).orElseThrow(()-> new FindByIdException(ResourceBundle.periodMess("idNotFound", lang))), context);
    }

    @Override
    public List<D> findAll() {
        return mapper.toDtos(repo.findAll(), context);
    }

    @Override
    public D update(D d) {
        return mapper.toDto(repo.saveAndFlush(mapper.toEntity(d, context)), context);
    }

    @Override
    public boolean delete(D d, Language lang) {
        try{
            d.setActive(false);
            save(d);
            return true;
        }catch (Exception exception) {
            throw new DeleteException(ResourceBundle.periodMess("failedToDelete", lang));
        }
    }
}
