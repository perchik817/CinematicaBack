package megalab.cinematica.models.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;
import megalab.cinematica.models.enums.Genre;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_films")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film extends BaseEntity {

    String name;
    String logo;
    String definition;
    @Enumerated(EnumType.STRING)
    Genre genre;
    String ageRestrictions;
    String format; //2d, 3d
    String duration;
}
