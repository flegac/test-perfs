package fr.flegac.dataoriented.dataoriented;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import fr.flegac.dataoriented.api.MyComponent;
import fr.flegac.dataoriented.api.MyEntity;
import fr.flegac.dataoriented.api.MyRepository;
import fr.flegac.dataoriented.object.MyEntity1;

public class Repository2 implements MyRepository {
  private final Map<Class<?>, MyTable<? extends MyComponent>> tables = new HashMap<>();

  @Override
  public Stream<MyEntity> all() {
    final Set<Long> ids = new HashSet<>();

    for (final MyTable<? extends MyComponent> table : tables.values()) {
      table.all().stream()
          .map(c -> c.id)
          .map(ids::add);
    }

    return ids.stream().map(this::get)
        .filter(Optional::isPresent)
        .map(Optional::get);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stream<MyComponent> components(final Class type) {
    return table(type).all().stream();
  }

  @Override
  public void delete(final long id) {
    for (final MyTable<?> table : tables.values()) {
      table.delete(id);
    }
  }

  @Override
  public Optional<MyEntity> get(final long id) {
    final MyEntity entity = new MyEntity1(id);
    boolean exist = false;
    for (final MyTable<?> table : tables.values()) {
      final Optional<?> component = table.get(id);
      exist = exist || component.isPresent();
      component.ifPresent(c -> {
        entity.set((MyComponent) c);
      });
    }
    if (!exist) {
      return Optional.empty();
    }

    return Optional.of(entity);
  }

  @SuppressWarnings("unchecked")
  public <C extends MyComponent> MyTable<C> table(final Class<C> type) {
    init(type);
    return (MyTable<C>) tables.get(type);
  }

  @Override
  public void update(final MyEntity entity) {
    for (final MyComponent c : entity.components()) {
      table(c.getClass()).update(c);
    }
  }

  private void init(final Class<? extends MyComponent> type) {
    if (!tables.containsKey(type)) {
      tables.put(type, new MyTable<>(type));
    }
  }

}
