import javax.swing.*;

/**
 * Created by Пользователь on 28.09.2016.
 */
public class Window extends JFrame {
    Conway core;
    Window(String s){
        super(s);
        core = new Conway();
        add(core);
        setVisible(true);
        setLocation(300,100);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
