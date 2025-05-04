package com.example.aurora.support.helper;

public class Helper {
  @SafeVarargs
  public static <T> T firstOf(T... args) {
    for (T arg : args) {
      if (arg != null)
        return arg;
    }

    return null;
  }
}
