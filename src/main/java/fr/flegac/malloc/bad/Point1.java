package fr.flegac.malloc.bad;

import static fr.flegac.malloc.PointPool.X_OFFSET;
import static fr.flegac.malloc.PointPool.Y_OFFSET;
import static fr.flegac.malloc.PointPool.Z_OFFSET;
import fr.flegac.malloc.Point;

public class Point1 implements Point {
  private int id;
  private final float[] xyz = new float[3];

  @Override
  public int id() {
    return id;
  }

  @Override
  public void setid(final int id) {
    this.id = id;
  }

  @Override
  public void setx(final float x) {
    this.xyz[X_OFFSET] = x;
  }

  @Override
  public void sety(final float y) {
    this.xyz[Y_OFFSET] = y;
  }

  @Override
  public void setz(final float z) {
    this.xyz[Z_OFFSET] = z;
  }

  @Override
  public float x() {
    return xyz[X_OFFSET];
  }

  @Override
  public float y() {
    return xyz[Y_OFFSET];
  }

  @Override
  public float z() {
    return xyz[Z_OFFSET];
  }

}
