package fis.police.fis_police_server.service.exceptions;

public class CustomSearchException extends Exception {
    public CustomSearchException(String type, String message){
        super(type + message);
    }
}
