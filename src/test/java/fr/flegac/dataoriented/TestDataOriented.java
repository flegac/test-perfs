package fr.flegac.dataoriented;

import org.junit.Test;
import fr.flegac.dataoriented.api.MySystem;
import fr.flegac.dataoriented.api.impl.C1;
import fr.flegac.dataoriented.api.impl.C2;
import fr.flegac.dataoriented.api.impl.C3;
import fr.flegac.dataoriented.api.impl.S1;
import fr.flegac.dataoriented.api.impl.S2;
import fr.flegac.dataoriented.api.impl.S3;
import fr.flegac.dataoriented.dataoriented.Repository2;
import fr.flegac.dataoriented.object.Repository1;

public class TestDataOriented {
  public static int ITEM_NUMBER = 5 * 1000 * 1000;
  public static final int UPDATE_ITERATIONS = 1;

  public static Class<?>[] COMPONENTS = { C1.class, C2.class, C3.class };

  public static MySystem<?>[] SYSTEMS = {
    new S1(), new S2(), new S3()
  };

  @Test
  public void dataOriented() throws Exception {
    System.out.println("data oriented");
    new Application(new Repository2(), SYSTEMS, COMPONENTS).run(ITEM_NUMBER, UPDATE_ITERATIONS);
    System.out.println();
  }

  @Test
  public void object() throws Exception {
    System.out.println("object");
    new Application(new Repository1(), SYSTEMS, COMPONENTS).run(ITEM_NUMBER, UPDATE_ITERATIONS);
    System.out.println();
  }
}
