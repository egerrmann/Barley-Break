public class BarleyBreakLogic {
    int[][] numbers;
    GUI gui;

    public BarleyBreakLogic(GUI gui) {
        numbers = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != 3 || j != 3)
                    numbers[i][j] = i * 4 + (j + 1);
                else
                    numbers[i][j] = 0;
            }
        }

        this.gui = gui;
    }

    public void moveUp(int row, int col) {
        numbers[row - 1][col] = numbers[row][col];
        numbers[row][col] = 0;
    }

    public void moveDown(int row, int col) {
        numbers[row + 1][col] = numbers[row][col];
        numbers[row][col] = 0;
    }

    public void moveLeft(int row, int col) {
        numbers[row][col - 1] = numbers[row][col];
        numbers[row][col] = 0;
    }

    public void moveRight(int row, int col) {
        numbers[row][col + 1] = numbers[row][col];
        numbers[row][col] = 0;
    }
}
