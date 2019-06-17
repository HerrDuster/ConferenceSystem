package com.training.duster.model.entity;
/**
 * The {@code role} enum represent {@link User} role
 */
public enum Role {
    MODERATOR,
    SPEAKER,
    USER;

    private long id;

    Role() {}

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
