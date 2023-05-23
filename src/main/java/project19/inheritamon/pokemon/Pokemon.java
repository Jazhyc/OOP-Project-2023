package project19.inheritamon.pokemon;

public class Pokemon implements CombatEntity {

    public void attack(CombatEntity target) {
        System.out.println("Pokemon attack");
    }

    public Pokemon() {
        System.out.println("Pokemon constructor");
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    
}
