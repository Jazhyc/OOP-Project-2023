package inheritamon.model.data;

import java.util.*;

import inheritamon.model.pokemon.moves.*;

/**
 * A class to handle and load data
 */
public class DataHandler {

    private HashMap<String, HashMap<String, String>> characterData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String>> moveData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String>> itemData = new HashMap<String, HashMap<String, String>>();

    /**
     * The constructor for the DataHandler class
     */
    public DataHandler() {
        loadAllData();
    }

    // Obtained from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private void loadAllData() {

        loadData(characterData, "monster_stats.csv");
        loadData(moveData, "move_stats.csv");
        loadData(itemData, "items.csv");

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

    /**
     * Returns the data of a character
     * @param characterName The name of the character
     * @return The data of the character
     */
    public HashMap<String, String> getCharacterData(String characterName) {
        try {
            return characterData.get(characterName);
        } catch (NullPointerException e) {
            System.out.println("Character not found");

            // Exit the program, hopefully
            Runtime.getRuntime().halt(0);
            return null;
        }
    }

    /**
     * Returns the data of a move
     * @param moveName The name of the move
     * @return The data of the move
     */
    public HashMap<String, String> getMoveData(String moveName) {
        try {
            return moveData.get(moveName);
        } catch (NullPointerException e) {
            System.out.println("Move not found");

            // Exit the program, hopefully
            Runtime.getRuntime().halt(0);
            return null;
        }
    }

    public HashMap<String, String> getItemData(String itemName) {
        try {
            return itemData.get(itemName);
        } catch (NullPointerException e) {
            System.out.println("Item not found");

            // Exit the program, hopefully
            Runtime.getRuntime().halt(0);
            return null;
        }
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
    
            return abilities;
    }

    /**
     * Converts a string of moves into an ArrayList of moves
     * @param nonFormattedString
     * @return An ArrayList of moves
     */
    public static ArrayList<String> convertMoveSetToString(String nonFormattedString) {
        
        ArrayList<String> moveSet = new ArrayList<String>(Arrays.asList(nonFormattedString.split(" ")));

        return moveSet;
    }

}