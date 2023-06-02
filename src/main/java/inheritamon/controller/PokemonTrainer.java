package inheritamon.controller;
public abstract class PokemonTrainer implements Player {
    private String username;
    private TrainerRegion region;
    private Language language;
    private TrainerAbility ability;
    PokemonTrainer(String username, TrainerRegion region, Language language) {
        this.username = username;
        this.region = region;
        this.language = language;
        loadDialogueOptions();
    }
    public String getUsername() {
        return username;
    }
    public TrainerRegion getRegion() {
        return region;
    }
    public void setLanguage(Language language) {
        this.language = language;
        loadDialogueOptions();
    }
    public Language getLanguage() {
        return language;
    }

    public void setTrainerAbility() {}

    public void loadDialogueOptions() {

    }

}
