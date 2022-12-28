package camping.domain;

import camping.domain.ReviewRegistered;
import camping.domain.ReviewDeleted;
import camping.ReviewApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Review_table")
@Data

public class Review  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long reviewId;
    
    
    
    
    
    private Long campsiteId;
    
    
    
    
    
    private String reviewContents;
    
    
    
    
    
    private Long customerId;

    @PostPersist
    public void onPostPersist(){


        ReviewRegistered reviewRegistered = new ReviewRegistered(this);
        reviewRegistered.publishAfterCommit();



        ReviewDeleted reviewDeleted = new ReviewDeleted(this);
        reviewDeleted.publishAfterCommit();

    }

    public static ReviewRepository repository(){
        ReviewRepository reviewRepository = ReviewApplication.applicationContext.getBean(ReviewRepository.class);
        return reviewRepository;
    }






}
