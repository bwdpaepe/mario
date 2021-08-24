package components;

import jade.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderer.DebugDraw;
import util.Settings;

import java.util.Set;

public class GridLines extends Component{
    @Override
    public void update(float dt){
        Vector2f cameraPos = Window.getScene().camera().position;
        Vector2f projectionSize = Window.getScene().camera().getProjectionSize();

        int firstX = ((int)(cameraPos.x / Settings.GRID_WIDTH) -1) * Settings.GRID_WIDTH;
        int firstY = ((int)(cameraPos.y / Settings.GRID_HEIGHT) -1) * Settings.GRID_HEIGHT;

        int numVertLines = (int)(projectionSize.x / Settings.GRID_WIDTH) +2;
        int numHorLines = (int)(projectionSize.y / Settings.GRID_HEIGHT) +2;

        int height = (int)projectionSize.y + Settings.GRID_HEIGHT * 2;
        int width = (int)projectionSize.x + Settings.GRID_WIDTH * 2;

        int maxLines = numVertLines > numHorLines ? numVertLines : numHorLines;
        Vector3f color = new Vector3f(0.2f, 0.2f, 0.2f);
        for(int i = 0; i < maxLines; i++){
            int x = firstX + (Settings.GRID_WIDTH * i);
            int y = firstY + (Settings.GRID_HEIGHT * i);

            if(i < numVertLines){
                DebugDraw.addLine2D(new Vector2f(x, firstY), new Vector2f(x, y + height), color);
            }

            if(i < numHorLines){
                DebugDraw.addLine2D(new Vector2f(firstX, y), new Vector2f(x + width, y), color);
            }
        }
    }
}
