package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class CampsiteCancelled extends AbstractEvent {

    private Long capsiteId;
    private String compsiteStatus;

    public CampsiteCancelled(Campsite aggregate){
        super(aggregate);
    }
    public CampsiteCancelled(){
        super();
    }
}
