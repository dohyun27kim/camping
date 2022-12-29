package camping.infra;

import camping.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="myPages", path="myPages")
public interface MyPageRepository extends PagingAndSortingRepository<MyPage, Long> {

    List<MyPage> findByReservationId(Long reservationId);
    List<MyPage> findByPayId(Long payId);
    List<MyPage> findByCampsiteId(Long campsiteId);
    List<MyPage> deleteByReservationId(Long reservationId);

    


    
}
