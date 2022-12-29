package camping.domain;

import camping.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class PaymentApproved extends AbstractEvent {

    private Long payId;
    private Long reservationId;
    private Long compsiteId;
    private String paymentStatus;
    private Long customerId;
}
