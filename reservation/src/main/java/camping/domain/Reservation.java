package camping.domain;

import camping.domain.ReservationCreated;
import camping.domain.ReservationCancelRequested;
import camping.domain.ReservationConfirmed;
import camping.domain.ReservationCancelled;
import camping.ReservationApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Reservation_table")
@Data

public class Reservation  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long reservationId;
    
    
    
    
    
    private String reservationStatus;
    
    
    
    
    
    private Long payId;
    
    
    
    
    
    private Long campsiteId;
    
    
    
    
    
    private Date fromDate;
    
    
    
    
    
    private Date toDate;
    
    
    
    
    
    private Long customerId;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


        camping.external.Payment payment = new camping.external.Payment();
        // mappings goes here
        payment.setCompsiteId(getCampsiteId());
        payment.setPayId(getPayId());
        payment.setPaymentStatus(getReservationStatus());
        payment.setReservationId(getReservationId());


        ReservationApplication.applicationContext.getBean(camping.external.PaymentService.class)
            .approvePayment(payment);


        ReservationCreated reservationCreated = new ReservationCreated(this);
        reservationCreated.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){


        ReservationCancelRequested reservationCancelRequested = new ReservationCancelRequested(this);
        reservationCancelRequested.publishAfterCommit();



        ReservationConfirmed reservationConfirmed = new ReservationConfirmed(this);
        reservationConfirmed.publishAfterCommit();



        ReservationCancelled reservationCancelled = new ReservationCancelled(this);
        reservationCancelled.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
    }

    public static ReservationRepository repository(){
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(ReservationRepository.class);
        return reservationRepository;
    }




    public static void confirmReserve(PaymentApproved paymentApproved){

        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);


         });
        */
        repository().findById(paymentApproved.getReservationId()).ifPresent(reservation->{
            
            //reservation // do something
            reservation.setPayId(paymentApproved.getPayId());
            reservation.setReservationStatus("?????????");
            repository().save(reservation);

         });

        
    }
    public static void confirmCancel(PaymentCancelled paymentCancelled){

        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCancelled.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);


         });
        */


        repository().findById(paymentCancelled.getReservationId()).ifPresent(reservation->{
            
            //reservation // do something
            reservation.setReservationId(paymentCancelled.getReservationId());  //???????????? !!
            reservation.setPayId(paymentCancelled.getPayId());  //???????????? !!
            reservation.setReservationStatus("?????????");
            repository().save(reservation);

         });        
        
    }


}
