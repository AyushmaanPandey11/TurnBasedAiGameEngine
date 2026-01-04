import Entity.boards.TicToeBoard;
import Entity.game.Board;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.GameEngine;
import api.GameManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GameManager gameManager;
    GameEngine gameEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        gameManager = new GameManager();
    }

    private void playGame(Board board, int[][] firstPlayerMove, int[][] secondPlayerMove){
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        int row,col;
        int next =0;
        while(!gameManager.isComplete(board).isOver()){
            if (next < firstPlayerMove.length) {
                row = firstPlayerMove[next][0];
                col = firstPlayerMove[next][1];
                System.out.println("Round: "+next);
                Move userMove = new Move(new Cell(row, col), user);
                gameEngine.move(board, userMove);
                System.out.println("User Move:\n" + board.toString());
            }
            if(gameManager.isComplete(board).isOver()) break;
            row = secondPlayerMove[next][0];
            col = secondPlayerMove[next][1];
            Move computerMove = new Move(new Cell(row,col),computer);
            gameEngine.move(board, computerMove);
            System.out.println("Bot Move:\n" + board.toString());
            next++;
        }
    }

    @Test
    public void playColWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{ {0,0}, {1,0}, {2,0} };
        int[][] secondPlayerMoves = new int[][]{ {0,1}, {1,1}, {2,1} };
        playGame(board,firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }

    @Test
    public void playRowWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{ {2,0}, {2,1}, {2,2} };
        int[][] secondPlayerMoves = new int[][]{ {1,0}, {1,1}, {1,2} };
        playGame(board,firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }

    @Test
    public void playRevDiagWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        int[][] firstPlayerMove = new int[][]{ {0,2}, {1,1}, {2,0} };
        int[][] secondPlayerMoves = new int[][]{ {0,0}, {1,0}, {1,1} };
        playGame(board, firstPlayerMove,secondPlayerMoves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }

    @Test
    public void playDiagComputerWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{ {0,0}, {1,1}, {2,2} };
        int[][] secondPlayerMoves = new int[][]{ {2,0}, {2,1}, {1,0} };
        playGame(board, firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }
}
