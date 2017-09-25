package com.ingeniouscontraptions.octopainting.engine;

public class PrintException extends Exception {

    public PrintException() {
    }

    public PrintException(String message) {
        super(message);
    }

    public PrintException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrintException(Throwable cause) {
        super(cause);
    }

}
