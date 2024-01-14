package megalab.cinematica.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass

public abstract class BaseEntity {

    protected Date addDate;
    protected Date updateDate;
    protected boolean active;

    @PrePersist
    protected void onCreate(){
        addDate = new Date();
        updateDate = new Date();
        active = true;
    }

    @PreUpdate
    protected void onUpdate(){
        updateDate = new Date();
    }
}
