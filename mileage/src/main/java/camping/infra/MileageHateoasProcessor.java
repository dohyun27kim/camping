package camping.infra;
import camping.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class MileageHateoasProcessor implements RepresentationModelProcessor<EntityModel<Mileage>>  {

    @Override
    public EntityModel<Mileage> process(EntityModel<Mileage> model) {

        
        return model;
    }
    
}
