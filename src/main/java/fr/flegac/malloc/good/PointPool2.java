package fr.flegac.malloc.good;

import java.util.BitSet;
import fr.flegac.malloc.Point;
import fr.flegac.malloc.PointPool;

public class PointPool2 implements PointPool {
  protected int[] id;

  protected float[] xyz;

  protected BitSet usage;

  // NOT THREAD SAFE !
  private final Point2 _point = new Point2();

  public PointPool2(final int size) {
    id = new int[size];
    xyz = new float[3 * size];
    usage = new BitSet(size);
  }

  @Override
  public Point allocate() {
    return get(usage.nextClearBit(0));
  }

  @Override
  public void free(final Point point) {
    usage.clear(point.id());
  }

  @Override
  public Point get(final int offset) {
    // NOT THREAD SAFE !
    return _point.setOffset(this, offset);

    // THREAD SAFE VERSION
    // return new Point2().setOffset(this, offset);
  }
}
