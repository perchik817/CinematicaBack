package megalab.cinematica.base;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto {
    protected Long id;
    protected Date addDate;
    protected Date updateDate;
    protected boolean active;
}
