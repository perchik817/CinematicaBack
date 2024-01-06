package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.enums.Ticket;
import megalab.cinematica.models.responces.Response;

public interface PriceService extends BaseService<PriceDto> {
    Response create(double price, Ticket type, Language language);
}
