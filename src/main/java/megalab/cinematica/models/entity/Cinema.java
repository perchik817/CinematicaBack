package megalab.cinematica.models.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "tb_cinema")
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Cinema extends BaseEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    Long id;
    MultipartFile logo;
    @Column(unique = true)
    String name;
    String definition;
    String address;
}
