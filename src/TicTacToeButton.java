import javax.swing.JButton;

public class TicTacToeButton extends JButton {
    private int row, col;
    private char state;

    public TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
        this.state = '-';
        setText("-");
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getState() {
        return state;
    }

    public void setState(char player) {
        state = player;
        setText(String.valueOf(player));
    }

    public void reset() {
        state = '-';
        setText("-");
    }
}
