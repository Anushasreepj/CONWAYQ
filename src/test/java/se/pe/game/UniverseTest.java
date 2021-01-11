package se.pe.game;


import lombok.extern.java.Log;
import org.junit.jupiter.api.*;


@Log
class UniverseTest {


    @Test
    public void nextGenerationTest() {
        Universe universe = new Universe(10, 10, new Rule[] { new Rule(2, 3, "<", CellState.ALIVE, CellState.DEAD), new Rule(2, 3, ">", CellState.DEAD, CellState.ALIVE)});
        universe.nextGeneration();
        Assertions.assertTrue(universe.toString().contains("..."));
    }

    @Test
    public void getterTest() {
        Universe universe= new Universe(10, 10, new Rule[] { new Rule(2, 3, "<", CellState.ALIVE, CellState.DEAD), new Rule(2, 3, ">", CellState.ALIVE, CellState.DEAD)});
        Assertions.assertEquals(10,universe.getUniverse().length);
        Assertions.assertEquals(0, universe.getGenerations());
    }

}
