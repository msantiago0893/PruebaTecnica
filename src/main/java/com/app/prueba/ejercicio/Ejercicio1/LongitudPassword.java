package com.app.prueba.ejercicio.Ejercicio1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongitudPassword {
  private static final Logger log = LoggerFactory.getLogger(LongitudPassword.class);

  public static Integer passwordLength(String message) {
    if (message.length() < 1 || message.length() > 200) {
      log.error("Longitud de cadena no válida.");
      return -1;
    }

    if (!isPrintableAscii(message)) {
      log.error("La cadena contiene caracteres no ASCII imprimibles.");
      return -1;
    }

    return Arrays.stream(message.split("\\s+"))
      .filter(LongitudPassword::isValidPassword)
      .map(String::length)
      .max(Integer::compare)
      .orElse(-1);
  }

  private static boolean isPrintableAscii(String str) {
    return str.chars().allMatch(c -> c >= 32 && c < 127);
  }
  private static boolean isValidPassword(String password) {
    if (!isAlphanumeric(password)) {
      return false;
    }

    long letterCount = password.chars().filter(Character::isLetter).count();
    long digitCount = password.chars().filter(Character::isDigit).count();

    return letterCount % 2 == 0 && digitCount % 2 != 0;
  }

  private static boolean isAlphanumeric(String word) {
    Pattern alphanumericPattern = Pattern.compile("^[a-zA-Z0-9]+$");
    Matcher matcher = alphanumericPattern.matcher(word);
    return matcher.matches();
  }

  public static void main(String[] args) {

    String input = "test a0A pass007 ?xy1";
    int result = passwordLength(input);

    if (result != -1) {
      log.info("La longitud de la contraseña más larga es: {} ", result);
    } else {
      log.info("{}",result);
    }
  }
}
