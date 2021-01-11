package se.pe.game;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RuleTest {

    @Test
    public void applyRuleTest() {
        Cell currentCell = new Cell(1, 1, 1);
        Cell[] neighborCells = new Cell[] { new Cell(1, 2, 0), new Cell(2, 1, 1), new Cell(2, 2, 2) };

        // For Operator <
        Rule lessthanRule = new Rule(2, 3, "<", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.DEAD, lessthanRule.applyRule(currentCell, neighborCells));

        // For Operator >
        Rule greaterthanRule = new Rule(lessthanRule.getRadius(), 3, ">", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.NONE, greaterthanRule.applyRule(currentCell, neighborCells));

        // For Operator <=
        Rule lessThanEqualRule = new Rule(2, 0, "<=", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.DEAD, lessThanEqualRule.applyRule(currentCell, neighborCells));

        // For Operator >=
        Rule greaterThanEqualRule = new Rule(2, 1, ">=", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.NONE, greaterThanEqualRule.applyRule(currentCell, neighborCells));

        // For Operator ==
        Rule equalRule = new Rule(2, 1, "==", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.NONE, equalRule.applyRule(currentCell, neighborCells));

        Rule equalRule2 = new Rule(3, 0, "==", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.DEAD, equalRule2.applyRule(currentCell, neighborCells));

        // For Operator !=
        Rule notEqualRule = new Rule(2, 1, "!=", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.DEAD, notEqualRule.applyRule(currentCell, neighborCells));

        // For invalid Operator
        Rule invalidOperatorRule = new Rule(2, 5, "===", CellState.ALIVE, CellState.DEAD);
        Assertions.assertEquals(CellState.NONE, invalidOperatorRule.applyRule(currentCell, neighborCells));

    }

}
