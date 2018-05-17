package fr.flegac.malloc.good;

import static fr.flegac.malloc.PointPool.X_OFFSET;
import static fr.flegac.malloc.PointPool.Y_OFFSET;
import static fr.flegac.malloc.PointPool.Z_OFFSET;
import fr.flegac.malloc.Point;

class Point2 implements Point {
  private PointPool2 pointPool;

  private int offset;

  @Override
  public int id() {
    return pointPool.id[offset];
  }

  @Override
  public void setid(final int i) {
    pointPool.id[offset] = i;
  }

  public Point setOffset(final PointPool2 pointPool, final int offset) {
    this.pointPool = pointPool;
    this.offset = offset;
    return this;
  }

  @Override
  public void setx(final float x) {
    pointPool.xyz[3 * offset + X_OFFSET] = x;
  }

  @Override
  public void sety(final float y) {
    pointPool.xyz[3 * offset + Y_OFFSET] = y;
  }

  @Override
  public void setz(final float z) {
    pointPool.xyz[3 * offset + Z_OFFSET] = z;
  }

  @Override
  public float x() {
    return pointPool.xyz[3 * offset + X_OFFSET];
  }

  @Override
  public float y() {
    return pointPool.xyz[3 * offset + Y_OFFSET];
  }

  @Override
  public float z() {
    return pointPool.xyz[3 * offset + Z_OFFSET];
  }

}