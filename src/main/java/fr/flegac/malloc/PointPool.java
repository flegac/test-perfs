package fr.flegac.malloc;

public interface PointPool {
  int X_OFFSET = 0;

  int Y_OFFSET = 1;

  int Z_OFFSET = 2;

  Point allocate();

  void free(Point point);

  Point get(int offset);
}