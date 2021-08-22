package scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.*;
import imgui.ImGui;
import imgui.ImVec2;
import jade.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import renderer.DebugDraw;
import scenes.Scene;
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
public class LevelEditorScene extends Scene {

    private GameObject obj1;
    private SpriteSheet sprites;
    private SpriteRenderer obj1SpriteRenderer;
    private MouseControls mouseControls = new MouseControls();
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
        this.sprites = AssetPool.getSpriteSheet("assets/images/spritesheets/decorationsAndBlocks.png");
        DebugDraw.addLine2D(new Vector2f(0,0), new Vector2f(800,800), new Vector3f(1,0,0), 120);
        if(levelLoaded){
            this.activeGameObject = gameObjects.get(0);
            return;
        }



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

        this.obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), 2);
        //SpriteRenderer obj1SpriteRenderer = new SpriteRenderer();
        this.obj1SpriteRenderer = new SpriteRenderer();
        obj1SpriteRenderer.setColor(new Vector4f(1, 0, 0, 1));
        this.obj1.addComponent(obj1SpriteRenderer);
        this.obj1.addComponent(new Rigidbody());
        this.addGameObjectToScene(this.obj1);
        this.activeGameObject = obj1;

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 4);
        SpriteRenderer obj2SpriteRenderer = new SpriteRenderer();
        Sprite obj2Sprite = new Sprite();
        obj2Sprite.setTex(AssetPool.getTexture("assets/images/blendImage2.png"));
        obj2SpriteRenderer.setSprite(obj2Sprite);
        obj2.addComponent(obj2SpriteRenderer);
        this.addGameObjectToScene(obj2);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();

        String serialized = gson.toJson(obj1);
        System.out.println(serialized);

        GameObject obj = gson.fromJson(serialized, GameObject.class);
        System.out.println(obj);





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
        AssetPool.getTexture("assets/images/blendImage2.png");
        AssetPool.addSpriteSheet("assets/images/spritesheets/decorationsAndBlocks.png",
                new SpriteSheet(AssetPool.getTexture("assets/images/spritesheets/decorationsAndBlocks.png"),
                        16,
                        16 ,
                        81,
                        0)
        );
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS: " + 1.0f / dt);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        System.out.println(gson.toJson(obj1SpriteRenderer));
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
        mouseControls.update(dt);
        for(GameObject go: this.gameObjects) {
            go.update(dt);
        }
        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test window");
        ImVec2 windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);

        float windowX2 = windowPos.x + windowSize.x;
        for(int i = 0; i<this.sprites.size(); i++){
            Sprite sprite = this.sprites.getSprite(i);
            float spriteWidth = sprite.getWidth() * 4;
            float spriteHeight = sprite.getHeight() * 4;
            int id = sprite.getTexId();
            Vector2f[] texCoords = sprite.getTexCoords();

            ImGui.pushID(i);
            if(ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[0].x, texCoords[0].y, texCoords[2].x, texCoords[2].y)){
                System.out.println("Button " + i + " clicked");
                GameObject object = Prefabs.generateSpriteObject(sprite, spriteWidth, spriteHeight);
                // Attach this to the mouse cursor
                mouseControls.pickupObject(object);

            }
            ImGui.popID();

            ImVec2 lastButtonPos = new ImVec2();
            ImGui.getItemRectMax(lastButtonPos);
            float lastButtonX2 = lastButtonPos.x;
            float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
            if(i+1 < sprites.size() && nextButtonX2 < windowX2){
                ImGui.sameLine();
            }

        }
        ImGui.end();
    }
}
