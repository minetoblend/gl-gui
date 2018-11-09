import com.minetoblend.parnorama.gui.element.Container;
import com.minetoblend.parnorama.gui.layout.BorderLayout;
import com.minetoblend.parnorama.gui.layout.FloatingLayout;
import com.minetoblend.parnorama.gui.Window;
import com.minetoblend.parnorama.gui.element.Button;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Window window = new Window().size(1280, 720);
        window.setLayout(new BorderLayout());
        window.add(new Button(), BorderLayout.Region.LEFT);
        Container container = new Container();
        window.add(container, BorderLayout.Region.TOP);
        container.setLayout(new BorderLayout());
        container.add(new Button(), BorderLayout.Region.LEFT);
        window.create();
    }
}
