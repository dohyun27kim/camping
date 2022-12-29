package camping.infra;

import camping.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "myPages", path = "myPages")
public interface MyPageRepository
    extends PagingAndSortingRepository<MyPage, Long> {
    List<MyPage> findByReservationId(Long reservationId);
    List<MyPage> findByCampsiteId(Long campsiteId);

    void deleteByReservationId(Long reservationId);
}