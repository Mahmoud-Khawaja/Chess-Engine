
import com.chess.engine.board.Board;
import com.chess.gui.Table;
public class Chess {
    public static void main(String[] args) {
    Board board = Board.createStandardBoard();    
    System.out.println(board.toString());
    Table table = new Table();
    }
}
