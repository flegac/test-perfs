package fr.flegac.dataoriented.object;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import fr.flegac.dataoriented.api.MyComponent;
import fr.flegac.dataoriented.api.MyEntity;
import fr.flegac.dataoriented.api.MyRepository;

public class Repository1 implements MyRepository {
  Map<Long, MyEntity> entities = new HashMap<>();

  @Override
  public Stream<MyEntity> all() {
    return entities.keySet().stream().map(entities::get);
  }

  @Override
  public Stream<MyComponent> components(final Class<? extends MyComponent> type) {
    return all()
        .map(e -> e.get(type))
        .filter(Optional::isPresent)
        .map(Optional::get);
  }

  @Override
  public void delete(final long id) {
    entities.remove(id);
  }

  @Override
  public Optional<MyEntity> get(final long id) {
    return Optional.ofNullable(entities.get(id));
  }

  @Override
  public void update(final MyEntity entity) {
    entities.put(entity.id(), entity);
  }

}
