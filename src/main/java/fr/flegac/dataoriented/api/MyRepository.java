package fr.flegac.dataoriented.api;

import java.util.Optional;
import java.util.stream.Stream;

public interface MyRepository {
  Stream<MyEntity> all();

  Stream<MyComponent> components(Class<? extends MyComponent> type);

  void delete(long id);

  Optional<MyEntity> get(long id);

  void update(MyEntity entity);

}