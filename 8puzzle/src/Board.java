import edu.princeton.cs.algs4.Stack;

public class Board {

    private final int n;
    private final int[][] tiles;

    public Board(int[][] blocks) {
        n = blocks.length;
        tiles = copy(blocks);
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (tiles[i][j] != expectedValue(i, j) && tiles[i][j] != 0)
                    ++hamming;
        return hamming;
    }
    public int manhattan() {
        int manhattan = 0;
        int[] res;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] != 0) {
                    res = expectedPosition(tiles[i][j]);
                    manhattan += Math.abs(res[0] - i);
                    manhattan += Math.abs(res[1] - j);
                }
            }
        return manhattan;
    }

    public boolean isGoal() {
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (expectedValue(i, j) != tiles[i][j])
                    return false;
        return true;
    }
    public Board twin() {
        int[][] copy = copy(tiles);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n - 1; ++j)
                if (copy[i][j] != 0 && copy[i][j + 1] != 0) {
                    int t = copy[i][j + 1];
                    copy[i][j + 1] = copy[i][j];
                    copy[i][j] = t;
                    return  new Board(copy);
                }
        throw new RuntimeException();
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;
        if (((Board) y).tiles.length != tiles.length) return false;
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length; col++)
                if (((Board) y).tiles[row][col] != tiles[row][col]) return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<>();
        int[] space = spaceLocation();
        int row = space[0];
        int col = space[1];
        if (row > 0)
            stack.push(new Board(copyAndSwap(this, row, col, row - 1, col)));
        if (row < n - 1)
            stack.push(new Board(copyAndSwap(this, row, col, row + 1, col)));
        if (col > 0)
            stack.push(new Board(copyAndSwap(this, row, col, row, col - 1)));
        if (col < n - 1)
            stack.push(new Board(copyAndSwap(this, row, col, row, col + 1)));

        return stack;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int expectedValue(int i, int j) {
        if (i == n - 1 && j == n - 1)
            return 0;
        return i * n + j + 1;
    }

    private int[] expectedPosition(int val) {
        if (val == 0)
            return new int[] {n - 1, n - 1};
        int i = (val - 1) / n;
        int j = (val - 1) % n;
        return new int[] {i, j};
    }

    private int[][] copyAndSwap(Board origin, int i0, int j0, int i1, int j1) {
        int[][] copy = copy(origin.tiles);
        int t = copy[i0][j0];
        copy[i0][j0] = copy[i1][j1];
        copy[i1][j1] = t;
        return copy;
    }

    private int[][] copy(int[][] src) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                copy[i][j] = src[i][j];
        return copy;
    }

    private int[] spaceLocation() {
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (tiles[i][j] == 0)
                    return new int[] {i, j};
        throw new RuntimeException();
    }
}
