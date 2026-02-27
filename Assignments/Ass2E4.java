import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ass2E4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxLayout Vertical Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JButton("Button 1"));
        panel.add(new JButton("Button 2"));

        frame.setVisible(true);
    }
}