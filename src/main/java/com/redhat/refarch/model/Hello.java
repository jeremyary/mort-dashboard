package com.redhat.refarch.model;

/***
 * @author jary
 * @since Nov/03/2016
 */
public class Hello {
    private final String message;

    public Hello(final String world) {
        this.message = world;
    }

    public String getMessage() {
        return this.message;
    }
}
