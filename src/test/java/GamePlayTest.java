import Entity.boards.TicToeBoard;
import Entity.game.Board;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.AIEngine;
import api.GameEngine;
import api.GameManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GameManager gameManager;
    GameEngine gameEngine;
    AIEngine aiEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        gameManager = new GameManager();
        aiEngine = new AIEngine();
    }

    private void playGame(Board board, int[][] moves){
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        int row,col;
        int next =0;
        while(!gameManager.isComplete(board).isOver()){
            System.out.println(board.toString());
            System.out.println("Game started, Make your move! \n");
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move userMove = new Move(new Cell(row, col), user);
            gameEngine.move(board,userMove);
            if(!gameManager.isComplete(board).isOver()){
                Move computerMove = aiEngine.suggestMove(board,computer);
                gameEngine.move(board, computerMove);
            }
            System.out.println(board.toString());
        }
    }

    @Test
    public void playColWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        int[][] moves = new int[][]{ {0,0}, {1,0}, {2,0} };
        playGame(board,moves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }

    @Test
    public void playRowWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        int row,col;
        int next =0;
        int[][] moves = new int[][]{ {0,0}, {0,1}, {0,2} };
        playGame(board,moves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }

    @Test
    public void playRevDiagWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        int row,col;
        int next =0;
        int[][] moves = new int[][]{ {0,2}, {1,1}, {2,0} };
        playGame(board,moves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"User");
    }

    @Test
    public void playDiagComputerWin(){
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        int row,col;
        int next =0;
        int[][] moves = new int[][]{ {0,0}, {1,1}, {2,2} };
        playGame(board,moves);
        Assert.assertTrue(gameManager.isComplete(board).isOver());
        Assert.assertEquals(gameManager.isComplete(board).getWinner(),"bot");
    }
}
