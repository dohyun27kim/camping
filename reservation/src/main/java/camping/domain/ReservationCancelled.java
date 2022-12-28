package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class ReservationCancelled extends AbstractEvent {

    private Long reservationId;
    private String reservationStatus;
    private Long payId;
    private Long campsiteId;
    private Date fromDate;
    private Date toDate;

    public ReservationCancelled(Reservation aggregate){
        super(aggregate);
    }
    public ReservationCancelled(){
        super();
    }
}
