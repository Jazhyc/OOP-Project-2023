package inheritamon.view.world.sidebar;

import inheritamon.model.GameModel;
import inheritamon.model.pokemon.types.PlayerPokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * @Author Jeremias
 * Panel for displaying certain stats of the player's pokemon data
 */
public class PokemonDataPanel extends JPanel {

    private String[] statNames =
            new String[] {"Atk", "Def", "Agi", "Acc", "M.Atk", "M.Def"};

    /**
     * Constructor for the PokemonDataPanel
     *
     * @param gameModel The game model
     */
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

            // Use a gbc to position the components
            // Images are on the left, names on the right
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 0.5;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.BOTH;

            addHeaders(gbc);

            // Loop over each pokemon and get the image of their species and their name
            for (int i = 0; i < roster.length; i++) {

                addPokemonToPanel(roster, gbc, i);

            }

        });
    }

    private void addPokemonToPanel(PlayerPokemon[] roster,
                                   GridBagConstraints gbc, int i) {
        PlayerPokemon pokemon = roster[i];

        System.out.println(pokemon.getName());

        gbc.gridy = i + 1;

        gbc.gridx = 0;
        BufferedImage pokemonImage = pokemon.getSpeciesImage();
        Integer spriteSize = 100;
        JLabel imageLabel = new JLabel(
                new ImageIcon(
                        pokemonImage.getScaledInstance(spriteSize, spriteSize,
                                Image.SCALE_DEFAULT)));
        add(imageLabel, gbc);

        // Configure the name
        gbc.gridx = 1;
        addPokemonName(pokemon, gbc);

        // Create a ratio of HP to MaxHP
        addHPRatio(pokemon, gbc);

        // Create a ratio of MP to MaxMP
        addMPRatio(pokemon, gbc);

        // Add the stats
        for (int j = 0; j < statNames.length; j++) {
            gbc.gridx = j + 4;
            addStat(j + 4, gbc, pokemon.getNumericalStat(statNames[j]));
        }
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
        JLabel hpString = new JLabel(
                pokemonHP.toString() + "/" + pokemonMaxHP.toString());
        add(hpString, gbc);
        hpString.setForeground(Color.GREEN);
        hpString.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void addMPRatio(PlayerPokemon pokemon, GridBagConstraints gbc) {
        gbc.gridx = 3;
        Integer pokemonMP = pokemon.getMP();
        Integer pokemonMaxMP = pokemon.getNumericalStat("MaxMP");
        JLabel mpString = new JLabel(
                pokemonMP.toString() + "/" + pokemonMaxMP.toString());
        add(mpString, gbc);
        mpString.setForeground(Color.BLUE);
        mpString.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void addHeaders(GridBagConstraints gbc) {

        String[] additionalHeaders = new String[] {"HP", "MP"};

        // We don't add a header for the image and name, so we start at 2
        gbc.gridx = 2;

        // Add the additional headers
        for (int i = 0; i < additionalHeaders.length; i++) {
            JLabel header = new JLabel(additionalHeaders[i]);
            header.setHorizontalAlignment(JLabel.CENTER);
            header.setForeground(Color.WHITE);
            header.setFont(new Font("Arial", Font.BOLD, 20));
            add(header, gbc);
            gbc.gridx++;
        }

        for (int i = 0; i < statNames.length; i++) {
            JLabel statName = new JLabel(statNames[i]);
            statName.setHorizontalAlignment(JLabel.CENTER);
            statName.setForeground(Color.WHITE);
            statName.setFont(new Font("Arial", Font.BOLD, 20));
            add(statName, gbc);
            gbc.gridx++;
        }
    }

    private void addStat(Integer placementIndex, GridBagConstraints gbc,
                         Integer stat) {
        gbc.gridx = placementIndex;
        JLabel statLabel = new JLabel(stat.toString());
        statLabel.setHorizontalAlignment(JLabel.CENTER);
        add(statLabel, gbc);
        statLabel.setForeground(Color.WHITE);
        statLabel.setFont(new Font("Arial", Font.BOLD, 20));
    }

}
