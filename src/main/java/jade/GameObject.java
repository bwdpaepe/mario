package jade;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private String name;
    private List<Component> components;
    public Transform transform;

    public GameObject(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = new Transform();
    }

    public GameObject(String name, Transform transform) {
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = transform;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c: components) {
            if(componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                }
                catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component.";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        /*for(int i=0; i<this.components.size(); i++) {
            Component c = this.components.get(i);
            if(componentClass.isAssignableFrom(c.getClass())) {
                this.components.remove(i);
                return;
            }
        }*/
        int i =0;
        for (Component c: components) {
            if(componentClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
            i++;
        }
    }

    public void addComponent(Component c) {
        this.components.add(c);
        c.gameObject = this;
    }

    public void update(float dt) {
        /*for(int i=0; i < this.components.size(); i++){
            components.get(i).update(dt);
        }*/
        int i =0;
        for (Component c: components) {
            c.update(dt);
            i++;
        }
    }

    public void start() {
        /*for(int i=0; i<components.size(); i++) {
            components.get(i).start();
        }*/
        int i =0;
        for (Component c: components) {
            c.start();
            i++;
        }
    }

}
