package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.SeatsRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.PlaceIsOccupied;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.SeatsMapper;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.entity.Seats;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SeatsCreateRequest;
import megalab.cinematica.models.responces.HallSeatsResponse;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.HallService;
import megalab.cinematica.service.SeatsService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatsServiceImpl extends BaseServiceImpl<Seats, SeatsRep, SeatsDto, SeatsMapper>
        implements SeatsService {
    protected SeatsServiceImpl(SeatsRep repo, SeatsMapper mapper, HallService hallService) {
        super(repo, mapper);
        this.hallService = hallService;
    }
    private final HallService hallService;

    @Override
    public Response create(SeatsCreateRequest request, Language language) {
        try {
            HallDto hall = hallService.findById(request.getHallId(), language);
            if(seatIsOccupied(request.getSeat())){
                throw new PlaceIsOccupied(ResourceBundle.periodMess("placeIsOccupied", language));
            }else {
                if (request.getSeat() > 0) {
                    SeatsDto seatsDto = new SeatsDto();
                    seatsDto.setSeat(request.getSeat());
                    seatsDto.setHall(hall);
                    save(seatsDto);
                    String freeSeatsCount = removeSeat(parseSeatIdsListString(hall.getSeatsCount()),
                            request.getSeat()).toString();
                    hall.setFreeSeatsCount(freeSeatsCount);
                    hallService.update(hall);
                    System.out.println(hall);
                    return Response.getSuccessResponse(seatsDto, language);
                } else {
                    throw new NumException(ResourceBundle.periodMess("placeNumIsNegative", language));
                }
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

    private boolean seatIsOccupied(int seat){
        List<SeatsDto> seats = findAll();
        boolean occupied = false;
        for (SeatsDto seatsDto : seats) {
            if (seatsDto.getSeat() == seat){
                occupied = true;
            }
        }
        return occupied;
    }

    @Override
    public HallSeatsResponse getHallSeats(Long id, Language lan) {
        HallDto hall = hallService.findById(id, lan);
        HallSeatsResponse hallSeatsResponse = new HallSeatsResponse();
        hallSeatsResponse.setSeatsCount(parseSeatIdsListString(hall.getSeatsCount()));
        hallSeatsResponse.setFreeSeats(parseSeatIdsListString(hall.getFreeSeatsCount()));

        return hallSeatsResponse;
    }

    private static List<List<Integer>> parseSeatIdsListString(String seatIdsListString) {
        List<List<Integer>> seatIdsList = new ArrayList<>();

        if (seatIdsListString.trim().isEmpty()) return seatIdsList;

        String[] rowStrings = seatIdsListString.replaceAll("\\[", "").split("\\],");

        for (String rowString : rowStrings) {
            String[] seatStrings = rowString.split(",");
            List<Integer> rowSeatIds = new ArrayList<>();

            for (String seatString : seatStrings) {
                if (!seatString.trim().isEmpty()) {
                    seatString = seatString.replaceAll("\\]", "");
                    rowSeatIds.add(Integer.parseInt(seatString.trim()));
                }
            }

            seatIdsList.add(rowSeatIds);
        }

        return seatIdsList;
    }


    private static List<List<Integer>> removeSeat(List<List<Integer>> seatIdsList, int selectedSeat) {
        for (List<Integer> row : seatIdsList) {
            if (row.contains(selectedSeat)) {
                row.remove(Integer.valueOf(selectedSeat));
                break;
            }
        }

        return seatIdsList;
    }

}
