package camping.domain;

import camping.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class ReservationCancelled extends AbstractEvent {

    private Long reservationId;
    private String reservationStatus;
    private Long payId;
    private Long campsiteId;
    private Date fromDate;
    private Date toDate;
    private Long customerId;
}
