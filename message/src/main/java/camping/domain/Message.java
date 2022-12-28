package camping.domain;

import camping.domain.ConfirmMsgSent;
import camping.domain.CancelMsgsent;
import camping.MessageApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Message_table")
@Data

public class Message  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long messageId;
    
    
    
    
    
    private Long reservationId;
    
    
    
    
    
    private String messageContents;
    
    
    
    
    
    private String messageStatus;

    @PostPersist
    public void onPostPersist(){


        ConfirmMsgSent confirmMsgSent = new ConfirmMsgSent(this);
        confirmMsgSent.publishAfterCommit();



        CancelMsgsent cancelMsgsent = new CancelMsgsent(this);
        cancelMsgsent.publishAfterCommit();

    }

    public static MessageRepository repository(){
        MessageRepository messageRepository = MessageApplication.applicationContext.getBean(MessageRepository.class);
        return messageRepository;
    }




    public static void sendConfirmMsg(ReservationConfirmed reservationConfirmed){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(reservationConfirmed.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

        
    }
    public static void sendCancelMsg(ReservationCancelled reservationCancelled){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(reservationCancelled.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

        
    }


}
