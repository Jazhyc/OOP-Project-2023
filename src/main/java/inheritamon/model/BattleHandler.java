package inheritamon.model;

import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.moves.*;
import inheritamon.model.data.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

/**
 * A class to handle battles, takes the player's pokemon and the enemy pokemon as parameters
 */
public class BattleHandler {

    // Reference to required objects
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private HashMap<String, NormalAbility> moveData;

    private PropertyChangeListener moveListener;

    /**
     * The constructor for the battle handler
     * @param playerPokemon The player's pokemon
     * @param enemyPokemon The enemy pokemon
     */
    public BattleHandler(Pokemon playerPokemon, Pokemon enemyPokemon) {
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;
        this.moveData = DataHandler.getInstance().getAllAbilities();
    }

    /**
     * A method to start the battle
     * @return The result of the battle, can be victory, defeat or a draw
     * 1 = defeat, 2 = victory, 0 = draw
     */
    public int startBattle() {

        int turn = 0;
        String ability;
        Pokemon attacker;
        Pokemon defender;

        // This only needs to be executed when the pokemon changes
        notifyMoveListener();

        while (playerPokemon.getHP() > 0 && enemyPokemon.getHP() > 0) {

            // Print the HP and MP of both pokemon
            System.out.println(playerPokemon.getName() + " HP: " + playerPokemon.getHP() + " MP: " + playerPokemon.getMP());
            System.out.println(enemyPokemon.getName() + " HP: " + enemyPokemon.getHP() + " MP: " + enemyPokemon.getMP());
            System.out.println("--------------------------------------");

            attacker = (turn % 2 == 0) ? playerPokemon : enemyPokemon;
            defender = (turn % 2 == 0) ? enemyPokemon : playerPokemon;

            // Get the ability to use
            ability = attacker.useMove(defender.getAllNumericalStats());

            // Check if the ability is not null
            if (ability == null) {
                System.out.println("Unknown Error");
                continue;
            }

            // Use the ability
            moveData.get(ability).useMove(defender, attacker);

            System.out.println("--------------------------------------");

            turn++;

        }

        if (playerPokemon.getHP() <= 0) {
            System.out.println("You lost!");
            return 1;
        } else if (enemyPokemon.getHP() <= 0) {
            System.out.println("You won!");
            return 2;
        }

        return 0;
    }

    public void addMoveListener(PropertyChangeListener listener) {
        this.moveListener = listener;
    }

    private void notifyMoveListener() {
        
        // Create a string array of the moves
        String[] moves = new String[playerPokemon.getMoves().size()];
        for (int i = 0; i < playerPokemon.getMoves().size(); i++) {
            moves[i] = playerPokemon.getMoves().get(i);
        }

        moveListener.propertyChange(new PropertyChangeEvent(this, "moves", null, moves));
    }

    public PlayerPokemon getActivePlayerPokemon() {
        return (PlayerPokemon) playerPokemon;
    }
    
}
