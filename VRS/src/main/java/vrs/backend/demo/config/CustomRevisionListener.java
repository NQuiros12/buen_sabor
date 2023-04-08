package vrs.backend.demo.config;

import org.hibernate.envers.RevisionListener;
import vrs.backend.demo.entities.audit.Revision;

public class CustomRevisionListener implements RevisionListener {
    public void newRevision(Object revisionEntity){
        final Revision revision = (Revision) revisionEntity;
    }
}
