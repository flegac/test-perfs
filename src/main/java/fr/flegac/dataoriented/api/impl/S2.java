package fr.flegac.dataoriented.api.impl;

import fr.flegac.Util;
import fr.flegac.dataoriented.api.MySystem;

public class S2 implements MySystem<C2> {

  @Override
  public Class<C2> type() {
    return C2.class;
  }

  @Override
  public void update(final C2 c) {
    Util.log(getClass().getSimpleName() + ":" + c.id);
    final String[] split = c.name.split("o");
    c.name = String.join("o", split);
  }
}