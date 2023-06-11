package inheritamon.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import inheritamon.model.data.DataHandler;
import inheritamon.model.data.language.LanguageConfiguration;
import inheritamon.model.inventory.Inventory;
import inheritamon.model.inventory.Item;
import inheritamon.model.inventory.PlayerRoster;
import inheritamon.model.pokemon.moves.NormalAbility;
import inheritamon.model.pokemon.types.PlayerPokemon;
import inheritamon.model.pokemon.types.Pokemon;
import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;

/**
 * @author Jeremias
 * A class to handle battles, takes the player and the enemy pokemon as parameters
 */
public class BattleHandler {

    private HashMap<String, NormalAbility> moveData;

    private PropertyChangeListener moveListener;
    private PropertyChangeListener dialogueListener;
    private PropertyChangeListener playerRosterListener;
    private PropertyChangeListener inventoryListener;
    private PropertyChangeListener battleStateListener;
    private final int WAIT_TIME = 1000;

    // Create an array of 2 for the statListeners
    private PropertyChangeListener[] statListeners = new PropertyChangeListener[2];

    // Same for the sprites
    private PropertyChangeListener[] spriteListeners = new PropertyChangeListener[2];

    private PlayerPokemon playerPokemon;
    private Pokemon enemyPokemon;
    private PlayerRoster playerRoster;

    private Inventory playerInventory;
    private int turn;

    /**
     * The constructor for the battle handler
     */
    public BattleHandler() {
        this.moveData = DataHandler.getInstance().getAllAbilities();
    }

