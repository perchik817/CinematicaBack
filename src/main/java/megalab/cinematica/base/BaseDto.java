package megalab.cinematica.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseDto {
    protected Long id;
    protected Date addDate;
    protected Date updateDate;
    protected boolean active;
}
