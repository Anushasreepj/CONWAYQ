package se.pe.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple GameOfLifeApp.
 */
public class GameOfLifeAppTest
{
    @Test
    public void runTest() {
        GameOfLifeApp gameOfLifeApp = new GameOfLifeApp();
        Universe universe = new Universe(10, 10, new Rule[] { new Rule(1, 1, "<=", CellState.ALIVE, CellState.DEAD), new Rule(1, 1, ">=", CellState.ALIVE, CellState.DEAD)});
        gameOfLifeApp.run(universe, 1);
        Assertions.assertTrue(universe.toString().contains("..."));
    }

    @Test
    public void renderTest() {
        GameOfLifeApp gameOfLifeApp = new GameOfLifeApp();
        Universe universe = new Universe(10, 10, new Rule[] { new Rule(2, 3, "<", CellState.ALIVE, CellState.DEAD), new Rule(2, 3, ">", CellState.DEAD, CellState.ALIVE)});
        gameOfLifeApp.render(universe);
        Assertions.assertTrue(universe.toString().contains("*.."));
    }

}
