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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    MultipartFile logo;
    String definition;
    @Enumerated(EnumType.STRING)
    Genre genre;
    String format;
    Duration duration;
}
