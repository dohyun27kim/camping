package camping.infra;

import camping.config.kafka.KafkaProcessor;
import camping.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyPageViewHandler {

    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCreated_then_CREATE_1(
        @Payload ReservationCreated reservationCreated
    ) {
        try {
            if (!reservationCreated.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setReservationId(reservationCreated.getReservationId());
            myPage.setCustomerId(reservationCreated.getCustomerId());
            myPage.setCampsiteId(reservationCreated.getCampsiteId());
            myPage.setReservationStatus(
                reservationCreated.getReservationStatus()
            );
            // view 레파지 토리에 save
            myPageRepository.save(myPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationConfirmed_then_UPDATE_1(
        @Payload ReservationConfirmed reservationConfirmed
    ) {
        try {
            if (!reservationConfirmed.validate()) return;
            // view 객체 조회

            List<MyPage> myPageList = myPageRepository.findByReservationId(
                reservationConfirmed.getReservationId()
            );
            for (MyPage myPage : myPageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setReservationStatus(
                    reservationConfirmed.getReservationStatus()
                );
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCampsiteRegistered_then_UPDATE_2(
        @Payload CampsiteRegistered campsiteRegistered
    ) {
        try {
            if (!campsiteRegistered.validate()) return;
            // view 객체 조회

            List<MyPage> myPageList = myPageRepository.findByCampsiteId(
                campsiteRegistered.getCampsiteId()
            );
            for (MyPage myPage : myPageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setCampsiteStatus(
                    campsiteRegistered.getCampsiteStatus()
                );
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_3(
        @Payload PaymentApproved paymentApproved
    ) {
        try {
            if (!paymentApproved.validate()) return;
            // view 객체 조회

            List<MyPage> myPageList = myPageRepository.findByPayId(
                paymentApproved.getPayId()
            );
            for (MyPage myPage : myPageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setPayId(paymentApproved.getPayId());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCancelled_then_DELETE_1(
        @Payload ReservationCancelled reservationCancelled
    ) {
        try {
            if (!reservationCancelled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            myPageRepository.deleteByReservationId(
                reservationCancelled.getReservationId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
