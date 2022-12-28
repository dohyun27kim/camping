package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class CancelMsgsent extends AbstractEvent {

    private Long messageId;
    private Long reservationId;
    private String messageContents;
    private String messageStatus;

    public CancelMsgsent(Message aggregate){
        super(aggregate);
    }
    public CancelMsgsent(){
        super();
    }
}
