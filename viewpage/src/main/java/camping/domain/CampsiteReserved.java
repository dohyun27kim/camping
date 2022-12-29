package camping.domain;

import camping.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class CampsiteReserved extends AbstractEvent {

    private Long capsiteId;
    private String compsiteStatus;
}