    /**
     * A method to start the battle on a different thread
     * @return The result of the battle, can be victory, defeat or a draw
     * 1 = defeat, 2 = victory, 0 = draw
     */
    public void startBattle(PlayerData playerData, Pokemon enemyPokemon) {

        // Create a new thread
        Thread battleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                battleLoop(playerData, enemyPokemon);
            }
        });

        // Start the thread
        battleThread.start();

    }

    private void battleLoop(PlayerData playerData, Pokemon enemyPokemon) {

        turn = 0;
        String ability;
        Pokemon attacker;
        Pokemon defender;

        this.playerRoster = playerData.getRoster();

        int playerPokemonIndex = playerRoster.getAlivePokemon();
        playerPokemon = playerRoster.getPokemon(playerPokemonIndex);
        this.enemyPokemon = enemyPokemon;
        this.playerInventory = playerData.getInventory();

        notifyStatListener(playerPokemon, enemyPokemon);
        notifyMoveListener(playerPokemon);
        notifyPokemonSpriteListener(playerPokemon, enemyPokemon);
        notifyPlayerRosterListener();
        notifyInventoryListener();
        notifyBattleStateListener("Start");

        LanguageConfiguration config = LanguageConfiguration.getInstance();

        // Beginning of the battle
        // Get the BattleStart string from language config
        String formattedString = String.format(config.getText("BattleStart"), enemyPokemon.getName());
        notifyDialogueListener(formattedString);
        wait(WAIT_TIME);

        while (!playerRoster.allFainted() && enemyPokemon.getHP() > 0) {

            // Print the HP and MP of both pokemon
            System.out.println(playerPokemon.getName() + " HP: " + playerPokemon.getHP() + " MP: " + playerPokemon.getMP());
            System.out.println(enemyPokemon.getName() + " HP: " + enemyPokemon.getHP() + " MP: " + enemyPokemon.getMP());
            System.out.println("--------------------------------------");

            attacker = (turn % 2 == 0) ? playerPokemon : enemyPokemon;
            defender = (turn % 2 == 0) ? enemyPokemon : playerPokemon;

            formattedString = String.format(config.getText("TurnStart"), attacker.getName());
            notifyDialogueListener(formattedString);

            // Only wait if it's not the player's turn
            if (turn % 2 != 0) {
                wait(WAIT_TIME);
            }

            // Get the ability to use
            ability = attacker.useMove(defender.getAllNumericalStats());

            // Check if the ability is Run
            if (ability.equals("Run")) {
                formattedString = String.format(config.getText("Run"));
                notifyDialogueListener(formattedString);
                wait(WAIT_TIME);
                notifyBattleStateListener("Draw");
                return;
            }

            // Add item functionality
            if (ability.startsWith("item")) {
                handleItemUse(ability);
                continue;
            }

            // Checked if the ability returned starts with switch
            if (ability.startsWith("switch")) {

                getPokemonToSwitchTo(playerRoster, enemyPokemon, ability);
                continue;
            }

            String localAbilityName = config.getLocalMoveName(ability);
            formattedString = String.format(config.getText("Attack"), attacker.getName(), localAbilityName);
            notifyDialogueListener(formattedString);
            wait(WAIT_TIME);

            // Use the ability
            Integer damageDealt = moveData.get(ability).executeMove(defender, attacker);

            // Check the damage for display purposes
            checkDamage(attacker, damageDealt);
            
            notifyStatListener(playerPokemon, enemyPokemon);
            wait(WAIT_TIME);

            // If the player pokemon fainted, notify the listeners
            if (playerPokemon.isFainted()) {
                handleFaint(playerRoster, enemyPokemon, config);
                continue;
            }

            System.out.println("--------------------------------------");

            turn++;

        }

        String conclusion;

        conclusion = determineConclusion(playerRoster, enemyPokemon);
        notifyBattleStateListener(conclusion);

        // Notify a listener in the model here
    }

    private void handleFaint(PlayerRoster playerRoster, Pokemon enemyPokemon, LanguageConfiguration config) {
        String formattedString;
        formattedString = String.format(config.getText("Fainted"), playerPokemon.getName());
        notifyDialogueListener(formattedString);
        wait(WAIT_TIME);
        
        // Get the next pokemon if there is one
        if (!playerRoster.allFainted()) {
            getPokemonToSwitchTo(playerRoster, enemyPokemon, "switch" + playerRoster.getAlivePokemon());
        }

        // Notify the roster listener
        notifyPlayerRosterListener();
    }

    private String determineConclusion(PlayerRoster playerRoster, Pokemon enemyPokemon) {

        String formattedString;
        LanguageConfiguration config = LanguageConfiguration.getInstance();
        
        String conclusion;
        if (enemyPokemon.getHP() <= 0) {
            formattedString = String.format(config.getText("Fainted"), enemyPokemon.getName());
            notifyDialogueListener(formattedString);
            wait(WAIT_TIME);
            notifyDialogueListener(config.getText("Victory"));
            wait(WAIT_TIME);
            handleLoot();
            conclusion = "Victory";
        } else if (playerRoster.allFainted()) {
            notifyDialogueListener(config.getText("AllFainted"));
            wait(WAIT_TIME);
            notifyDialogueListener(config.getText("Defeat"));
            conclusion = "Defeat";
        } else {
            conclusion = "Draw";
        }

        wait(WAIT_TIME);
        return conclusion;
    }

    private void handleLoot() {

        DataHandler dataHandler = DataHandler.getInstance();
        LanguageConfiguration config = LanguageConfiguration.getInstance();

        // Get the loot from the enemy pokemon
        String loot = enemyPokemon.getLoot();
        Integer coins = enemyPokemon.getNumericalStat("Coins");
        Item item = new Item(dataHandler.getItemData(loot));
        playerInventory.addItem(item);
        playerInventory.addCoins(coins);

        String formattedString = String.format(config.getText("Loot"), item.getItemName(), coins);
        notifyDialogueListener(formattedString);
        wait(WAIT_TIME);

    }

    private void getPokemonToSwitchTo(PlayerRoster playerRoster, Pokemon enemyPokemon, String ability) {
        System.out.println(ability);

        // Get the pokemon index to switch to using regex
        int pokemonToSwitchTo = Integer.parseInt(ability.replaceAll("[^0-9]", ""));
        playerPokemon = playerRoster.getPokemon(pokemonToSwitchTo);

        // Notify the listeners
        notifyMoveListener(playerPokemon);
        notifyPokemonSpriteListener(playerPokemon, enemyPokemon);
        notifyStatListener(playerPokemon, enemyPokemon);

        LanguageConfiguration config = LanguageConfiguration.getInstance();
        String formattedString = String.format(config.getText("Switch"), playerPokemon.getName());
        notifyDialogueListener(formattedString);
        wait(WAIT_TIME);

        // Skip the rest of the turn
        turn++;
    }

    private void checkDamage(Pokemon attacker, Integer damageDealt) {

        LanguageConfiguration config = LanguageConfiguration.getInstance();
        String formattedString;

        if (damageDealt == -1) {
            formattedString = String.format(config.getText("LackOfMP"), attacker.getName());
            notifyDialogueListener(formattedString);
        } else if (damageDealt < 0) {
            formattedString = String.format(config.getText("Heal"), attacker.getName(), Math.abs(damageDealt));
            notifyDialogueListener(formattedString);
        } else if (damageDealt > 0) {
            formattedString = String.format(config.getText("Damage"), attacker.getName(), damageDealt);
            notifyDialogueListener(formattedString);
        } else {
            formattedString = String.format(config.getText("Miss"), attacker.getName());
            notifyDialogueListener(formattedString);
        }

    }

    private void handleItemUse(String ability) {


        // Get the item index to use using regex
        int itemToUse = Integer.parseInt(ability.replaceAll("[^0-9]", ""));

        // Get the item
        Item item = playerInventory.getItem(itemToUse);

        // Use the item
        item.useItem(enemyPokemon, playerPokemon);

        // Remove the item from the inventory
        playerInventory.removeItem(itemToUse);

        // Notify the listeners
        notifyInventoryListener();
        notifyStatListener(playerPokemon, enemyPokemon);

        LanguageConfiguration config = LanguageConfiguration.getInstance();
        String formattedString = String.format(config.getText("Item"), item.getItemName());
        notifyDialogueListener(formattedString);
        notifyInventoryListener();
        wait(WAIT_TIME);

        // Skip the rest of the turn
        turn++;

    }

    private int[] getPokemonDisplayStats(Pokemon pokemon) {
        int[] stats = new int[4];
        stats[0] = pokemon.getHP();
        stats[1] = pokemon.getNumericalStat("MaxHP");
        stats[2] = pokemon.getMP();
        stats[3] = pokemon.getNumericalStat("MaxMP");
        return stats;
    }

    private void addListener(PropertyChangeListener[] listeners, PropertyChangeListener newListener) {
        // Add the listener to the array
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == null) {
                listeners[i] = newListener;
                break;
            }
        }
    }

    public void addListener(String listenerType, PropertyChangeListener listener) {
        switch (listenerType) {
            case "pokemonSprite":
                addListener(spriteListeners, listener);
                break;
            case "stat":
                addListener(statListeners, listener);
                break;
            case "playerRoster":
                this.playerRosterListener = listener;
                break;
            case "inventory":
                this.inventoryListener = listener;
                break;
            case "battleState":
                this.battleStateListener = listener;
                break;
            case "dialogue":
                this.dialogueListener = listener;
                break;
            case "moves":
                this.moveListener = listener;
                break;
            default:
                throw new IllegalArgumentException("Invalid listener type: " + listenerType);
        }
    }

    private void notifyDialogueListener(String dialogue) {
        dialogueListener.propertyChange(new PropertyChangeEvent(this, "dialogue", null, dialogue));
    }

    private void notifyPokemonSpriteListener(Pokemon playerPokemon, Pokemon enemyPokemon) {
        spriteListeners[0].propertyChange(new PropertyChangeEvent(this, "playerSprite", null, playerPokemon.getName()));
        spriteListeners[1].propertyChange(new PropertyChangeEvent(this, "enemySprite", null, enemyPokemon.getName()));
    }

    private void notifyMoveListener(Pokemon playerPokemon) {
        
        // Create a string array of the moves
        String[] moves = new String[playerPokemon.getMoves().size()];
        for (int i = 0; i < playerPokemon.getMoves().size(); i++) {
            moves[i] = playerPokemon.getMoves().get(i);
        }

        moveListener.propertyChange(new PropertyChangeEvent(this, "moves", null, moves));
    }

    private void notifyStatListener(Pokemon playerPokemon, Pokemon enemyPokemon) {

        // Get the stats for the player pokemon
        int[] playerStats = getPokemonDisplayStats(playerPokemon);
        statListeners[0].propertyChange(new PropertyChangeEvent(this, "playerStats", null, playerStats));

        // Get the stats for the enemy pokemon
        int[] enemyStats = getPokemonDisplayStats(enemyPokemon);
        statListeners[1].propertyChange(new PropertyChangeEvent(this, "enemyStats", null, enemyStats));
    }

    private void notifyPlayerRosterListener() {

        // Create a pokemon array of the pokemon
        Pokemon[] playerRosterArray = playerRoster.getRoster();

        playerRosterListener.propertyChange(new PropertyChangeEvent(this, "playerInventory", null, playerRosterArray));
    }
    private void notifyInventoryListener() {
        // Create a copy of the player's inventory
        Inventory inventory = playerInventory;
        inventoryListener.propertyChange(new PropertyChangeEvent(this, "playerRoster", null, inventory));
    }

    private void notifyBattleStateListener(String conclusion) {
        battleStateListener.propertyChange(new PropertyChangeEvent(this, "conclusion", null, conclusion));
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

    public boolean isPlayerTurn() {
        
        return turn % 2 == 0;

    }

}
