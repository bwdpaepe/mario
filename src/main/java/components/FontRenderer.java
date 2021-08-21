package components;

public class FontRenderer extends Component {


    @Override
    public void start() {
        if(this.gameObject.getComponent(FontRenderer.class) != null) {
            System.out.println("Found Font Renderer");
        }
    }
    @Override
    public void update(float dt) {

    }
}
