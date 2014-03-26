package entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class JunctionRequest {
    @Id @GeneratedValue
    private int id;
    private int index;
    private String response;
    private String foes;
    private boolean cont;

    @ManyToOne
    private Junction junction;
}
