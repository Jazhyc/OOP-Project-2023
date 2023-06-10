package inheritamon.model.data;

import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import inheritamon.model.pokemon.moves.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * @author Jeremias
 * A class to handle and load data with the appropriate exceptions
 */
public class DataHandler {

    // Singleton pattern
    private static DataHandler dataHandler;

    private HashMap<String, HashMap<String, String>> characterData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String>> moveData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String>> itemData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, BufferedImage>> characterImages = new HashMap<String, HashMap<String, BufferedImage>>();
    private HashMap<String, BufferedImage> icons = new HashMap<String, BufferedImage>();
    private HashMap<String, BufferedImage> battleBackgrounds = new HashMap<String, BufferedImage>();
    private HashMap<String, BufferedImage> tiles = new HashMap<String, BufferedImage>();
    private HashMap<String, BufferedImage> characterTextures = new HashMap<String, BufferedImage>();
    private HashMap<String, HashMap<String, String>> languageData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, BufferedImage> inventorySprites = new HashMap<String, BufferedImage>();
    // private HashMap<String, HashMap<Language, String[]>> dialogueData = new HashMap<String, HashMap<Language, String[]>>();

    // Create a hashmap for sounds and music
    private HashMap<String, AudioInputStream> sounds = new HashMap<String, AudioInputStream>();
    private HashMap<String, AudioInputStream> music = new HashMap<String, AudioInputStream>();

    /**
     * The constructor for the DataHandler class
     */
    private DataHandler() {
        loadAllData();
    }

    public static DataHandler getInstance() {
        if (dataHandler == null) {
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }

    // Obtained from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private void loadAllData() {

        loadData(characterData, "monster_stats.csv");
        loadData(moveData, "move_stats.csv");
        loadData(itemData, "items.csv");
        loadData(languageData, "languages.csv");
        loadCharacterImages();
        loadGeneralImages(icons, "icons");
        loadGeneralImages(battleBackgrounds, "battleBackgrounds");
        loadGeneralImages(tiles, "tiles");
        loadGeneralImages(characterTextures, "characterTextures");
        loadGeneralImages(inventorySprites, "inventorySprites");
        // loadConversations(dialogueData, "dialogues.csv");
        loadAudio(sounds, "sounds");
        loadAudio(music, "music");

    }

    private void loadData(HashMap<String, HashMap<String, String>> data, String fileName) {

        ArrayList<String> attributes = new ArrayList<String>(); 
        
        // Try with resources to automatically close the scanner
        try (Scanner characterDataScanner = new Scanner(DataHandler.class.getResourceAsStream("/" + fileName))) {

            // Read the first line of the file and put the attributes into the attributes ArrayList
            String firstLine = characterDataScanner.nextLine();
            String[] firstLineSplit = firstLine.split(",");
            for (String attribute : firstLineSplit) {
                attributes.add(attribute);
            }

            // Loop through the rest of the file and put the data into the correct HashMap
            while (characterDataScanner.hasNextLine()) {
                String line = characterDataScanner.nextLine();
                String[] lineSplit = line.split(",");
                HashMap<String, String> characterDataEntry = new HashMap<String, String>();
                for (int i = 0; i < lineSplit.length; i++) {
                    characterDataEntry.put(attributes.get(i), lineSplit[i]);
                }
                data.put(lineSplit[0], characterDataEntry);
            }

            characterDataScanner.close();
            
        } catch (NullPointerException e) {
            System.out.println("File not found");
            Runtime.getRuntime().halt(0);
        }
       

    }

    // Images are obtained from Pokemon
    private void loadCharacterImages() {

        try {
            // Go through all the keys in the characterData HashMap and load the images
            for (String characterName : characterData.keySet()) {
                HashMap<String, BufferedImage> characterImagesEntry = new HashMap<String, BufferedImage>();
                characterImagesEntry.put("front", ImageIO.read(DataHandler.class.getResource("/battleSprites/" + characterName + ".png")));
                characterImagesEntry.put("back", ImageIO.read(DataHandler.class.getResource("/battleSprites/" + characterName + "Back.png")));
                characterImages.put(characterName, characterImagesEntry);
            }
        } catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

    }

