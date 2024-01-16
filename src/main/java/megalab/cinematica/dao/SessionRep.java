package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRep extends BaseRep<Session> {

}
