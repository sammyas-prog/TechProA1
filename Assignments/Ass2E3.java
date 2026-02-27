import javax.swing.JButton;
import javax.swing.JFrame;

public class Ass2E3 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Absolute Positioning");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 1. Disable the layout manager
        frame.setLayout(null);

        JButton btn1 = new JButton("Button 1");
        // 2. Set bounds: (x, y, width, height)
        // (50, 50) is the top-left corner, 150px wide, 40px tall
        btn1.setBounds(50, 50, 150, 40);

        JButton btn2 = new JButton("Button 2");
        // Place this one lower and further right
        btn2.setBounds(150, 120, 150, 40);

        frame.add(btn1);
        frame.add(btn2);

        frame.setVisible(true);
    }
}