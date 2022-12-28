package camping.domain;

import camping.domain.MileageSaved;
import camping.domain.MileageDeleted;
import camping.MileageApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Mileage_table")
@Data

public class Mileage  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long mileageId;
    
    
    
    
    
    private Long customerId;
    
    
    
    
    
    private Long mileageCount;

    @PostPersist
    public void onPostPersist(){


        MileageSaved mileageSaved = new MileageSaved(this);
        mileageSaved.publishAfterCommit();



        MileageDeleted mileageDeleted = new MileageDeleted(this);
        mileageDeleted.publishAfterCommit();

    }

    public static MileageRepository repository(){
        MileageRepository mileageRepository = MileageApplication.applicationContext.getBean(MileageRepository.class);
        return mileageRepository;
    }




    public static void saveMileage(PaymentApproved paymentApproved){

        /** Example 1:  new item 
        Mileage mileage = new Mileage();
        repository().save(mileage);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(mileage->{
            
            mileage // do something
            repository().save(mileage);


         });
        */

        
    }
    public static void saveMileage(ReviewRegistered reviewRegistered){

        /** Example 1:  new item 
        Mileage mileage = new Mileage();
        repository().save(mileage);

        */

        /** Example 2:  finding and process
        
        repository().findById(reviewRegistered.get???()).ifPresent(mileage->{
            
            mileage // do something
            repository().save(mileage);


         });
        */

        
    }
    public static void deleteMileage(PaymentCancelled paymentCancelled){

        /** Example 1:  new item 
        Mileage mileage = new Mileage();
        repository().save(mileage);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCancelled.get???()).ifPresent(mileage->{
            
            mileage // do something
            repository().save(mileage);


         });
        */

        
    }


}
