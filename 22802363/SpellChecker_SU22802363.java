import DictionaryTypes.*;

/**This class makes use of different dictionary data types*/

public class SpellChecker_SU22802363 {

   DictionaryInterface spellChecker;

   /** Create a new dictionary data structure */
   public SpellChecker_SU22802363(String checkerType) {
      if (checkerType.equals("Trie")) {
         spellChecker = new TrieDictionary();
      } else if (checkerType.equals("Array")) {
         spellChecker = new ArrayDictionary();
      } else if (checkerType.equals("ResizingArray")) {
         spellChecker = new ResizingArrayDictionary();
      } else {
         System.out.println("Invalid command line input, use one of: Trie, Array, or ResizingArray");
         System.exit(0);
      }
   }

   /**
    * Method to determine if word is contained in data structure
    * 
    * @param word , word to be compared if inside data structure
    */
   public boolean check(String word) {
      return spellChecker.isWord(word);
   }

   /**
    * Method returns a word that is most similar to given word according to its
    * levenshtein difference
    * 
    * @param wordToCheck, word to get closest levenshtein difference of
    */

   public String getBestSuggestion(String wordToCheck) {
      return spellChecker.getTopNSuggestions(spellChecker.getDictionaryWords(), wordToCheck, 1)[0];
   }
}