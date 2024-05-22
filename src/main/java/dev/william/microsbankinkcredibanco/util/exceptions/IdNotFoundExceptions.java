package dev.william.microsbankinkcredibanco.util.exceptions;

public class IdNotFoundExceptions extends RuntimeException{


    private static final String ERROR_MESSAGE = "Record no exist in %s";

    public IdNotFoundExceptions(String tableName){
        super(String.format(ERROR_MESSAGE,tableName));

    }
}