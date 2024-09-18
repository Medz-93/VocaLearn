
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * VocabManagmenet manages vocabularies (topics and words).
 * @author Mehdi Hanini
 * @version 4/15/2024
 */
public class VocabManagement {
	
    /** The list of vocabularies stored as a doubly linked list. 
    */
    private static DoublyLinkedList<Vocab> vocab_List = new DoublyLinkedList<>();
    private static Scanner keyIn = new Scanner(System.in);

    /**
     * The main method to start the vocabulary management interactive Menu
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        int choice;
        do {
            displayMainMenu();
            choice = keyIn.nextInt();
            keyIn.nextLine();
            
            switch (choice) {
                case 1:
                    browseTopics();
                    break;
                case 2:
                    insertTopicBefore();
                    break;
                case 3:
                    insertTopicAfter();
                    break;
                case 4:
                    removeTopic();
                    break;
                case 5:
                    modifyTopic();
                    break;
                case 6:
                    searchForWord();
                    break;
                case 7:
                    loadFromFile();
                    break;
                case 8:
                    showWordsStartingWith();
                    break;
                case 9:
                    saveToFile();
                    break;
                case 0:
                    System.out.println("Exiting.. Goodbye");
                	System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);

        keyIn.close();
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("---------------------------------------");
        System.out.println(" \tVocabulary Control Center");
        System.out.println("---------------------------------------");
        System.out.println("1 Browse a topic");
        System.out.println("2 Insert a new topic before another");
        System.out.println("3 Insert a new topic after another");
        System.out.println("4 Remove a topic");
        System.out.println("5 Modify a topic");
        System.out.println("6 Search topics for a word");
        System.out.println("7 Load from a file");
        System.out.println("8 Show all words starting with a given letter");
        System.out.println("9 Save to file");
        System.out.println("0 Exit");
        System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
    }
    /**
     * Allows the user to browse through available topics and their associated words.
     */
    private static void browseTopics() {
        System.out.println("---------------------------------------");
        System.out.println("\tPick a topic");
        System.out.println("---------------------------------------");
        vocab_List.displayTopics();
        System.out.println("0: Exit");
        System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
        int topicChoice = keyIn.nextInt();
        if (topicChoice == 0) return;
        if (topicChoice < 0 || topicChoice > vocab_List.size()) {
            System.out.println("Invalid choice, try again.");
            return;
        }
        
        Vocab selectedTopic = vocab_List.getVocabByIndex(topicChoice - 1); // Adjust for zero-based indexing
        if (selectedTopic != null) {
            System.out.println("Topic: " + selectedTopic.getTopic());
            selectedTopic.displayWords();
        } else {
            System.out.println("Topic not found, try again.");
        }
    }

    /**
     * Inserts a new topic before another specified topic.
     */
    private static void insertTopicBefore() {
        System.out.println("---------------------------------------");
        System.out.println("\tPick a topic");
        System.out.println("---------------------------------------");

        vocab_List.displayTopics();
        System.out.println("0: Exit");

        System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
        int position = keyIn.nextInt();

        if (position == 0) {
            return; // Exit the method if the user selects cancel
        }

        // Adjusting for 1-based index input to 0-based index used in the list
        if (position < 1 || position > vocab_List.size()) {
            System.out.println("Invalid position, try again.");
            return;
        }

        keyIn.nextLine(); // Consume the leftover newline
        System.out.print("Enter a topic name: ");
        String topicName = keyIn.nextLine().trim();

        // Create a new Vocab object with the topic name entered by the user
        Vocab newTopic = new Vocab(topicName);
        
     // Prompt the user to enter words for the new topic
        System.out.println("Enter words for the topic '" + topicName + "' (press Enter to finish):");
        String word;
        while (true) {
            word = keyIn.nextLine().trim();
            if (word.isEmpty()) {
                break; // Break the loop if the user presses Enter without entering a word
            }
            newTopic.addWord(word); // Add the word to the new topic
        }

        // Insert the new topic before the given position
        vocab_List.addBefore(position - 1, newTopic); 
    }
    /**
     * Inserts a new topic after another specified topic.
     */
    private static void insertTopicAfter() {
    	System.out.println("---------------------------------------");
        System.out.println("\tPick a topic");
        System.out.println("---------------------------------------");

        vocab_List.displayTopics();
        System.out.println("0: Exit");
        
        System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
        int position = keyIn.nextInt();

        if (position == 0) {
            return; 
        }

        // Adjusting for 1-based index input to 0-based index used in the list
        if (position < 1 || position > vocab_List.size()) {
            System.out.println("Invalid position. Please try again.");
            return;
        }

        keyIn.nextLine(); // Consume the leftover newline
        System.out.print("Enter a topic name: ");
        String topicName = keyIn.nextLine().trim();

        // Create a new Vocab object with the topic name entered by the user
        Vocab newTopic = new Vocab(topicName);

        // Prompt the user to enter words for the new topic
        System.out.println("Enter words for the topic '" + topicName + "' (press Enter to finish):");
        String word;
        while (true) {
            word = keyIn.nextLine().trim();
            if (word.isEmpty()) {
                break; // Break the loop if the user presses Enter without entering a word
            }
            newTopic.addWord(word); // Add the word to the new topic
        }

        // Insert the new topic after the given position
        vocab_List.addAfter(position - 1, newTopic); // Method to be implemented in the DoublyLinkedList class
    }

