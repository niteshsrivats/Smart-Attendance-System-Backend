package attendance.system.central.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateEntityException extends RuntimeException {

    private final Class<?> entity;
    private final String conflictingId;

    public <T> DuplicateEntityException(Class<?> entity, T conflictingId) {
        super("Entity with id `" + conflictingId + "` already exists for type `" + entity.getSimpleName() + "`");
        this.conflictingId = String.valueOf(conflictingId);
        this.entity = entity;
    }

    public DuplicateEntityException(Class<?> entity, String conflictingId, String message) {
        super(message);
        this.conflictingId = conflictingId;
        this.entity = entity;
    }

    public Class<?> getEntity() {
        return entity;
    }

    public String getConflictingId() {
        return conflictingId;
    }
}
