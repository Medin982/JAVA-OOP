package WorkingwithAbstraction.Exercise.JediGalaxy.jediGalaxy;

public class Field {
    private int[][] matrix;

    public Field(int row, int col) {
        this(row, col, 0);
    }

    public Field(int row, int col, int beginValue) {
        this.matrix = new int[row][col];
        this.fillValues(beginValue);
    }

    private void fillValues(int beginValue) {
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[row].length; col++) {
                this.matrix[row][col] = beginValue++;
            }
        }
    }
    public boolean isOnBounds(int rows, int cols) {
        return rows >= 0 && rows < this.matrix.length && cols >= 0 && cols < this.matrix.length;
    }

    public void setValue(int row, int col, int newValue) {
        this.matrix[row][col] = newValue;
    }

    public int getValue(int row, int col) {
        return this.matrix[row][col];
    }

    public int getColLength(int row) {
        return this.matrix[row].length;
    }
}
