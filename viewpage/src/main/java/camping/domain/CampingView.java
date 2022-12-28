package camping.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Entity
@Table(name="CampingView_table")
@Data
public class CampingView {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long campsiteId;
        private String campsiteDescription;
        private String campsiteStatus;
        private Long reservationId;
        private String reservationStatus;
        private Long payId;
        private String paymentStatus;


}
