package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class PaymentCancelled extends AbstractEvent {

    private Long payId;
    private Long reservationId;
    private Long compsiteId;
    private String paymentStatus;
}


