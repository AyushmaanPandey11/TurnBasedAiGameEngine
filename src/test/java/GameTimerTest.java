import Entity.game.*;
import org.junit.Assert;
import org.junit.Test;

public class GameTimerTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTest(){
        Game game = gameFactory.createGame(11000,6000);
        Player x = new Player('X',"User");
        Player o = new Player('O',"bot");
        Cell c00 = Cell.getCell(0,0);
        Cell c01 = Cell.getCell(0,1);
        Cell c02 = Cell.getCell(0,2);
        Cell c10 = Cell.getCell(1,0);
        int ts = 5000;
        game.move(new Move(c00,x),ts);
        game.move(new Move(c01,o),ts);
        game.move(new Move(c02,x),ts);
        game.move(new Move(c10,o),7000);
        Assert.assertEquals(game.getWinner().getPlayerName(),x.getPlayerName());
    }

    @Test
    public void timeOutTestForVictory(){
        Game game = gameFactory.createGame(11000,6000);
        Player x = new Player('X',"User");
        Player o = new Player('O',"bot");
        Cell c00 = Cell.getCell(0,0);
        Cell c01 = Cell.getCell(0,1);
        Cell c02 = Cell.getCell(0,2);
        Cell c10 = Cell.getCell(1,0);
        Cell c11 = Cell.getCell(1,1);
        Cell c22 = Cell.getCell(2,2);
        int ts = 2000;
        game.move(new Move(c00,x),ts);
        game.move(new Move(c01,o),ts);
        game.move(new Move(c11,x),ts);
        game.move(new Move(c10,o),ts);
        game.move(new Move(c22,x),ts);
        game.move(new Move(c02,o),ts);
        Assert.assertEquals(game.getWinner().getPlayerName(),x.getPlayerName());
    }

    @Test
    public void timeOutTestPerPlayer(){
        Game game = gameFactory.createGame(11000);
        Player x = new Player('X',"User");
        Player o = new Player('O',"bot");
        Cell c00 = Cell.getCell(0,0);
        Cell c01 = Cell.getCell(0,1);
        Cell c02 = Cell.getCell(0,2);
        Cell c10 = Cell.getCell(1,0);
        int ts = 5000;
        game.move(new Move(c00,x),ts);
        game.move(new Move(c01,o),ts);
        game.move(new Move(c02,x),ts);
        game.move(new Move(c10,o),7000);
        Assert.assertEquals(game.getWinner().getPlayerName(),x.getPlayerName());
    }

    @Test
    public void timeoutTestForOneMoveWithoutMoveTimeout(){
        Game game = gameFactory.createGame(11000);
        Player x = new Player('X',"User");
        Cell c00 = Cell.getCell(0,0);
        int ts = 5000;
        game.move(new Move(c00,x),ts);
        Assert.assertNull(game.getWinner());
    }
}
