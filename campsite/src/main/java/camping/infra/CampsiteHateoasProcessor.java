package camping.infra;
import camping.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class CampsiteHateoasProcessor implements RepresentationModelProcessor<EntityModel<Campsite>>  {

    @Override
    public EntityModel<Campsite> process(EntityModel<Campsite> model) {

        
        return model;
    }
    
}
