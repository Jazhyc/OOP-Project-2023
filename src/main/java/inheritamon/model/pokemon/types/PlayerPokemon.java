package inheritamon.model.pokemon.types;

import java.util.*;

public class PlayerPokemon extends Pokemon {

    private static final int WAIT_TIME = 50;
    private volatile boolean awaitingMove = false;
    private String selectedMove;

    public PlayerPokemon(HashMap<String, String> data) {
        super(data);
    }

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

    public void selectMove(String move) {
        awaitingMove = false;
        selectedMove = move;
    }
        
}
    

