package fr.flegac.dataoriented.api;

import java.util.Collection;
import java.util.Optional;

public interface MyEntity {
  Collection<MyComponent> components();

  <C extends MyComponent> Optional<C> get(final Class<C> type);

  long id();

  <C extends MyComponent> void set(C component);

}