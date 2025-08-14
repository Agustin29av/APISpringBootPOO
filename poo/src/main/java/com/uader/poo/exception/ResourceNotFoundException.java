package com.uader.poo.exception;

// Esta clase extiende tu GenericException
public class ResourceNotFoundException extends GenericException {

    // El serialVersionUID es importante para la serialización de la clase
    private static final long serialVersionUID = 1L;

    // Usamos el constructor de la clase padre (GenericException)
    // para pasarle el enum de error y un mensaje
    public ResourceNotFoundException(String message) {
        // En tu ErrorEnum.java, el más cercano a "no encontrado" es el 1002 (dato sin valor ingresado),
        // aunque lo ideal sería tener un enum específico para este caso.
        // Podríamos crear uno nuevo si quieres, por ahora usaremos este como ejemplo.
        super(ErrorEnum.DATO_SIN_VALOR_INGRESADO);
    }
}
