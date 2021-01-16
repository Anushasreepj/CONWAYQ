package se.pe.game;

import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;


class Universe {
    @NonNull private int width, height;
    @Getter private Cell[][] universe;
    @Getter private int generations;
    private Rule[] rules;

    public Universe(int width, int height, Rule[] rules) {
        this.width = width;
        this.height = height;
        randomSeed();
        this.rules = rules;
    }

    private void randomSeed() {
        universe = new Cell[height][width];
        val random = new Random();

        loop(universe, (i, j) -> {
            universe[i][j] = new Cell(i, j, random.nextInt(2));
        });

    }

    public void nextGeneration() {
        var newPopulation = new Cell[height][width];

        loop(universe, (i, j) -> {
            Cell cell = universe[i][j];
            Optional<CellState> resultState = Optional.ofNullable(getNeigborsAndApplyRules(cell));

            if (resultState.isPresent()){
                newPopulation[i][j] = new Cell(i, j, resultState);
            } else{
                newPopulation[i][j] = new Cell(i, j, Optional.of(CellState.DEAD));
            }

        });

        universe = newPopulation;
        generations++;
    }



    private CellState getNeigborsAndApplyRules(Cell cell){
        for (int i = 0; i <rules.length ; i++) {
            val neighbors = getNeighborhood(cell, rules[i].getRadius());
            var result = rules[i].applyRule(cell, neighbors);

            if (result != CellState.NONE){
                return result;
            }
        }
        return null;
    }

    private Cell[] getNeighborhood(Cell cell, int radius) {
        var block = (radius * 2) + 1;
        var neighbors = new Cell[block * block];
        var index = 0;

        for (int i = cell.getRow()-radius; i <= cell.getRow()+radius ; i++) {
            for (int j = cell.getCol()-radius; j <= cell.getCol()+radius ; j++) {
                if (checkIfCellExists(i,j)){
                    neighbors[index] = universe[i][j];
                    index++;
                }
            }
        }
        return neighbors;
    }

    private boolean checkIfCellExists(int row, int col){
        if ((row >= 0) && (row < height) && (col >= 0) && (col < width)){
            return true;
        }
        // Cell does not exist
        return false;
    }


    private void loop(Cell[][] universe, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                consumer.accept(i, j);
            }
        }
    }

    public String toString(){
        var result = new String();
        for (int i = 0; i < universe.length ; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                if (universe[i][j].currentState.get() == CellState.ALIVE){
                    result += "*";
                } else {
                    result += ".";
                }
            }
            result += "\n";
        }
        return result;
    }

}
