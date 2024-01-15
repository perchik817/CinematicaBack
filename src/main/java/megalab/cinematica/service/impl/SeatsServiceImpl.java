package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.SeatsRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.PlaceIsOccupied;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.SeatsMapper;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.entity.Seats;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.enums.SeatsStatus;
import megalab.cinematica.models.requests.SeatsCreateRequest;
import megalab.cinematica.models.responces.HallSeatsResponse;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.HallService;
import megalab.cinematica.service.SeatsService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatsServiceImpl extends BaseServiceImpl<Seats, SeatsRep, SeatsDto, SeatsMapper>
        implements SeatsService {
    protected SeatsServiceImpl(SeatsRep repo, SeatsMapper mapper, HallService hallService) {
        super(repo, mapper);
        this.hallService = hallService;
    }
    private final HallService hallService;
    private final Map<Long, List<Integer>> occupiedSeatsMap = new HashMap<>();
    @Override
    public Response create(SeatsCreateRequest request, Language language) {
        try {
            HallDto hall = hallService.findById(request.getHallId(), language);
            if (seatIsOccupied(request.getSeat(), hall.getId())) {
                throw new PlaceIsOccupied(ResourceBundle.periodMess("placeIsOccupied", language));
            } else {
                if (request.getSeat() > 0) {
                    SeatsDto seatsDto = new SeatsDto();
                    seatsDto.setSeat(request.getSeat());
                    seatsDto.setHall(hall);
                    save(seatsDto);
                    addOccupiedSeats(hall.getId(), request.getSeat());
                    return Response.getSuccessResponse(seatsDto, language);
                } else {
                    throw new NumException(ResourceBundle.periodMess("placeNumIsNegative", language));
                }
            }
        } catch (UnsavedDataException e) {
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

    @Override
    public HallSeatsResponse getHallSeats(Long id, Language lan) {
        HallDto hall = hallService.findById(id, lan);
        HallSeatsResponse hallSeatsResponse = new HallSeatsResponse();

        List<Integer> occupiedSeatsFromDB = repo.findOccupiedSeatsByHallIdAndStatus(id, SeatsStatus.OCCUPIED);

        occupiedSeatsMap.put(id, occupiedSeatsFromDB);

        hallSeatsResponse.setSeatsCount(parseSeatIdsListString(hall.getSeatsCount()));
        hallSeatsResponse.setOccupiedSeatsCount(occupiedSeatsFromDB);

        return hallSeatsResponse;
    }

    @Override
    public Response removeSeat(Long hallId, int selectedSeat, Language language) {
        try {
            List<Integer> occupiedSeats = occupiedSeatsMap.getOrDefault(hallId, new ArrayList<>());
            if (occupiedSeats.contains(selectedSeat)) {
                occupiedSeats.remove(Integer.valueOf(selectedSeat));

                SeatsDto seatsDto = findSeatByHallAndSeat(hallId, selectedSeat);
                if (seatsDto != null) {
                    seatsDto.setStatus(SeatsStatus.FREE);
                    update(seatsDto);

                    List<Integer> updatedOccupiedSeats = repo.findOccupiedSeatsByHallIdAndStatus(hallId, SeatsStatus.OCCUPIED);

                    occupiedSeatsMap.put(hallId, updatedOccupiedSeats);

                    return Response.getSuccessDelete("successDelete", language);
                } else {
                    throw new UnsavedDataException(ResourceBundle.periodMess("seatNotFound", language));
                }
            } else {
                throw new PlaceIsOccupied(ResourceBundle.periodMess("placeIsNotOccupied", language));
            }
        } catch (UnsavedDataException e) {
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

    private boolean seatIsOccupied(int seat, Long hallId) {
        List<Integer> occupiedSeats = occupiedSeatsMap.getOrDefault(hallId, new ArrayList<>());
        return occupiedSeats.contains(seat);
    }

    private SeatsDto findSeatByHallAndSeat(Long hallId, int seat) {
        return mapper.toDto(repo.findSeatByHallAndSeat(hallId, seat), context);
    }

    private void addOccupiedSeats(Long hallId, int selectedSeat) {
        List<Integer> occupiedSeats = occupiedSeatsMap.computeIfAbsent(hallId, k -> new ArrayList<>());
        occupiedSeats.add(selectedSeat);
    }

    private List<List<Integer>> parseSeatIdsListString(String seatIdsListString) {
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

}
