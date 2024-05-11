package org.vang.minimicroservice.exception;

public class UsernameExistException extends Exception {
    public UsernameExistException() {
        super("Username already exists");
    }
}
