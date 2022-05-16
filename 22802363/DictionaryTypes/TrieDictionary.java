package DictionaryTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import DictionaryTypes.TrieStructureComponents.State;

import DictionaryTypes.abstractClasses.Dictionary;

/**This class creates the trie data structure and implements DictionaryInterface*/

public class TrieDictionary extends Dictionary implements DictionaryInterface {

   private State rootState = new State(0, false, "");

   /* Constructor to initialize new state */
   public TrieDictionary() {
      new State();
   }

   /**
    * Returns array list of characters
    * This method traverses the trie data structure
    * in a breadth first manner. It will add nodes to a queue and 
    * subsequently add characters in an array list in the correct order.
    */
   /* SPECIFIC METHODS - Hand in 2 */
   public ArrayList < Character > BFS() {
      //todo
      return null;
   }
   /**
    * Returns array list of characters
    * This method traverses the trie data structure
    * in a depth first manner. It fetches an array list of
    * outgoing edges states and bubble sorts the words. 
    * The outgoing characters are also bubble sorted and then 
    * added to the array list.
    */
   public ArrayList < Character > DFS() {

      String temp;
      Character tempo;
      ArrayList < String > testt = rootState.test();
      ArrayList < Character > testing = rootState.testing();

      String[] arraytest = testt.toArray(new String[testt.size()]);
      Character[] arraytestt = testing.toArray(new Character[testing.size()]);
      //bubble sort algorithm to sort all words from states
      //characters sorted with their respective states
      for (int j = 0; j < arraytest.length - 1; j++) {
         for (int i = j + 1; i < arraytest.length; i++) {
            if (arraytest[j].compareTo(arraytest[i]) > 0) {
               tempo = arraytestt[j];
               temp = arraytest[j];
               arraytest[j] = arraytest[i];
               arraytestt[j] = arraytestt[i];
               arraytest[i] = temp;
               arraytestt[i] = tempo;
            }
         }
      }

      testing = new ArrayList < > (Arrays.asList(arraytestt));
      return testing;
   }
   /* GENERAL METHODS - Hand in 1*/
   /**
    * Removes word from Trie Dictionary
    * This method searches array list of outgoing edges 
    * if word found inside the arraylist of edges and the accept state is true
    * then the words accept state is changed to false.
    * 
    * @param word1 word to be removed
    */

   public void removeWord(String word1) {
      for (int i = 0; i < rootState.getArrayList().size(); i++) {
         //word found and its accept state shows its a full word
         if (rootState.getArrayList().get(i).getLastState().getCurrentwordUpUntil().equals(word1) && rootState.getArrayList().get(i).getLastState().getAcceptState() == true) {
            rootState.getArrayList().get(i).getLastState().setAcceptState(false);
         }
      }
   }

   /**
    * Creates Dictionary for Trie Structure 
    * A root state is created and words are read one by one from 
    * input file. States are created for every character and
    * concatenate on one another until the full word is formed.
    * When the full word is created the boolean state is changed 
    * to accept.
    *  
    *  @param filePath file path of input file
    */

   public void CreateDictionary(String filePath) {
      File LanguageFile = new File(filePath);

      Scanner LanguageScanner;
      try {
         LanguageScanner = new Scanner(LanguageFile);

         while (LanguageScanner.hasNext()) {
            State rootState = new State(0, false, "");
            String WordAdd = LanguageScanner.nextLine();

            for (int i = 0; i < WordAdd.length(); i++) {
               //adds false states of word being built up by its characters
               char ch = WordAdd.charAt(i);
               if (i != WordAdd.length() - 1) {
                  rootState.setAcceptState(false);
                  rootState.addLink(rootState, ch);
               }
               //adds true state of final complete word
               if (i == WordAdd.length() - 1) {
                  rootState.setAcceptState(true);
                  rootState.addLink(rootState, ch);
               }
            }
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method to validate whether word is in Trie dictionary structure. 
    * Searches through states and if full word
    * is found and its boolean state is true, returns true.
    * 
    * @param word word the be validated
    */

   public boolean isWord(String word) {
      char c = word.charAt(word.length() - 1);
      Boolean CheckWord = false;
      //try block used to prevent null exception is word doesnt exist in state
      try {
         if (rootState.compareOutgoingEdges(word.length(), c, word).getAcceptState() == true && rootState.compareOutgoingEdges(word.length(), c, word).getAcceptState() != null)
            CheckWord = true;
      } catch (NullPointerException e) {}
      return CheckWord;
   }

   /**
    * Method to add new word to Trie dictionary structure.
    * Creates new states for every character and concatenates these
    * characters onto one another until full word is formed and then
    * then changes the accept state to true.
    * 
    * @param w is the word to be added
    */

   public void addNewWord(String w) {
      State rootState = new State(0, false, "");
      for (int i = 0; i < w.length(); i++) {
         char ch = w.charAt(i);
         if (i != w.length() - 1) {
            rootState.setAcceptState(false);
            rootState.addLink(rootState, ch);
         }
         if (i == w.length() - 1) {
            rootState.setAcceptState(true);
            rootState.addLink(rootState, ch);
         }
      }
   }

   /**
    * Returns an array list of words in Trie dictionary structure.
    * It searches through the arraylist of outgoing edges which 
    * contain all the states.
    * When an accept state is found the word is added to a new 
    * arraylist.
    */

   public ArrayList < String > getDictionaryWords() {
      ArrayList < String > NewArrayList = new ArrayList < String > ();

      for (int i = 0; i < rootState.getArrayList().size(); i++) {
         if (rootState.getArrayList().get(i).getLastState().getAcceptState() == true && NewArrayList.lastIndexOf(rootState.getArrayList().get(i).getLastState().getCurrentwordUpUntil()) == -1)
            NewArrayList.add(rootState.getArrayList().get(i).getLastState().getCurrentwordUpUntil());
      }
      return NewArrayList;
   }

   /**
    * Returns the number of words contained in the Trie dictionary structure.
    * It searches states through the arraylist of outgoing edges. If a state
    * is an accept state, a counter is increased.
    */

   public int getNumberOfElements() {
      Integer count = 0;
      for (int i = 0; i < rootState.getArrayList().size(); i++) {
         if (rootState.getArrayList().get(i).getLastState().getAcceptState() == true)
            count += 1;
      }
      return count;
   }

   /**
    * Returns a common prefix of a given word with one in the Trie dictionary.
    * It searches states and returns the state that has the longest
    * common prefix.
    * 
    * @param word , is the word to compare prefixes with
    */

   public String findCommonPrefix(String word) {
      String commonPrefix = "";
      String concatWord = "";

      for (int i = 0; i < word.length(); i++) {
         char ch = word.charAt(i);
         //searches state of word from its first character until its last character
         concatWord = concatWord + ch;
         try {
            commonPrefix = rootState.compareOutgoingEdges(i + 1, ch, concatWord).getCurrentwordUpUntil();
         } catch (NullPointerException e) {}
      }
      return commonPrefix;
   }
}