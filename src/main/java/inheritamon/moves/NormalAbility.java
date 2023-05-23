package inheritamon.moves;

import inheritamon.pokemon.Pokemon;
import java.util.HashMap;

public class NormalAbility implements Ability {

    private HashMap<String, Integer> numericalStats = new HashMap<String, Integer>();
    private HashMap<String, String> stringStats = new HashMap<String, String>();

    public void useMove(Pokemon enemy, Pokemon user) {

        // Obtain the accuracy of the move
        int accuracy = user.getNumericalStat("Acc");
        
        System.out.println("The move " + stringStats.get("Name") + " was used!");

        String type = stringStats.get("Type");

        // Calculate the damage based on the user's attack and modifier depending on the type
        int modifier = numericalStats.get("Modifier");
        int atk = user.getNumericalStat("Atk");
        int mAtk = user.getNumericalStat("M.Atk");

        // Take a split of the attack and magic attack by default, used in the case of healing
        int damage = (atk + (mAtk * 2)) / 2 * modifier;

        if (type.equals("Physical")) {
            damage = atk * modifier;
        } else if (type.equals("Magical")) {
            damage = mAtk * modifier;
        } else if (type.equals("Healing")) {
            user.gainHP(damage);
            return;
        } else {
            System.out.println("Unknown type!");
        }
        

        enemy.takeDamage(damage, accuracy);

    }

    public void setUp(HashMap<String, String> moveData) {
        
        // Loop through the data and put it into the correct HashMap
        for (String key : moveData.keySet()) {
            try {
                numericalStats.put(key, Integer.parseInt(moveData.get(key)));
            } catch (NumberFormatException e) {
                stringStats.put(key, moveData.get(key));
            }
        }

    }

    public NormalAbility(HashMap<String, String> moveData) {
        setUp(moveData);
    }
    
}
