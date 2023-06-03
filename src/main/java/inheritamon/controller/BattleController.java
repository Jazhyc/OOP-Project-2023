package inheritamon.controller;

import inheritamon.model.BattleHandler;
import inheritamon.model.pokemon.PlayerPokemon;

public class BattleController {

    private BattleHandler battleHandler;
    
    public BattleController(BattleHandler battleHandler) {
        this.battleHandler = battleHandler;
    }

    public void selectChoice(String choice) {
        System.out.println("You selected " + choice);
    }

    public void selectMove(String move) {
       
        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove(move);

    }

}
