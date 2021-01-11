package se.pe.game;

import java.util.Optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellTest {

    /*
     * To Test cell, where CellState is provided as Enum
     */
    @Test
    public void cellWithActualStateValues() {

        Cell cellWithAliveState = new Cell(1, 1, Optional.of(CellState.ALIVE));
        Assertions.assertEquals(cellWithAliveState.getCurrentState().get(), CellState.ALIVE);

        Cell cellWithDeadState = new Cell(1, 1, Optional.of(CellState.DEAD));
        Assertions.assertEquals(cellWithDeadState.getCurrentState().get(), CellState.DEAD);

    }

    /*
     * To Test cell, where CellState is provided as int
     */
    @Test
    public void cellWithState() {
        Cell cellWithAliveState = new Cell(1, 1, 1);
        Assertions.assertEquals(cellWithAliveState.getCurrentState().get(), CellState.ALIVE);

        Cell cellWithDeadState = new Cell(1, 1, 2);
        Assertions.assertEquals(cellWithDeadState.getCurrentState().get(), CellState.NONE);

        // Test when CellState integer is not present
        try {

        }catch(ArrayIndexOutOfBoundsException e) {
            Assertions.assertTrue(e.getMessage()!=null);
        }

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new Cell(cellWithAliveState.getRow(), cellWithAliveState.getCol(), 3);
        });
    }


    // For Testing Setter methods
    @Test
    public void basicCellTest() {
        Cell cell=new Cell(1, 1, 1);
        cell.setRow(4);
        cell.setCol(5);
        Assertions.assertEquals(4, cell.getRow());
        Assertions.assertEquals(5, cell.getCol());
    }

}
