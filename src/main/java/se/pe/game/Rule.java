package se.pe.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Rule {
    @Getter private int radius;
    private int requiredLiveNeighbors;
    String operator;
    private CellState initialState, resultingState;

    public CellState applyRule(Cell current, Cell[] neighborHood){
        if (current.currentState.get() == initialState){
            boolean currentIsAlive = false;
            if (current.currentState.get() == CellState.ALIVE){
                currentIsAlive = true;
            }
            int livingNeighbors = countAliveNeighbors(currentIsAlive, neighborHood);
            switch (operator){
                case "<" ->{return livingNeighbors < requiredLiveNeighbors ? resultingState: CellState.NONE;}
                case ">" ->{return livingNeighbors > requiredLiveNeighbors ? resultingState: CellState.NONE;}
                case "<=" ->{return livingNeighbors <= requiredLiveNeighbors ? resultingState: CellState.NONE;}
                case ">=" ->{return livingNeighbors >= requiredLiveNeighbors ? resultingState: CellState.NONE;}
                case "==" ->{return livingNeighbors == requiredLiveNeighbors ? resultingState: CellState.NONE;}
                case "!=" ->{return livingNeighbors != requiredLiveNeighbors ? resultingState: CellState.NONE;}
            }
        }
        // No Rule was applied to the cell
        return CellState.NONE;
    }

    private int countAliveNeighbors(boolean currentIsAlive, Cell[] neighborHood){
        int livingNeighbors = 0;

        for (int i = 0; i < neighborHood.length ; i++) {
            if (neighborHood[i] != null) {
                if (neighborHood[i].currentState.get() == CellState.ALIVE){
                    livingNeighbors++;
                }
            }
        }

        if (currentIsAlive) livingNeighbors--;
        return livingNeighbors;
    }

}
