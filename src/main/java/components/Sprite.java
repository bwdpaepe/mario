package components;

import org.joml.Vector2f;
import renderer.Texture;

public class Sprite {
    private Texture tex;
    private Vector2f[] texCoords;

    public Sprite (Texture tex) {
        this.tex = tex;
        Vector2f[] texCoords = {
                new Vector2f(1,1),
                new Vector2f(1,0),
                new Vector2f(0,0),
                new Vector2f(0,1),
        };
        this.texCoords = texCoords;
    }

    public Sprite (Texture tex, Vector2f[] texCoords) {
        this.tex = tex;
        this.texCoords = texCoords;
    }

    public Texture getTexure() {
        return this.tex;
    }

    public Vector2f[] getTexCoords() {
        return this.texCoords;
    }
}
