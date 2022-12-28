package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class CampsiteReserved extends AbstractEvent {

    private Long capsiteId;
    private String compsiteStatus;

    public CampsiteReserved(Campsite aggregate){
        super(aggregate);
    }
    public CampsiteReserved(){
        super();
    }
}
