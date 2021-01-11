package se.pe.game;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Optional;


@Getter
@Setter
public class Cell {

     @NonNull  private int row, col;
     Optional<CellState> currentState;

    public Cell(int row, int col, Optional<CellState> state) {
        this.row = row;
        this.col = col;
        currentState = state;
    }

    public Cell(int row, int col, int state){
        this.row = row;
        this.col = col;
        currentState = Optional.ofNullable(CellState.values()[state]);
    }
}
