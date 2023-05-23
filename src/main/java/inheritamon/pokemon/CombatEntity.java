package inheritamon.pokemon;

import java.util.HashMap;

/**
 * An interface for all entities that can participate in combat. This includes the player pokemon as well as hostile ones
 * Only a single method is required, attack, which takes a target as a parameter
 */
public interface CombatEntity {

    /**
     * The attack method, which takes a target as a parameter
     * @param enemyStats The stats of the enemy
     * @return The name of the move used
     */
    public String useMove(HashMap<String, Integer> enemyStats);

    /**
     * A method to take damage
     * @param damage The amount of damage to take
     */
    public void takeDamage(int damage, int enemyAccuracy);


}
