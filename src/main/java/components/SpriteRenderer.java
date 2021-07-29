package components;

import jade.Component;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {

    //private boolean firstTime = false;

    private Vector4f color;
    private Vector2f[] texCoords;
    // (0, 0)
    // (1, 0)
    // (1, 1)
    // (0, 1)
    private Texture texture;

    public SpriteRenderer(Vector4f color) {
        this.texture = null;
        this.color = color;
    }
    public SpriteRenderer(Texture texture) {
        this.texture = texture;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    @Override
    public void start() {

        //System.out.println("I am starting");
    }
    @Override
    public void update(float dt) {
        /*if(!firstTime) {
            System.out.println("I am updating");
            this.firstTime = true;
        }*/
    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTexCoords() {
        Vector2f[] texCoords = {
          new Vector2f(1,1),
          new Vector2f(1, 0),
          new Vector2f(0, 0),
          new Vector2f(0, 1)
        };
        return texCoords;
    }
}
