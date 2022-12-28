package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class CampsiteModified extends AbstractEvent {

    private Long campsiteId;
    private String campsiteStatus;
    private String campsiteDescription;
    private String location;
    private String description;

    public CampsiteModified(Campsite aggregate){
        super(aggregate);
    }
    public CampsiteModified(){
        super();
    }
}
