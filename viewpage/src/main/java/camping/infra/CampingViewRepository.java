package camping.infra;

import camping.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="campingViews", path="campingViews")
public interface CampingViewRepository extends PagingAndSortingRepository<CampingView, Long> {

    

    
}
