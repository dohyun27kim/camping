package camping.domain;

import camping.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class CampsiteDeleted extends AbstractEvent {

    private Long capsiteId;
}
