package fr.flegac;

import java.util.function.Consumer;

public class Util {
  public static final int NANO_TO_MS = 1000000;

  public static void eval(final String label, final int n, final Consumer<Integer> action) {
    System.out.println(label + ":");
    final long start = System.nanoTime();
    for (int i = 0; i < n; i++) {
      action.accept(i);
    }
    final long time = System.nanoTime() - start;
    System.out.println(" -> time = " + time / NANO_TO_MS + "ms");
  }

  public static void eval(final String label, final Runnable action) {
    eval(label, 1, (i) -> action.run());
  }

  public static void log(final String s) {
    // System.err.println(s);
  }
}
