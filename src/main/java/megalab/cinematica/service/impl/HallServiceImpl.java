package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.HallRep;
import megalab.cinematica.exceptions.FindByIdException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.HallMapper;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.entity.Hall;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.HallCreateRequest;
import megalab.cinematica.models.responces.HallSeatsResponse;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.CinemaService;
import megalab.cinematica.service.HallService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class HallServiceImpl extends BaseServiceImpl<Hall, HallRep, HallDto, HallMapper> implements HallService {
    protected HallServiceImpl(HallRep repo, HallMapper mapper, CinemaService cinemaService) {
        super(repo, mapper);
        this.cinemaService = cinemaService;
    }

    private final CinemaService cinemaService;

    @Override
    public Response create(HallCreateRequest request, Language lang) {
        try{
            CinemaDto cinema = cinemaService.findById(request.getCinemaId(), lang);

            if (Objects.nonNull(cinema)){
                if(!repo.existsByName(request.getName())){
                    HallDto hallDto = new HallDto();
                    hallDto.setName(request.getName());
                    hallDto.setCinema(cinema);
                    String seatsCount = seatsCount(request.getSeatsCount(), request.getRowsCount());
                    hallDto.setSeatsCount(seatsCount);
                    hallDto.setFreeSeatsCount(seatsCount);

                    save(hallDto);

                    return Response.getSuccessResponse(hallDto, lang);
                } else{
                    return Response.getUniqueFieldResponse("notUniqueName", lang);
                }
            }else{
                throw new FindByIdException(ResourceBundle.periodMess("idNotFound", lang));
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", lang));
        }
    }

    private static String seatsCount(int totalSeats, int rows) {
        List<List<Integer>> seatIdsList = new ArrayList<>();
        int currentSeatId = 1;

        for (int row = 0; row < rows; row++) {
            List<Integer> rowSeatIds = new ArrayList<>();
            for (int seatCount = 0; seatCount < totalSeats / rows; seatCount++) {
                rowSeatIds.add(currentSeatId++);
            }
            seatIdsList.add(rowSeatIds);
        }

        // Распределение оставшихся мест, если totalSeats не делится нацело на rows
        int remainingSeats = totalSeats % rows;
        for (int i = 0; i < remainingSeats; i++) {
            seatIdsList.get(i).add(currentSeatId++);
        }

        String seatIdsListString = seatIdsList.toString();
        seatIdsListString = seatIdsListString.substring(0, seatIdsListString.length() - 1);

        return seatIdsListString;
    }

}
