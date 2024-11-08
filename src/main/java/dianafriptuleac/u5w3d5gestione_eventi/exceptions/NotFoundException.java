package dianafriptuleac.u5w3d5gestione_eventi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long id) {
        super("Il record con id: " + id + " non è stato trovato!");
    }
}
