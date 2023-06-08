package iut.info1.sdd;

/**
 * Exception levée en cas de pile vide.
 */
public class PileVideException extends RuntimeException {

    /** 
     * @see {@code RuntimeException#RuntimeException()}
     */
    public PileVideException() {
        super();
    }

    /** 
     * @see {@code RuntimeException#RuntimeException(String))}
     * @param string le message d'erreur
     */
    public PileVideException(String string) {
        super(string);
    }


}