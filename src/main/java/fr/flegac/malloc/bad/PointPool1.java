package fr.flegac.malloc.bad;

import java.util.BitSet;
import fr.flegac.malloc.Point;
import fr.flegac.malloc.PointPool;

public class PointPool1 implements PointPool {
  private final Point[] points;
  private final BitSet usage;

  public PointPool1(final int size) {
    usage = new BitSet(size);
    points = new Point[size];
    for (int i = 0; i < size; i++) {
      points[i] = new Point1();
    }
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
    return points[offset];
  }

}
