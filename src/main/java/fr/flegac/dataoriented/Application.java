package fr.flegac.dataoriented;

import static java.util.Arrays.stream;
import fr.flegac.Util;
import fr.flegac.dataoriented.api.MyComponent;
import fr.flegac.dataoriented.api.MyRepository;
import fr.flegac.dataoriented.api.MySystem;
import fr.flegac.dataoriented.object.MyEntity1;

public class Application {
  private final MyRepository repo;
  private final MySystem<?>[] systems;
  private final Class<?>[] components;

  public Application(final MyRepository repo, final MySystem<?>[] systems, final Class<?>[] components) {
    super();
    this.repo = repo;
    this.systems = systems.clone();
    this.components = components.clone();
  }

  public void run(final int entityNumber, final int updateIterations) {
    initRepo(entityNumber);
    update(updateIterations);
  }

  private void initRepo(final int entityNumber) {
    Util.eval("init", entityNumber, (id) -> {
      try {
        final MyEntity1 entity = new MyEntity1(id);
        final MyComponent c = (MyComponent) components[id % components.length].getConstructor().newInstance();
        entity.set(c);
        repo.update(entity);
      } catch (final Exception e) {
        e.printStackTrace();
      }
    });
  }

  private void update(final int updateIterations) {
    Util.eval("update", updateIterations, (i) -> {
      stream(systems).forEach((final MySystem sys) -> {
        repo.components(sys.type())
            .forEach(component -> {
              sys.update((MyComponent) component);
            });
      });
    });
  }
}
