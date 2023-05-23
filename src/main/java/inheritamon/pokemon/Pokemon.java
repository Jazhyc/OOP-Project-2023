package inheritamon.pokemon;

public class Pokemon implements CombatEntity {

    public void attack(CombatEntity target) {
        System.out.println("Pokemon attack");
    }

    public Pokemon() {
        System.out.println("Pokemon constructor");
    }
    
}
