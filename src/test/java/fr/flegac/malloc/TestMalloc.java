package fr.flegac.malloc;

import org.junit.Test;
import fr.flegac.Util;
import fr.flegac.malloc.bad.PointPool1;
import fr.flegac.malloc.good.PointPool2;

public class TestMalloc {
  public static final int ITEM_NUMBER = 5 * 1000 * 1000;
  public static final int UPDATE_ITERATIONS = 50;

  private PointPool pointpool;

  @Test
  public void bad() throws Exception {
    System.out.println("bad");
    Util.eval("allocating DataPool", () -> {
      pointpool = new PointPool1(ITEM_NUMBER);
    });
    testProcess();
  }

  @Test
  public void good() throws Exception {
    System.out.println("good");
    Util.eval("allocating DataPool", () -> {
      pointpool = new PointPool2(ITEM_NUMBER);
    });
    testProcess();
  }

  private void testProcess() {
    Util.eval("process Data objects", () -> {
      float sum = 0;
      for (int k = 0; k < UPDATE_ITERATIONS; k++) {
        for (int i = 0; i < ITEM_NUMBER; i++) {
          final Point point = pointpool.get(i);
          point.setid(i);
          point.setx(.01f * i * 2);
          sum += point.x() + point.id() + point.length();
        }
      }
      System.out.println("result = " + sum);
    });
    System.out.println();
  }

}
