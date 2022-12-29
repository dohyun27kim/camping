package camping.domain;

import camping.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class CampsiteModified extends AbstractEvent {

    private Long campsiteId;
    private String campsiteStatus;
    private String campsiteDescription;
    private String location;
    private String description;
}
