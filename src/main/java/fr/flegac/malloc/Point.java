package fr.flegac.malloc;

public interface Point {
  int id();

  default double length() {
    final double x = x();
    final double y = y();
    final double z = z();
    return Math.sqrt(x * x + y * y + z * z);
  }

  void setid(int id);

  void setx(float x);

  void sety(float y);

  void setz(float z);

  float x();

  float y();

  float z();
}
