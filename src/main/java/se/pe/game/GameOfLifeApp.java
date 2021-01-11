package se.pe.game;

import lombok.val;


class GameOfLifeApp {
    private final static int MAX_GENERATION = 1000;

    public static void main(String[] args) {
        val rules = new Rule[5];

        // Any live cell with fewer than two live neighbours dies, as if by underpopulation.
        rules[0] = new Rule(1, 2, "<", CellState.ALIVE, CellState.DEAD);
        // Any live cell with more than three live neighbours dies, as if by overpopulation.
        rules[1] = new Rule(1, 3, ">", CellState.ALIVE, CellState.DEAD);
        // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        rules[2] = new Rule(1, 3, "==", CellState.DEAD, CellState.ALIVE);
        // Any live cell with two OR three live neighbours lives on to the next generation.
        rules[3] = new Rule(1, 2, "==", CellState.ALIVE, CellState.ALIVE);
        rules[4] = new Rule(1, 3, "==", CellState.ALIVE, CellState.ALIVE);

        Universe universe = new Universe(100,15, rules);
        run(universe, MAX_GENERATION);
    }

    public static void run(Universe universe, int maxGeneration) {
        int numberOfGenerations = 0;
        while (numberOfGenerations < maxGeneration) {
            render(universe);
            universe.nextGeneration();

            // Using thread only for printing out the game simultaneously
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numberOfGenerations++;
        }
    }

    public static void render(Universe universe){
        System.out.println("Generation: "+ universe.getGenerations());
        System.out.println(universe.toString());
    }
}
