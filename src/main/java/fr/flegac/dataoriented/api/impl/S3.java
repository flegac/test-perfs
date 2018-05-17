package fr.flegac.dataoriented.api.impl;

import fr.flegac.Util;
import fr.flegac.dataoriented.api.MySystem;

public class S3 implements MySystem<C3> {

  @Override
  public Class<C3> type() {
    return C3.class;
  }

  @Override
  public void update(final C3 c) {
    Util.log(getClass().getSimpleName() + ":" + c.id);
    c.entityRef = c.id;
  }
}