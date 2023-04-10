package vrs.backend.demo.entities.audit;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import vrs.backend.demo.config.CustomRevisionListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Revision_Info")
@RevisionEntity(CustomRevisionListener.class)
@Data
public class Revision implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "revision_seq")
    @SequenceGenerator(name="revision_seq",sequenceName = "rbac.seq_rev_id")
    @RevisionNumber
    private int id;
    @Column(name="REVISION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @RevisionTimestamp
    private Date date;

}
