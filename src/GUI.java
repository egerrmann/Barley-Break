import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GUI {
    JFrame window;
    JPanel panel;
    GridLayout layout;
    BarleyBreakLogic barleyBreak;

    public GUI() {
        window = new JFrame("Barley-Break");
        panel = new JPanel();
        layout = new GridLayout(4, 4);
        barleyBreak = new BarleyBreakLogic(this);

        createWindow();
        createGrid();
        addMouseListener();
        addKeyListener();
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
    }

    public void addMouseListener() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / (panel.getHeight() / 4);
                int col = e.getX() / (panel.getWidth() / 4);

                if (row != 0 && barleyBreak.numbers[row - 1][col] == 0)
                    barleyBreak.moveUp(row, col);

                if (row != 3 && barleyBreak.numbers[row + 1][col] == 0)
                    barleyBreak.moveDown(row, col);

                if (col != 0 && barleyBreak.numbers[row][col - 1] == 0)
                    barleyBreak.moveLeft(row, col);

                if (col != 3 && barleyBreak.numbers[row][col + 1] == 0)
                    barleyBreak.moveRight(row, col);

                updatePanel();
            }
        });
    }

    public void addKeyListener() {
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int row = 0;
                int col = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (barleyBreak.numbers[i][j] == 0) {
                            row = i;
                            col = j;
                            break;
                        }
                    }
                }

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && row != 3)
                    barleyBreak.moveUp(row + 1, col);

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && row != 0)
                    barleyBreak.moveDown(row - 1, col);

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && col != 3)
                    barleyBreak.moveLeft(row, col + 1);

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && col != 0)
                    barleyBreak.moveRight(row, col - 1);


                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && row != 3)
                    while (row != 3) {
                        barleyBreak.moveUp(row + 1, col);
                        row++;
                    }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && row != 0)
                    while (row != 0) {
                        barleyBreak.moveDown(row - 1, col);
                        row--;
                    }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && col != 3)
                    while (col != 3) {
                        barleyBreak.moveLeft(row, col + 1);
                        col++;
                    }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && col != 0)
                    while (col != 0) {
                        barleyBreak.moveRight(row, col - 1);
                        col--;
                    }

                updatePanel();
            }
        });
    }
}
