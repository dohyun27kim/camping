package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class MileageDeleted extends AbstractEvent {

    private Long mileageId;
    private Long customerId;
    private Long mileageCount;

    public MileageDeleted(Mileage aggregate){
        super(aggregate);
    }
    public MileageDeleted(){
        super();
    }
}
