package fr.flegac.dataoriented.object;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import fr.flegac.dataoriented.api.MyComponent;
import fr.flegac.dataoriented.api.MyEntity;

public class MyEntity1 implements MyEntity {
  private final long id;
  private final Map<Class<?>, MyComponent> components = new HashMap<>();

  public MyEntity1(final long id) {
    super();
    this.id = id;
  }

  @Override
  public Collection<MyComponent> components() {
    return components.values();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MyEntity1 other = (MyEntity1) obj;
    if (components == null) {
      if (other.components != null) {
        return false;
      }
    } else if (!components.equals(other.components)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <C extends MyComponent> Optional<C> get(final Class<C> type) {
    return Optional.ofNullable((C) components.get(type));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((components == null) ? 0 : components.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public long id() {
    return id;
  }

  @Override
  public <C extends MyComponent> void set(final C component) {
    component.id = id;
    components.put(component.getClass(), component);
  }

}
