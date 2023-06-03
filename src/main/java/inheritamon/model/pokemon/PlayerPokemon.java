package inheritamon.model.pokemon;

import java.util.*;

public class PlayerPokemon extends Pokemon {

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
                Thread.sleep(100);
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
    

