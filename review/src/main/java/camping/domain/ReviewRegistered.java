package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class ReviewRegistered extends AbstractEvent {

    private Long reviewId;
    private Long campsiteId;
    private String reviewContents;
    private Long customerId;

    public ReviewRegistered(Review aggregate){
        super(aggregate);
    }
    public ReviewRegistered(){
        super();
    }
}
