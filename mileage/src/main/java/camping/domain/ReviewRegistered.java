package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReviewRegistered extends AbstractEvent {

    private Long reviewId;
    private Long campsiteId;
    private String reviewContents;
    private Long customerId;
}


