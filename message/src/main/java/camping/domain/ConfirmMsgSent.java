package camping.domain;

import camping.domain.*;
import camping.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class ConfirmMsgSent extends AbstractEvent {

    private Long messageId;
    private Long reservationId;
    private String messageContents;
    private String messageStatus;

    public ConfirmMsgSent(Message aggregate){
        super(aggregate);
    }
    public ConfirmMsgSent(){
        super();
    }
}
