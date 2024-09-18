
import java.util.ArrayList;
import java.util.Objects;


/**
 * Represents a vocabulary list associated with a specific topic.
 * @author Mehdi Hanini
 * @version 4/15/2024
 */
public class Vocab {
    private String topic; // The topic of this Vocab
    private SinglyLinkedList words; // The list of words associated with the topic
    
    /**
     * Constructor for Vocab
     * @param topic The topic of the vocabulary list.
     */    public Vocab(String topic) {
        this.topic = topic;
        this.words = new SinglyLinkedList();
    }

   
     /**
      * Adds a word to this vocabulary list.
      * @param word The word to add.
      */    public void addWord(String word) {
        words.addLast(word);
    }
    
      /**
       * Removes a word from this vocabulary list.        
       * @param word The word to remove.
       * @return true if the word is removed successfully, false otherwise.
       */
	public boolean removeWord(String word) {
        return words.remove(word); 
	}
    
	/**
     * Replaces a word with a new word in this vocabulary list.
     * @param oldWord The word to be replaced.
     * @param newWord The new word to replace with.
     * @return true if the word is replaced successfully, false otherwise.
     */
    public boolean changeWord(String oldWord, String newWord) {
        return words.replace(oldWord, newWord); // Method to be implemented in SinglyLinkedList
    }
    
    /**
     * Checks if this vocabulary list contains a specific word. 
     * @param word The word to check.
     * @return true if the word is found, false otherwise.
     */
    public boolean containsWord(String word) {
        return words.contains(word);
    }

    /**
     * Retrieves all words in this vocabulary list.
     * @return An ArrayList containing all words.
     */    public ArrayList<String> getWords() {
        return words.toList();
    }
    
     /**
      * Gets the topic of this vocabulary list.
      * @return The topic of the vocabulary list.
      */    public String getTopic() {
        return topic;
    }
    
      /**
       * Displays all words 
       */
    public void displayWords() {
        ArrayList<String> wordList = words.toList(); 

        if (wordList.isEmpty()) {
            System.out.println("No words found for this topic.");
            return;
        }      
        for (int i = 0; i < wordList.size(); i++) {
            if (i > 0 && i % 4 == 0) { // After every 4 words, insert a newline
                System.out.println();
            }
            // Print the index and word with tab spacing (formatting output of Topics and words
            System.out.printf("%d: %-25s\t", (i + 1), wordList.get(i)); // Index is 1-based for display
        }  
        System.out.println();        
    }
    
    /**
     * Returns a string representation of this vocabulary list.
     * @return A string containing the topic and words of the vocabulary list.
     */    @Override
    public String toString() {
        return "Topic: " + topic + "\n" + "Words: " + words.toString();
    }

     /**
      * Checks if two vocabulary lists are equal.
      * @param o The object to compare.
      * @return true if Vocabs are equal, false if not
      */    @Override
     
     public boolean equals(Object o) {
    	    if (this == o) 
    	        return true;
    	    if (!(o instanceof Vocab)) 
    	        return false;
    	    
    	    Vocab vocab = (Vocab) o;
    	    if (topic == null) {
    	        if (vocab.topic != null) 
    	            return false;
    	    } else if (!topic.equals(vocab.topic)) 
    	        return false;
    	    return words.toList().equals(vocab.words.toList());
    	}


      /**
       * Singly linked list of Strings: words of this vocabulary list.
       */
      public class SinglyLinkedList {
    	  
    	  //Inner class represents a node in the singly linked list
          private class SNode {
              String word; // The word contained within this node
              SNode next;  // The next node in the list

              /**
               * Constructor for SNode.
               * @param word The word to store in the node.
               */           
              public SNode(String word) {
                  this.word = word;
                  this.next = null;
              }
          }  
          
        private SNode head; // Head node of the list
        private int size;   // Number of nodes in the list

        /**
         * Constructor for SinglyLinkedList.
         */
        public SinglyLinkedList() {
            this.head = null;
            this.size = 0;
        }

        /**
         * Adds a word to the end of the list.
         * 
         * @param word The word to add.
         */
        public void addLast(String word) {
            SNode newNode = new SNode(word);
            if (head == null) {
                head = newNode;
            } else {
                SNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }
        
        /**
         * Replaces a word with a new word in the list.
         * @param oldWord the word to be replaced.
         * @param newWord the new word to replace with.
         * @return true if the word is replaced successfully, false otherwise.
         */
        public boolean replace(String oldWord, String newWord) {
            SNode current = head;
            while (current != null) {
                if (current.word.equals(oldWord)) {
                    current.word = newWord;
                    return true;
                }
                current = current.next;
            }
            return false;
        }
        /**
         * Removes a word from the list
         * @param word the word to remove.
         * @return true if the word is removed successfully, false otherwise.
         */
        public boolean remove(String word) {
            if (head == null) return false;
            if (head.word.equals(word)) {
                head = head.next;
                return true;
            }
            SNode current = head;
            while (current.next != null) {
                if (current.next.word.equals(word)) {
                    current.next = current.next.next;
                    return true;
                }
                current = current.next;
            }
            return false;
        }
        /**
         * Checks if the list contains a specific word.
         * 
         * @param word the word to check.
         * @return true if the word is found, false otherwise
         */
        public boolean contains(String word) {
            SNode current = head;
            while (current != null) {
                if (current.word.equals(word)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        /**
         * Converts the list to an ArrayList of Strings.
         * @return An ArrayList containing all words in the list.
         */       
        public ArrayList<String> toList() {
            ArrayList<String> list = new ArrayList<>();
            SNode current = head;
            while (current != null) {
                list.add(current.word);
                current = current.next;
            }
            return list;
        }
             
    }	
}
