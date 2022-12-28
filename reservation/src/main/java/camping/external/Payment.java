package camping.external;

import lombok.Data;
import java.util.Date;
@Data
public class Payment {

    private Long payId;
    private Long reservationId;
    private Long compsiteId;
    private String paymentStatus;
}


