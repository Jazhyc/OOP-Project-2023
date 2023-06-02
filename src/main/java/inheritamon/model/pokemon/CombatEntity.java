package inheritamon.model.pokemon;

import java.util.HashMap;

/**
 * An interface for all entities that can participate in combat. This includes the player pokemon as well as hostile ones
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

    /**
     * A method to gain HP
     */
    public void gainHP(int hp);

    /**
     * A method to gain MP from using items
     */
    public void gainMP(int mp);

    /**
     * A method to lose MP for using abilities
     */
    public void loseMP(int mp);

    /**
     * A method to fully restore HP and MP
     */
    public void revitalize();


}
