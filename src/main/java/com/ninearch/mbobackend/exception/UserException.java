package com.ninearch.mbobackend.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user." + code);
    }

    //user.register.mail

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    public static UserException notFound() {
        return new UserException("notfound");
    }

    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createEmailExits() {
        return new UserException("create.email.exits");
    }
    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createRolesNull() {
        return new UserException("create.roles.null");
    }
    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }

    public static UserException loginFailPasswordIncorrect() { return new UserException("login.password.incorrect"); }

    public static UserException unauthorized() { return new UserException("login.unauthorized"); }


    //DATA
    public static UserException idNotFound() { return new UserException("user.notfound.id"); }
    public static UserException emailNotFound() { return new UserException("user.notfound.email"); }
    public static UserException nameNotFound() { return new UserException("user.notfound.name"); }
    public static UserException rolesNotFound() { return new UserException("user.notfound.roles"); }

}