    private void loadGeneralImages(HashMap<String, BufferedImage> images, String folderName) {

        try {
            // Get all file names in the icons folder using File
            File iconsFolder = new File(DataHandler.class.getResource("/" + folderName + "/").toURI());
            File[] iconFiles = iconsFolder.listFiles();

            // Load each icon into the icons hashmap
            for (File iconFile : iconFiles) {
                String iconName = iconFile.getName().replace(".png", "");
                BufferedImage iconImage = ImageIO.read(iconFile);
                images.put(iconName, iconImage);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void loadAudio(HashMap<String, AudioInputStream> audios, String folderName) {
        try {
            // Get the URL of the folder containing the audio files
            URL folderUrl = getClass().getResource("/" + folderName + "/");

            // Create a file object from the folder URL
            File folder = new File(folderUrl.toURI());

            // Get a list of all the files in the folder
            File[] files = folder.listFiles();

            // Load each audio file into the HashMap
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".wav")) {
                    // Get the name of the audio file without the extension
                    String name = file.getName().substring(0, file.getName().lastIndexOf("."));

                    // Load the audio file into an AudioInputStream
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

                    // Add the audio stream to the HashMap
                    audios.put(name, audioStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        

//    private void loadConversations() {
//        try {
//            // Go through all localizations and load the corresponding dialogue options.
//            for (NonPlayerCharacter npc : NPCs) {
//                dialogueData.put(npc, dialogues?);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//    }

    private HashMap<String, String> getData(HashMap<String, HashMap<String, String>> dataMap, String dataName, String dataType) {
        try {
            return new HashMap<String, String>(dataMap.get(dataName));
        } catch (NullPointerException e) {
            System.out.println(dataType + " not found");

            // Exit the program, hopefully
            Runtime.getRuntime().halt(0);
            return null;
        }
    }

    public HashMap<String, String> getCharacterData(String characterName) {
        return getData(characterData, characterName, "Character");
    }

    public HashMap<String, String> getMoveData(String moveName) {
        return getData(moveData, moveName, "Move");
    }

    public HashMap<String, String> getItemData(String itemName) {
        return getData(itemData, itemName, "Item");
    }

    /**
     * Gets the data of all menus
     * @return The data of all menus
     */
    public HashMap<String, HashMap<String, String>> getLanguageData() {
        return new HashMap<String, HashMap<String, String>>(languageData);
    }

    /**
     * Converts the data of all the moves into an ArrayList of move objects
     * @return The data of the moves as an Ability
     */
    public HashMap<String, NormalAbility> getAllAbilities() {
            
            HashMap<String, NormalAbility> abilities = new HashMap<String, NormalAbility>();
    
            for (String moveName : moveData.keySet()) {
                HashMap<String, String> moveData = getMoveData(moveName);
                NormalAbility ability = new NormalAbility(moveData);
                abilities.put(moveName, ability);
            }
    
            return new HashMap<String, NormalAbility>(abilities);
    }

    public HashMap<String, HashMap<String, BufferedImage>> getCharacterImages() {
        return characterImages;
    }

    public HashMap<String, BufferedImage> getPokemonSprite(String pokemonName) {
        return characterImages.get(pokemonName);
    }

    /**
     * Converts a string of moves into an ArrayList of moves
     * @param nonFormattedString
     * @return An ArrayList of moves
     */
    public static ArrayList<String> convertMoveSetToString(String nonFormattedString) {
        
        ArrayList<String> moveSet = new ArrayList<String>(Arrays.asList(nonFormattedString.split(";")));

        return new ArrayList<String>(moveSet);
    }

    public HashMap<String, BufferedImage> getIcons() {
        return icons;
    }

    private <T> T getImage(Map<String, T> imageMap, String imageName, String imageType) {
        T image = imageMap.get(imageName);
        if (image == null) {
            throw new IllegalArgumentException(imageType + " not found: " + imageName);
        }
        return image;
    }

    public BufferedImage getBackground(String backgroundName) {
        return getImage(battleBackgrounds, backgroundName, "Background");
    }

    public BufferedImage getTileImage(String tileName) {
        return getImage(tiles, tileName, "Tile");
    }

    public BufferedImage getCharacterTexture(String textureName) {
        return getImage(characterTextures, textureName, "Texture");
    }

    public BufferedImage getItemSprite(String spriteName) {
        return getImage(inventorySprites, spriteName, "Inventory Sprite");
    }

    public HashMap<String, AudioInputStream> getAudios(String audioType) {
        
        if (audioType.equals("Music")) {
            return music;
        } else if (audioType.equals("Sounds")) {
            return sounds;
        } else {
            throw new IllegalArgumentException("Audio type not found: " + audioType);
        }

    }

}