package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class MileageSaved extends AbstractEvent {

    private Long mileageId;
    private Long customerId;
    private Long mileageCount;

    public MileageSaved(Mileage aggregate){
        super(aggregate);
    }
    public MileageSaved(){
        super();
    }
}
