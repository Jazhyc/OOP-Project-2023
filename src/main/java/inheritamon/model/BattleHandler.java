package inheritamon.model;

import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.moves.*;
import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;
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
    private PropertyChangeListener dialogueListener;
    private final int WAIT_TIME = 1000;

    // Create an array of 2 for the statListeners
    private PropertyChangeListener[] statListeners = new PropertyChangeListener[2];

    // Same for the sprites
    private PropertyChangeListener[] spriteListeners = new PropertyChangeListener[2];

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

        notifyStatListener();

        // These only need to be executed when the pokemon changes
        notifyMoveListener();
        notifyPokemonSpriteListener();

        // Beginning of the battle
        notifyDialogueListener("A wild " + enemyPokemon.getName() + " appeared!");
        wait(WAIT_TIME);

        while (playerPokemon.getHP() > 0 && enemyPokemon.getHP() > 0) {

            // Print the HP and MP of both pokemon
            System.out.println(playerPokemon.getName() + " HP: " + playerPokemon.getHP() + " MP: " + playerPokemon.getMP());
            System.out.println(enemyPokemon.getName() + " HP: " + enemyPokemon.getHP() + " MP: " + enemyPokemon.getMP());
            System.out.println("--------------------------------------");

            attacker = (turn % 2 == 0) ? playerPokemon : enemyPokemon;
            defender = (turn % 2 == 0) ? enemyPokemon : playerPokemon;

            notifyDialogueListener("It's " + attacker.getName() + "'s turn!");
            wait(WAIT_TIME);

            // Get the ability to use
            ability = attacker.useMove(defender.getAllNumericalStats());

            // Check if the ability is not null
            if (ability == null) {
                System.out.println("Unknown Error");
                continue;
            }

            notifyDialogueListener(attacker.getName() + " used " + ability + "!");
            wait(WAIT_TIME);

            // Use the ability
            Integer damageDealt = moveData.get(ability).executeMove(defender, attacker);

            // Check the damage for display purposes
            checkDamage(attacker, damageDealt);
            
            notifyStatListener();
            wait(WAIT_TIME);

            System.out.println("--------------------------------------");

            turn++;

        }

        if (playerPokemon.getHP() <= 0) {
            notifyDialogueListener(playerPokemon.getName() + " fainted!");
            wait(WAIT_TIME);
            notifyDialogueListener("You lost!");
            return 1;
        } else if (enemyPokemon.getHP() <= 0) {
            notifyDialogueListener(enemyPokemon.getName() + " fainted!");
            wait(WAIT_TIME);
            notifyDialogueListener("You won!");
            return 2;
        }

        return 0;
    }

    private void checkDamage(Pokemon attacker, Integer damageDealt) {
        if (damageDealt == -1) {
            notifyDialogueListener("But " + attacker.getName() + " doesn't have enough MP!");
        } else if (damageDealt < 0) {
            notifyDialogueListener(attacker.getName() + " healed for " + Math.abs(damageDealt) + " HP!");
        } else if (damageDealt > 0) {
            notifyDialogueListener(attacker.getName() + " dealt " + damageDealt + " damage!");
        } else {
            notifyDialogueListener("But it missed!");
        }
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

    public void addStatListener(PropertyChangeListener listener) {
        
        // Add the listener to the array
        for (int i = 0; i < statListeners.length; i++) {
            if (statListeners[i] == null) {
                statListeners[i] = listener;
                break;
            }
        }

    }

    private void notifyStatListener() {

        // Get the stats for the player pokemon
        int[] playerStats = getPokemonDisplayStats(playerPokemon);
        statListeners[0].propertyChange(new PropertyChangeEvent(this, "playerStats", null, playerStats));

        // Get the stats for the enemy pokemon
        int[] enemyStats = getPokemonDisplayStats(enemyPokemon);
        statListeners[1].propertyChange(new PropertyChangeEvent(this, "enemyStats", null, enemyStats));
    }

    private int[] getPokemonDisplayStats(Pokemon pokemon) {
        int[] stats = new int[4];
        stats[0] = pokemon.getHP();
        stats[1] = pokemon.getNumericalStat("MaxHP");
        stats[2] = pokemon.getMP();
        stats[3] = pokemon.getNumericalStat("MaxMP");
        return stats;
    }

    public void addPokemonSpriteListener(PropertyChangeListener listener) {
        // Add the listener to the array
        for (int i = 0; i < spriteListeners.length; i++) {
            if (spriteListeners[i] == null) {
                spriteListeners[i] = listener;
                break;
            }
        }
    }

    private void notifyPokemonSpriteListener() {
        spriteListeners[0].propertyChange(new PropertyChangeEvent(this, "playerSprite", null, playerPokemon.getName()));
        spriteListeners[1].propertyChange(new PropertyChangeEvent(this, "enemySprite", null, enemyPokemon.getName()));
    }

    public void addDialogueListener(PropertyChangeListener listener) {
        this.dialogueListener = listener;
    }

    private void notifyDialogueListener(String dialogue) {
        dialogueListener.propertyChange(new PropertyChangeEvent(this, "dialogue", null, dialogue));
    }

    public String getCurrentPokemonName(DisplayType type) {
        if (type == DisplayType.PLAYER) {
            return playerPokemon.getName();
        } else {
            return enemyPokemon.getName();
        }
    }

    public PlayerPokemon getActivePlayerPokemon() {
        return (PlayerPokemon) playerPokemon;
    }

    private void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
