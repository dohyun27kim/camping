package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class CampsiteDeleted extends AbstractEvent {

    private Long capsiteId;

    public CampsiteDeleted(Campsite aggregate){
        super(aggregate);
    }
    public CampsiteDeleted(){
        super();
    }
}
