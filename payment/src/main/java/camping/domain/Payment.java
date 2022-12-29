package camping.domain;

import camping.domain.PaymentApproved;
import camping.domain.PaymentCancelled;
import camping.PaymentApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Payment_table")
@Data

public class Payment  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long payId;
    
    
    
    
    
    private Long reservationId;
    
    
    
    
    
    private Long compsiteId;
    
    
    
    
    
    private String paymentStatus;

    @PostPersist
    public void onPostPersist(){


        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.publishAfterCommit();



        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        paymentCancelled.publishAfterCommit();

    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }




    public static void cancelPayment(ReservationCancelRequested reservationCancelRequested){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(reservationCancelRequested.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        repository().findById(reservationCancelRequested.getPayId()).ifPresent(payment->{
            
            // do something
            payment.setPayId(reservationCancelRequested.getPayId());
            payment.setPaymentStatus("결제취소");
            repository().save(payment);


         });

        
    }


}
