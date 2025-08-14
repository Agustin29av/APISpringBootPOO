package com.uader.poo.exception;

// Esta clase extiende tu GenericException, lo que permite que la instanciemos
public class InvalidInputException extends GenericException {

    // Es importante para la serialización
    private static final long serialVersionUID = 1L;

    // Usamos un constructor que toma el enum de error y un mensaje
    public InvalidInputException(String message) {
        // Llama al constructor de la clase padre (GenericException) y le pasa el enum
        super(ErrorEnum.DATO_SIN_VALOR_INGRESADO);
    }
}
