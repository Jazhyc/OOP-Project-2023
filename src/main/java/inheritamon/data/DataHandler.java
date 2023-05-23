package inheritamon.data;

import java.util.*;

/**
 * A class to handle and load data
 */
public class DataHandler {

    private HashMap<String, HashMap<String, String>> characterData = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String>> moveData = new HashMap<String, HashMap<String, String>>();

    /**
     * The constructor for the DataHandler class
     */
    public DataHandler() {
        loadAllData();
    }

    private void loadAllData() {

        loadData(characterData, "monster_stats.csv");
        loadData(moveData, "move_stats.csv");

    }

    private void loadData(HashMap<String, HashMap<String, String>> data, String fileName) {

        Scanner characterDataScanner = new Scanner(DataHandler.class.getResourceAsStream("/" + fileName));

        ArrayList<String> attributes = new ArrayList<String>(); 

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