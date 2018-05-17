package fr.flegac.dataoriented.api.impl;

import fr.flegac.Util;
import fr.flegac.dataoriented.api.MySystem;

public class S1 implements MySystem<C1> {

  @Override
  public Class<C1> type() {
    return C1.class;
  }

  @Override
  public void update(final C1 c) {
    Util.log(getClass().getSimpleName() + ":" + c.id);
    for (int i = 0; i < c.position.length; i++) {
      c.position[i] += i;
    }
  }

}