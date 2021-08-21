package jade;

import components.Component;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private static int ID_COUNTER = 0;
    private int uid = -1;

    private String name;
    private List<Component> components;
    public Transform transform;
    private int zIndex;

    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;

        this.uid = ID_COUNTER++;
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
        c.generateId();
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

    public void imgui() {
        for (Component c: components) {
            c.imgui();
        }
    }

    public int zIndex() {
        return this.zIndex;
    }

    public static void init(int maxId){
        ID_COUNTER = maxId;
    }

    public int getUid() {
        return uid;
    }

    public List<Component> getAllComponents(){
        return this.components;
    }
}
