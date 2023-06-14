package inheritamon.model.pokemon.types;

import java.util.*;

/**
 * @author Jeremias
 * A class to represent the player's pokemon, awaits input from the
 * player
 */
public class PlayerPokemon extends Pokemon {

    private final int WAIT_TIME = 50;
    private volatile boolean awaitingMove = false;
    private String selectedMove;

    /**
     * Constructor for the PlayerPokemon class
     *
     * @param data The data of the pokemon
     */
    public PlayerPokemon(HashMap<String, String> data) {
        super(data);
    }

    /**
     * Uses a move according to the player's input
     */
    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        awaitingMove = true;

        while (awaitingMove) {
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Player selected " + selectedMove + "!");

        return selectedMove;
    }

    /**
     * Selects a move for the pokemon, called by the GUI
     *
     * @param move The move to select
     */
    public void selectMove(String move) {
        awaitingMove = false;
        selectedMove = move;
    }

}
