package inheritamon.model.pokemon.types;

import java.util.*;

import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A class to represent a pokemon, does not possess an AI
 */
public abstract class Pokemon {

    protected HashMap<String, Integer> numericalStats = new HashMap<String, Integer>();
    protected HashMap<String, String> stringStats = new HashMap<String, String>();
    protected ArrayList<String> moves = new ArrayList<String>();
    private boolean hasFainted = false;

    private int BASE_DODGE_CHANCE = 20;

    /**
     * The attack method, which takes a target as a parameter, returns a random move
     * @param targetStats The stats of the target
     * @return The name of the move used
     */
    public abstract String useMove(HashMap<String, Integer> targetStats);

    /**
     * A method to set the stats of the pokemon
     * @param pokemonData The data of the pokemon
     */
    public void setUp(HashMap<String, String> pokemonData) {

        // Obtain the moves and put them into the moves ArrayList
        moves = DataHandler.convertMoveSetToString(pokemonData.get("MoveSet"));

        // Remove the moves from the pokemonData HashMap
        pokemonData.remove("MoveSet");

        // Loop through the data and put it into the correct HashMap
        for (String key : pokemonData.keySet()) {
            String value = pokemonData.get(key);
            if (DataHandler.isNumeric(value)) {
                numericalStats.put(key, Integer.parseInt(value));
            } else {
                stringStats.put(key, value);
            }
        }

        // Add current HP and MP to the numericalStats HashMap and set them to the max
        // Also add a field for the species name which is the same as name
        numericalStats.put("HP", numericalStats.get("MaxHP"));
        numericalStats.put("MP", numericalStats.get("MaxMP"));
        stringStats.put("Species", stringStats.get("Name"));
        
    }

    /**
     * Constructor for the Pokemon class
     * @param pokemonData
     */
    public Pokemon(HashMap<String, String> pokemonData) {
        setUp(pokemonData);
    }

    /**
     * A method to take damage, takes agility and defense into account
     * @param damage The amount of damage to take
     * @param enemyAccuracy The accuracy of the enemy
     * @return The amount of damage taken after defense and agility are taken into account
     */
    public int takeDamage(int damage, int enemyAccuracy) {

        // Calculate the chance to dodge, simple addition
        int chanceToDodge = numericalStats.get("Agi") - enemyAccuracy + BASE_DODGE_CHANCE;
        System.out.println(stringStats.get("Name") + " has a " + chanceToDodge + "% chance to dodge");

        // Generate a random number between 0 and 100, if the number is lower than the chance to dodge, set the damage to zero
        if ((int) (Math.random() * 100) < chanceToDodge) {
            damage = 0;
            System.out.println(stringStats.get("Name") + " dodged the attack!");
        }

        // Calculate the damage to take, minimum 0
        int damageToTake = damage - numericalStats.get("Def");

        // If the damage to take is less than 0, set it to 0
        if (damageToTake < 0) {
            damageToTake = 0;
            System.out.println(stringStats.get("Name") + " took no damage!");
        } else {
            System.out.println(stringStats.get("Name") + " took " + damageToTake + " damage!");
        }

        // Take the damage
        numericalStats.put("HP", numericalStats.get("HP") - damageToTake);

        // If the pokemon has fainted, call the faint method
        if (numericalStats.get("HP") <= 0) {
            faint();
        }

        // Return the damage taken
        return damageToTake;

    }

    /**
     * A method to lose MP, caps at 0
     * The cap would be useful in the case of drain attacks
     * @param mp The amount of MP to gain
     */
    public void loseMP(int mp) {
        numericalStats.put("MP", numericalStats.get("MP") - mp);
        if (numericalStats.get("MP") < 0) {
            numericalStats.put("MP", 0);
        }
    }

    /**
     * A method to gain MP, caps at max MP
     * @param mp The amount of MP to gain
     */
    public void gainHP(int hp) {

        System.out.println(stringStats.get("Name") + " gained " + hp + " HP!");

        numericalStats.put("HP", numericalStats.get("HP") + hp);
        if (numericalStats.get("HP") > numericalStats.get("MaxHP")) {
            numericalStats.put("HP", numericalStats.get("MaxHP"));
        }
    }

    /**
     * A method to gain MP, caps at max MP
     * @param mp The amount of MP to gain
     */
    public void gainMP(int mp) {

        System.out.println(stringStats.get("Name") + " gained " + mp + " MP!");

        numericalStats.put("MP", numericalStats.get("MP") + mp);
        if (numericalStats.get("MP") > numericalStats.get("MaxMP")) {
            numericalStats.put("MP", numericalStats.get("MaxMP"));
        }
    }

    /**
     * A method to revitalize the pokemon, sets HP and MP to max
     * Could be used in the case of a revive item or healing machine
     */
    public void revitalize() {

        System.out.println(stringStats.get("Name") + " was revitalized!");

        numericalStats.put("HP", numericalStats.get("MaxHP"));
        numericalStats.put("MP", numericalStats.get("MaxMP"));

        hasFainted = false;
    }

    private void faint() {
        System.out.println(stringStats.get("Name") + " has fainted!");
        hasFainted = true;
    }

    /**
     * A method to get a particular integer stat
     * @param statName The name of the stat
     * @return The value of the stat
     */
    public Integer getNumericalStat(String statName) {

        return numericalStats.get(statName);
    }

    /**
     * A method to directly get the current HP
     * @return The HP of the pokemon
     */
    public Integer getHP() {
        return numericalStats.get("HP");
    }

    /**
     * A method to directly get the current MP
     * @return The MP of the pokemon
     */
    public Integer getMP() {
        return numericalStats.get("MP");
    }

    /**
     * A method to directly get the Name / Species of the pokemon
     * @return The name of the pokemon
     */
    public String getName() {
        return stringStats.get("Name");
    }

    /**
     * A method to get the moves of the pokemon
     * @return The moves of the pokemon
     */
    public ArrayList<String> getMoves() {
        return moves;
    }

    /**
     * A method to get all stats of a pokemon
     * @return The values of all stats
     */
    public HashMap<String, Integer> getAllNumericalStats() {

        return new HashMap<String, Integer>(numericalStats);
    }

    public boolean isFainted() {
        return hasFainted;
    }

    public String getLoot() {
        return stringStats.get("Loot");
    }
    
}
