package fr.flegac.dataoriented.dataoriented;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.flegac.dataoriented.api.MyComponent;
import fr.flegac.dataoriented.api.MyEntity;
import fr.flegac.dataoriented.api.MyRepository;
import fr.flegac.dataoriented.object.MyEntity1;

public class Repository2 implements MyRepository {
  private final Map<Class<?>, MyTable<? extends MyComponent>> tables = new HashMap<>();

  @Override
  public Stream<MyEntity> all() {
    final Set<Long> ids = tables.values().stream()
        .flatMap(MyTable::all)
        .map(c -> c.id)
        .collect(Collectors.toSet());

    return ids.stream()
        .map(this::get)
        .filter(Optional::isPresent)
        .map(Optional::get);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stream<MyComponent> components(final Class type) {
    return table(type).all();
  }

  @Override
  public void delete(final long id) {
    tables.values().forEach(table -> table.delete(id));
  }

  @Override
  public Optional<MyEntity> get(final long id) {
    final MyEntity entity = new MyEntity1(id);

    tables.values().stream()
        .map(table -> table.get(id))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(entity::set);

    return entity.components().isEmpty()
        ? Optional.empty()
        : Optional.of(entity);
  }

  @SuppressWarnings("unchecked")
  public <C extends MyComponent> MyTable<C> table(final Class<C> type) {
    init(type);
    return (MyTable<C>) tables.get(type);
  }

  @Override
  public void update(final MyEntity entity) {
    entity.components().stream()
        .forEach(c -> table(c.getClass()).update(c));

  }

  private void init(final Class<? extends MyComponent> type) {
    if (!tables.containsKey(type)) {
      tables.put(type, new MyTable<>(type));
    }
  }

}
