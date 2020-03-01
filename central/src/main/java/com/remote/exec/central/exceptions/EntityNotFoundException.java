package com.remote.exec.central.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private final Class<?> entity;
    private final String entityId;

    public <T> EntityNotFoundException(Class<?> entity, T entityId) {
        super("Could not find entity of type `" + entity.getSimpleName() + "` with id `" + entityId + "`");
        this.entity = entity;
        this.entityId = String.valueOf(entityId);
    }

    public EntityNotFoundException(Class<?> entity, String entityId, String message) {
        super(message);
        this.entity = entity;
        this.entityId = entityId;
    }

    public Class<?> getEntity() {
        return entity;
    }

    public String getEntityId() {
        return entityId;
    }
}
