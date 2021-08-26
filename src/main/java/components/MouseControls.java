package components;

import jade.GameObject;
import jade.MouseListener;
import jade.Window;
import util.Settings;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class MouseControls extends Component {
    GameObject holdingObject = null;

    public void pickupObject(GameObject go) {
        this.holdingObject = go;
        Window.getScene().addGameObjectToScene(go);
    }

    public void place() {
        this.holdingObject = null;
    }

    @Override
    public void update(float dt){
        if(holdingObject!=null){
            float myX = MouseListener.getOrthoX();
            float myY = MouseListener.getOrthoY();
            holdingObject.transform.position.x = (int)(myX / Settings.GRID_WIDTH) * Settings.GRID_WIDTH;
            holdingObject.transform.position.y = (int)(myY / Settings.GRID_HEIGHT) * Settings.GRID_HEIGHT;
            if(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT)){
                place();
            }
        }
    }
}
