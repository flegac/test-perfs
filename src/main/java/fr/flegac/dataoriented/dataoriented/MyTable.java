package fr.flegac.dataoriented.dataoriented;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import fr.flegac.dataoriented.api.MyComponent;

public class MyTable<COMPONENT extends MyComponent> {
  private final Map<Long, COMPONENT> items = Collections.synchronizedMap(new HashMap<>());
  private final Class<COMPONENT> type;

  public MyTable(final Class<COMPONENT> type) {
    super();
    this.type = type;
  }

  public Collection<COMPONENT> all() {
    return items.values();
  }

  public void delete(final long id) {
    items.remove(id);
  }

  public Optional<COMPONENT> get(final long id) {
    return Optional.ofNullable(items.get(id));
  }

  public Class<COMPONENT> type() {
    return type;
  }

  @SuppressWarnings("unchecked")
  public void update(final MyComponent item) {
    items.put(item.id, (COMPONENT) item);
  }
}
