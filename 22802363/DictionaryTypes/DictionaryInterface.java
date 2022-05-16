package DictionaryTypes;
import java.util.ArrayList;

/**This interface groups related methods*/

public interface DictionaryInterface {
   /** This method creates the Dictionary 
    * Returns void
    * 
    * @param filepath, path to input file
    */
   void CreateDictionary(String filepath);

   /** This method validates whether word is contained in data structure 
    * Returns boolean value
    * 
    * @param word, word to be validated
    */
   boolean isWord(String word);

   /** This method adds new word into data structure 
    * Returns void
    * 
    * @param word, word to be added in data structure
    */
   void addNewWord(String word);

   /** This method removes words from data structure
    * Returns void 
    * 
    * @param word, word to be removed from data structure
    */

   void removeWord(String word);

   /**
    * This method returns words contained in data structure
    * Returns array list
    */
   ArrayList < String > getDictionaryWords();

   /**
    * This method returns an array list of top similar words from data structure to
    * given word. Words in data structure compared using levenshtein distance
    * 
    * @param dictionaryWords, all words contained in data structure
    * @param word, word to be compared to
    * @param N, number of top suggested words to be returned
    */
   String[] getTopNSuggestions(ArrayList < String > dictionaryWords, String word, int N);

   /**
    * Levenshtein distance between two words is returned
    * 
    * @param wrongWord, word to be compared to
    * @param actualWord, word we want suggestions for
    */
   int getLevenshteinDistance(String wrongWord, String actualWord);

   /**
    * This method returns the number of words contained in an array structure
    * Returns integer value
    */
   int getNumberOfElements();
}