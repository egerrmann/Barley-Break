import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUI {
    JFrame window;
    JPanel panel;
    GridLayout layout;
    BarleyBreakLogic barleyBreak;
    ActionListeners actionListeners;

    public GUI() {
        window = new JFrame("Barley-Break");
        panel = new JPanel();
        layout = new GridLayout(4, 4);
        barleyBreak = new BarleyBreakLogic(this);
        actionListeners = new ActionListeners(this);

        createWindow();
        createGrid();
        actionListeners.addMouseListener();
        actionListeners.addKeyListener();
    }

    public void createWindow() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds(
                dimension.width / 2 - 225,
                dimension.height / 2 - 225,
                450,
                450);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void createGrid() {
        panel.removeAll();
        panel.setLayout(layout);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JPanel cell = new JPanel();
                JLabel number;
                if (i != 3 || j != 3)
                    number = new JLabel("" + barleyBreak.numbers[i][j]);
                else
                    number = new JLabel("");

                number.setFont(new Font(null, Font.PLAIN, 40));
                number.setForeground(Color.WHITE);

                cell.setLayout(new GridBagLayout());
                if (!Objects.equals(number.getText(), ""))
                    cell.setBackground(Color.green);
                else
                    cell.setBackground(Color.black);
                cell.add(number);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                panel.add(cell);
            }
        }

        window.getContentPane().add(panel);
        panel.setVisible(true);
    }

    public void updatePanel() {
        int iterations = 0;

        for(Component component : panel.getComponents()){
            int row = iterations / 4;
            int col = iterations % 4;

            JLabel label = (JLabel)((JPanel)component).getComponent(0);

            if (barleyBreak.numbers[row][col] != 0) {
                label.setText("" + barleyBreak.numbers[row][col]);
                if (barleyBreak.numbers[row][col] == (iterations + 1))
                    component.setBackground(Color.green);
                else
                    component.setBackground(Color.red);
            }
            else {
                label.setText("");
                component.setBackground(Color.black);
            }

            iterations++;
        }

        if (barleyBreak.gameIsCompleted())
            JOptionPane.showMessageDialog(new JFrame(),
                    "Game is completed!",
                    "Congratulations!",
                    JOptionPane.INFORMATION_MESSAGE);
    }
}
