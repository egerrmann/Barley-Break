import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionListeners {
    GUI gui;

    public ActionListeners(GUI gui) {
        this.gui = gui;
    }

    public void addMouseListener() {
        gui.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / (gui.panel.getHeight() / 4);
                int col = e.getX() / (gui.panel.getWidth() / 4);

                if (row != 0 && gui.barleyBreak.numbers[row - 1][col] == 0)
                    gui.barleyBreak.moveUp(row, col);

                if (row != 3 && gui.barleyBreak.numbers[row + 1][col] == 0)
                    gui.barleyBreak.moveDown(row, col);

                if (col != 0 && gui.barleyBreak.numbers[row][col - 1] == 0)
                    gui.barleyBreak.moveLeft(row, col);

                if (col != 3 && gui.barleyBreak.numbers[row][col + 1] == 0)
                    gui.barleyBreak.moveRight(row, col);

                gui.updatePanel();
            }
        });
    }

    public void addKeyListener() {
        gui.window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int row = 0;
                int col = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (gui.barleyBreak.numbers[i][j] == 0) {
                            row = i;
                            col = j;
                            break;
                        }
                    }
                }

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && row != 3)
                    gui.barleyBreak.moveUp(row + 1, col);

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && row != 0)
                    gui.barleyBreak.moveDown(row - 1, col);

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && col != 3)
                    gui.barleyBreak.moveLeft(row, col + 1);

                if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && col != 0)
                    gui.barleyBreak.moveRight(row, col - 1);

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && row != 3)
                    while (row != 3) {
                        gui.barleyBreak.moveUp(row + 1, col);
                        row++;
                    }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && row != 0)
                    while (row != 0) {
                        gui.barleyBreak.moveDown(row - 1, col);
                        row--;
                    }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && col != 3)
                    while (col != 3) {
                        gui.barleyBreak.moveLeft(row, col + 1);
                        col++;
                    }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && col != 0)
                    while (col != 0) {
                        gui.barleyBreak.moveRight(row, col - 1);
                        col--;
                    }

                gui.updatePanel();
            }
        });
    }
}
