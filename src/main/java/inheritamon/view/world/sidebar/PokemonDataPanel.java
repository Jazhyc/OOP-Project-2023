package inheritamon.view.world.sidebar;

import inheritamon.model.GameModel;
import inheritamon.model.pokemon.types.PlayerPokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class PokemonDataPanel extends JPanel {

    public PokemonDataPanel(GameModel gameModel) {
        
        // Make the background black
        setBackground(Color.BLACK);
        setUpListener(gameModel);

        // Use a grid bag layout
        setLayout(new GridBagLayout());

    }

    private void setUpListener(GameModel gameModel) {
        
        gameModel.addRosterListener(e -> {

            PlayerPokemon[] roster = (PlayerPokemon[]) e.getNewValue();

            // Remove all the components from the panel
            removeAll();

            // Loop over each pokemon and get the image of their species and their name
            for (int i = 0; i < roster.length; i++) {

                PlayerPokemon pokemon = roster[i];

                System.out.println(pokemon.getName());

                // Use a gbc to position the components
                // Images are on the left, names on the right
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 0.5;
                gbc.weighty = 0.5;
                gbc.anchor = GridBagConstraints.LINE_START;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.gridy = i;
                
                gbc.gridx = 0;
                BufferedImage pokemonImage = pokemon.getSpeciesImage();
                JLabel imageLabel = new JLabel(new ImageIcon(pokemonImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                add(imageLabel, gbc);

                // Configure the name
                gbc.gridx = 1;
                addPokemonName(pokemon, gbc);

                // Create a ratio of HP to MaxHP
                addHPRatio(pokemon, gbc);

                // Create a ratio of MP to MaxMP
                addMPRatio(pokemon, gbc);


            }


        });
    }

    private void addPokemonName(PlayerPokemon pokemon, GridBagConstraints gbc) {
        JLabel pokemonName = new JLabel(pokemon.getName());
        pokemonName.setForeground(Color.WHITE);
        pokemonName.setHorizontalAlignment(JLabel.CENTER);
        pokemonName.setFont(new Font("Arial", Font.BOLD, 20));
        add(pokemonName, gbc);
    }

    private void addHPRatio(PlayerPokemon pokemon, GridBagConstraints gbc) {
        gbc.gridx = 2;
        Integer pokemonHP = pokemon.getHP();
        Integer pokemonMaxHP = pokemon.getNumericalStat("MaxHP");
        JLabel hpString = new JLabel(pokemonHP.toString() + "/" + pokemonMaxHP.toString());
        add(hpString, gbc);
        hpString.setForeground(Color.GREEN);
        hpString.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void addMPRatio(PlayerPokemon pokemon, GridBagConstraints gbc) {
        gbc.gridx = 3;
        Integer pokemonMP = pokemon.getMP();
        Integer pokemonMaxMP = pokemon.getNumericalStat("MaxMP");
        JLabel mpString = new JLabel(pokemonMP.toString() + "/" + pokemonMaxMP.toString());
        add(mpString, gbc);
        mpString.setForeground(Color.BLUE);
        mpString.setFont(new Font("Arial", Font.BOLD, 20));
    }
    
}
