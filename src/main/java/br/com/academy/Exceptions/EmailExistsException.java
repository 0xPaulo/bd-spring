package br.com.academy.Exceptions;

import java.util.Objects;

public class EmailExistsException extends Exception {

  public EmailExistsException(String message) {
    super(message);
  }

  private static final long serialVersionUID = 1L;

}
