package camping.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import camping.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import camping.domain.*;

@Service
@Transactional
public class PolicyHandler{
    @Autowired MileageRepository mileageRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PaymentApproved'")
    public void wheneverPaymentApproved_SaveMileage(@Payload PaymentApproved paymentApproved){

        PaymentApproved event = paymentApproved;
        System.out.println("\n\n##### listener SaveMileage : " + paymentApproved + "\n\n");


        

        // Sample Logic //
        Mileage.saveMileage(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReviewRegistered'")
    public void wheneverReviewRegistered_SaveMileage(@Payload ReviewRegistered reviewRegistered){

        ReviewRegistered event = reviewRegistered;
        System.out.println("\n\n##### listener SaveMileage : " + reviewRegistered + "\n\n");


        

        // Sample Logic //
        Mileage.saveMileage(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PaymentCancelled'")
    public void wheneverPaymentCancelled_DeleteMileage(@Payload PaymentCancelled paymentCancelled){

        PaymentCancelled event = paymentCancelled;
        System.out.println("\n\n##### listener DeleteMileage : " + paymentCancelled + "\n\n");


        

        // Sample Logic //
        Mileage.deleteMileage(event);
        

        

    }

}