    /**
     * Removes a topic from the vocabulary list.
     */
    private static void removeTopic() {
    	System.out.println("---------------------------------------");
        System.out.println("\tPick a topic");
        System.out.println("---------------------------------------");

        vocab_List.displayTopics();
        System.out.println("0: Exit");

        System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
        int topicNumber = keyIn.nextInt();
        keyIn.nextLine();

        if (topicNumber == 0) {
            return; // Exit the method if the user selects cancel
        }

        if (topicNumber < 1 || topicNumber > vocab_List.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        // Remove the topic at the given position
        Vocab removedVocab = vocab_List.removeAt(topicNumber - 1); 
        if (removedVocab != null) {
            System.out.println("Removed topic: " + removedVocab.getTopic());
        } else {
            System.out.println("Topic could not be removed.");
        }
    }

    /**
     * Modifies a topic by adding, removing, or changing words.
     */
    private static void modifyTopic() {
       
    	System.out.println("---------------------------------------");
        System.out.println("\tPick a topic");
        System.out.println("---------------------------------------");

        vocab_List.displayTopics();
        System.out.println("0: Exit");

        System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
        int topicNumber = keyIn.nextInt();
        keyIn.nextLine();

        if (topicNumber == 0) {
            return; // Exit the method if the user selects cancel
        }

        if (topicNumber < 1 || topicNumber > vocab_List.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        
        Vocab modifiedVocab = vocab_List.getVocabByIndex(topicNumber - 1);
        if (modifiedVocab == null) {
            System.out.println("Invalid topic number.");
            return;
        }
    	
        
    	System.out.println("---------------------------------------");
        System.out.println("\tModify topics Menu");
        System.out.println("---------------------------------------");
        System.out.println("a: add a word");
        System.out.println("r: remove a word");
        System.out.println("c: change a word");
        System.out.println("0: exit");      
    	System.out.println("---------------------------------------");
        System.out.print("Enter your choice: ");
        
        String modifyChoice = keyIn.next();
        keyIn.nextLine(); 

        switch (modifyChoice) {
            case "a": // Add a word
                System.out.println("Type a word and press Enter, or press Enter to end input");
                String wordToAdd = keyIn.nextLine().trim();
                modifiedVocab.addWord(wordToAdd);
                break;
            case "r": // Remove a word
                System.out.print("Enter a word to remove:");
                String wordToRemove = keyIn.nextLine().trim();
                if (modifiedVocab.removeWord(wordToRemove)) {
                    System.out.println("Word removed.");
                } else {
                    System.out.println("sorry, there is no word: " + wordToRemove);
                }
                break;
            case "c": // Change a word
                String wordToChange;
                boolean wordExists;
                
                do {
                    System.out.println("Enter the word you want to change:");
                    wordToChange = keyIn.nextLine().trim();
                    
                    // Check if the word exists
                    wordExists = modifiedVocab.containsWord(wordToChange);
                    if (!wordExists) {
                        System.out.println("Word not found, try again.");
                    }
                } while (!wordExists);
                
                System.out.println("Enter the new word:");
                String newWord = keyIn.nextLine().trim();
                
                // Now we made the word exists, so we can attempt to change it
                modifiedVocab.changeWord(wordToChange, newWord);
                System.out.println("Word changed.");
                break;
            case "0": // Exit
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
    /**
     * Searches for a specific word among all topics and displays the topics containing it.
     */
    private static void searchForWord() {
        
    	System.out.print("Enter the word to search for: ");
        String wordToSearch = keyIn.nextLine();
        boolean found = false;

        for (int i = 0; i < vocab_List.size(); i++) {
            Vocab currentVocab = vocab_List.getVocabByIndex(i);
            if (currentVocab != null && currentVocab.containsWord(wordToSearch)) {
                if (!found) {
                    System.out.println("This word is in the following Topic(s):");
                    found = true;
                }
                System.out.println(currentVocab.getTopic());
            }
            
        }

        if (!found) {
            System.out.println("This word is not present in any topic.");
        }
    }

    /**
     * Loads vocabulary data from teh specified file by the user 
     */
    private static void loadFromFile() {
    	System.out.println("Enter the filename to load from:");
        String filename = keyIn.next();
        loadVocabFromFile(filename);
    }
    /**
     * Loads vocabulary data from the specified file into the application.
     * @param filename The name of the file to load vocabulary data from.
     */
    private static void loadVocabFromFile(String filename) {
        try (Scanner keyIn = new Scanner(new File(filename))) {
            Vocab currentVocab = null;
            vocab_List.clear(); // To make sure the existing list is clear before loading a new file

            while (keyIn.hasNextLine()) {
                String line = keyIn.nextLine().trim();
                if (line.startsWith("#")) {
                    String topic = line.substring(1).trim(); // Remove '#' and trim the topic name
                    currentVocab = new Vocab(topic);
                    vocab_List.addLast(currentVocab); // Add to the end of the doubly linked list
                } else if (!line.isEmpty() && currentVocab != null) {
                    currentVocab.addWord(line); // Add the word to the current vocab's singly linked list
                }
            }
            System.out.println("Done loading."); // Notify the user of successful loading
        } catch (FileNotFoundException e) {
            System.out.println("The file " + filename + " was not found.");
        }
    }
    
    /**
     * Displays words starting with a specific letter from any topic
     */
    private static void showWordsStartingWith() {
        System.out.print("Enter a letter: ");
        String input = keyIn.nextLine().trim().toLowerCase();  // Normalize input to lower case for consistent comparison

        if (input.isEmpty() || input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid input, please enter a single letter.");
            return;
        }

        char startChar = input.charAt(0);

        ArrayList<String> foundWords = new ArrayList<>();
        
        // Iterate over each Vocab object in the vocabulary list
        for (int i = 0; i < vocab_List.size(); i++) {
            Vocab currentVocab = vocab_List.getVocabByIndex(i);
            if (currentVocab != null) {
                ArrayList<String> words = currentVocab.getWords();
                for (String word : words) {
                    if (word.toLowerCase().startsWith(String.valueOf(startChar))) {  // Check if word starts with the specified letter
                        foundWords.add(word);
                    }
                }
            }
        }

        if (foundWords.isEmpty()) {
            System.out.println("No words found starting with the letter '" + startChar + "'.");
        } else {
            // Sort the ArrayList
            Collections.sort(foundWords);

            // Print the sorted list
            System.out.println("Words starting with '" + startChar + "':");
            for (String word : foundWords) {
                System.out.println(word);
            }
        }
    }

    /**
     * Saves vocabulary data from the application to a file.
     */
    private static void saveToFile() {
        System.out.print("Enter the filename to save the vocabularies to: ");
        String filename = keyIn.nextLine().trim(); // Get the filename from the user

        File file = new File(filename);
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            // Iterate over each Vocab object in the vocabulary list
            for (int i = 0; i < vocab_List.size(); i++) {
                Vocab currentVocab = vocab_List.getVocabByIndex(i);
                if (currentVocab != null) {
                    // Write the topic to the file
                    writer.println("#" + currentVocab.getTopic());
                    ArrayList<String> words = currentVocab.getWords();
                    for (String word : words) {
                        writer.println(word); // Write each word under the topic
                    }
                    writer.println(); 
                }
            }
            System.out.println("The Vocabularies have been saved successfully to '" + filename + "'");
        } catch (FileNotFoundException e) {
            System.out.println("The file '" + filename + "' could not be created or opened.");
        }
    }

    
    
}
