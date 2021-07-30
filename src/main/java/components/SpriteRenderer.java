package components;

import jade.Component;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {

    //private boolean firstTime = false;

    private Vector4f color;
    private Sprite sprite;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);
    }
    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
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
        return sprite.getTexure();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }
}
