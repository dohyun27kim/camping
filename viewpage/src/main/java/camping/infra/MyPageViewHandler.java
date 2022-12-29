package camping.infra;

import camping.domain.*;
import camping.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {

    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCreated_then_CREATE_1 (@Payload ReservationCreated reservationCreated) {
        try {

            if (!reservationCreated.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setReservationId(reservationCreated.getReservationId());
            myPage.setCustomerId(reservationCreated.getCustomerId());
            myPage.setCampsiteId(reservationCreated.getReservationId());
            myPage.setReservationStatus("요청됨");
            // view 레파지 토리에 save
            myPageRepository.save(myPage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCampsiteModified_then_UPDATE_1(@Payload CampsiteModified campsiteModified) {
        try {
            if (!campsiteModified.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findByCampsiteId(campsiteModified.getCampsiteId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setCampsiteStatus(campsiteModified.getCampsiteStatus());    
                myPage.setCampsiteDescription(campsiteModified.getCampsiteDescription());    
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationConfirmed_then_UPDATE_2(@Payload ReservationConfirmed reservationConfirmed) {
        try {
            if (!reservationConfirmed.validate()) return;
                // view 객체 조회

                List<MyPage> myPageList = myPageRepository.findByReservationId(reservationConfirmed.getReservationId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReservationStatus("확정됨");
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCampsiteDeleted_then_DELETE_1(@Payload CampsiteDeleted campsiteDeleted) {
        try {
            if (!campsiteDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            myPageRepository.deleteById(campsiteDeleted.getCapsiteId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

