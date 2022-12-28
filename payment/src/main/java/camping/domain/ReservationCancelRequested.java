package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReservationCancelRequested extends AbstractEvent {

    private Long reservationId;
    private String reservationStatus;
    private Long payId;
    private Long campsiteId;
    private Date fromDate;
    private Date toDate;
}


