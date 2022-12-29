package camping.domain;

import camping.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class CampsiteRegistered extends AbstractEvent {

    private Long campsiteId;
    private String campsiteStatus;
    private String campsiteDescription;
    private String location;
    private String description;
}
