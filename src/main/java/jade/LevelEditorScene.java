package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.SpriteSheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.system.CallbackI;
import util.AssetPool;

/*import components.FontRenderer;
import components.SpriteRenderer;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import renderer.Shader;
import renderer.Texture;
import util.Time;

import java.awt.event.KeyEvent;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
*/
public class LevelEditorScene extends Scene{

    private GameObject obj1;
    private SpriteSheet sprites;
    /*private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;*/

    /*private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";
    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    color = fColor;\n" +
            "}";*/
/*
    private int vertexID, fragmentID, shaderProgram;
    private float vertexArray[] = {
            // position                      // color                    // UV Coordinates
            100.5f,    0.5f, 0.0f,           1.0f, 0.0f, 0.0f, 1.0f,     1, 1, // Bottom right 0
              0.5f,  100.5f, 0.0f,           0.0f, 1.0f, 0.0f, 1.0f,     0, 0, // Top left     1
            100.5f,  100.5f, 0.0f,           0.0f, 0.0f, 1.0f, 1.0f,     1, 0, // Top right    2
              0.5f,    0.5f, 0.0f,           1.0f, 1.0f, 0.0f, 1.0f,     0, 1, // Bottom left  3
    };

    // IMPORTANT: Must be in counter-clockwise direction
    private int[] elementArray = {

    }
    */
        /*
                   *          *



                   *          *
        */
/*            2, 1, 0,    // Top right triangle
            0, 1, 3     // bottom left triangle
    };

*/
/*
    private int vaoID, vboID, eboID;

    private Shader defaultShader;
    private Texture testTexture;

    private GameObject testObj;

    private boolean firstTime = false;
*/
    public LevelEditorScene() {
        //System.out.println("Inside LevelEditorScene.");

    }

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f());
        this.sprites = AssetPool.getSpriteSheet("assets/images/spritesheet.png");


        /*int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 -xOffset * 2);
        float totalHeight = (float)(300 -yOffset * 2);
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight / 100.0f;

        for(int x = 0; x < 100; x++){
            for(int y = 0; y < 100; y++){
                float xPos = xOffset + (x * sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject go = new GameObject("Obj" + x + "" + y, new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
                go.addComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth, yPos / totalHeight, 1 ,1 )));
                this.addGameObjectToScene(go);
            }
        }*/

        this.obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        this.obj1.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(this.obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        obj2.addComponent(new SpriteRenderer(sprites.getSprite(10)));
        this.addGameObjectToScene(obj2);



/*        System.out.println("Creating test object");
        this.testObj = new GameObject("test object");
        this.testObj.addComponent(new SpriteRenderer());
        this.testObj.addComponent(new FontRenderer());
        this.addGameObjectToScene(this.testObj);

        this.camera = new Camera(new Vector2f());
        this.defaultShader = new Shader("assets/shaders/default.glsl");
        this.defaultShader.compileAndLink();
        this.testTexture = new Texture("assets/images/mario.png");
        // ********************************************
        // Generate VAO, VBO, and EBO buffer objects, and send to GPU
        // ********************************************
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create a float buffer of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int vertexSizeBytes = (positionSize + colorSize + uvSize) * Float.BYTES;
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);
*/
    }

    private void loadResources() {

        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpriteSheet("assets/images/spritesheet.png",
                new SpriteSheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16,
                        16 ,
                        26,
                        0)
        );
    }

    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;
    @Override
    public void update(float dt) {
        System.out.println("FPS: " + 1.0f / dt);
        //this.camera.position.x -= dt * 100.0f;
        /*System.out.println("We are running at " + (1.0f / dt) + "FPS");

        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }
        if(changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        }
        else if(changingScene){
            Window.changeScene(1);
        }*/
        /*
        // Bind shader program
        this.defaultShader.use();
        // upload texture to shader
        defaultShader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        testTexture.bind();

        this.defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        this.defaultShader.uploadMat4f("uView", camera.getViewMatrix());
        this.defaultShader.uploadFloat("uTime", Time.getTime());
        // Bind the VAO we're using
        glBindVertexArray(vaoID);
        // Enable the vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        // unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);
        this.defaultShader.detach();

        if(!firstTime) {
            System.out.println("Creating gameObject!");
            GameObject go = new GameObject("Game Test 2");
            go.addComponent(new SpriteRenderer());
            this.addGameObjectToScene(go);
            this.firstTime=true;
        }
        */
        spriteFlipTimeLeft -= dt;
        if(spriteFlipTimeLeft <= 0){
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if(spriteIndex>4){
                spriteIndex=0;
            }
            this.obj1.getComponent(SpriteRenderer.class).setSprite(this.sprites.getSprite(spriteIndex));
        }
        //this.obj1.transform.position.x += 10 * dt;
        for(GameObject go: this.gameObjects) {
            go.update(dt);
        }
        this.renderer.render();
    }
}
