package inheritamon.pokemon;

/**
 * An interface for all entities that can participate in combat. This includes the player pokemon as well as hostile ones
 * Only a single method is required, attack, which takes a target as a parameter
 */
public interface CombatEntity {

    /**
     * The attack method, which takes a target as a parameter
     * @param target The target pokemon of the attack
     */
    public void attack(CombatEntity target);

}
