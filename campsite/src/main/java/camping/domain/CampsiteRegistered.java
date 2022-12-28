package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class CampsiteRegistered extends AbstractEvent {

    private Long campsiteId;
    private String campsiteStatus;
    private String campsiteDescription;
    private String location;
    private String description;

    public CampsiteRegistered(Campsite aggregate){
        super(aggregate);
    }
    public CampsiteRegistered(){
        super();
    }
}
