package ie.licenta.artassistant.utils;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractPersistent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @PrePersist
    private void initCreationDate() {
        this.creationDate = new Date();
    }
}
