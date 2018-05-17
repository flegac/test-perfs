package fr.flegac.dataoriented.api;

// Simplified version of component which take a component as input (in place of a full entity)
public interface MySystem<C extends MyComponent> {
  Class<C> type();

  void update(C c);
}