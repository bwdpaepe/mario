package renderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shader {

    private int shaderProgramID;
    private int vertexID;
    private int fragmentID;

    private String vertexSource;
    private String fragmentSource;
    private String filepath;

    public Shader(String filepath) {
        this.filepath = filepath;
        try {
            String source = Files.readString(Paths.get(this.filepath));
            String[] splitString = source.split("#type\\s+[a-zA-Z]+");

            // Find the first pattern after #type
            int index = source.indexOf("#type") + 6;
            int eol = source.indexOf("\r\n", index);
            String firstPattern = source.substring(index, eol).trim();

            // Find the second pattern after #type
            index = source.indexOf("#type", eol) + 6;
            eol = source.indexOf("\r\n", index);
            String secondPattern = source.substring(index, eol).trim();

            if (firstPattern.equals("vertex")) {
                this.vertexSource = splitString[1];
            }
            else if (firstPattern.equals("fragment")) {
                this.fragmentSource = splitString[1];
            }
            else {
                throw new IOException("Unexpected token: " + firstPattern + " in: " + filepath + "'");
            }

            if (secondPattern.equals("vertex")) {
                this.vertexSource = splitString[2];
            }
            else if (secondPattern.equals("fragment")) {
                this.fragmentSource = splitString[2];
            }
            else {
                throw new IOException("Unexpected token: " + firstPattern + " in: " + filepath + "'");
            }

            System.out.println(vertexSource);
            System.out.println(fragmentSource);
        }
        catch(IOException e) {
            e.printStackTrace();
            assert false: "Error: could not open file for shader: '" + this.filepath + "'";
        }
    }

    public void compileAndLink() {
        // ********************************************
        // Compile and link shaders
        // ********************************************


        // First load and compile the vertex shader
        this.vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(this.vertexID, this.vertexSource);
        glCompileShader(this.vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(this.vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(this.vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + filepath + "'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(this.vertexID, len));
            assert false: "";
        }

        // Then load and compile the fragment shader
        this.fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(this.fragmentID, this.fragmentSource);
        glCompileShader(this.fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(this.fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(this.fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + filepath + "'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(this.fragmentID, len));
            assert false: "";
        }

        // Link shaders and check for errors

        this.shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);

        // Check for linking errors
        success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + filepath + "'\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            assert false: "";
        }


    }

    public void use() {
        // Bind shader program
        glUseProgram(this.shaderProgramID);

    }

    public void detach () {
        glUseProgram(0);
    }
}
