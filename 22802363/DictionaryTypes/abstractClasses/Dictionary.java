package DictionaryTypes.abstractClasses;

import java.util.ArrayList;
import java.util.Arrays;

	/**Abstract class that is extended by the dictionary data types
	 * It is used to return the top suggested words*/

	public abstract class Dictionary {

   /**
    * This method returns an array list of top similar words from data structure to
    * given word. Words in data structure compared using levenshtein distance
    * 
    * @param dictionaryWords, all words contained in data structure
    * @param wrongWord, word to be compared to
    * @param N, number of top suggested words to be returned
    */

   public String[] getTopNSuggestions(ArrayList < String > dictionaryWords, String wrongWord, int N) {
      String[] LevenshteinDistanceArray = new String[N];
      Integer[] AssignedDistance = new Integer[dictionaryWords.size()];
      String[] TempArray = dictionaryWords.toArray(new String[dictionaryWords.size()]);

      //adds the LevenshetinDistance for all words into the array
      for (int i = 0; i < dictionaryWords.size(); i++) {
         AssignedDistance[i] = getLevenshteinDistance(wrongWord, dictionaryWords.get(i));
      }
      //makes use of a bubble sort algorithm 
      //sorts the integer array of LevenshetinDistance from lowest to highest
      //sorts the string array which corresponds to the integer array values in the same LevenshetinDistance order
      for (int i = 0; i < dictionaryWords.size() - 1; i++)
         for (int j = 0; j < dictionaryWords.size() - i - 1; j++)
            if (AssignedDistance[j] > AssignedDistance[j + 1]) {
               // swap arr[j+1] and arr[j]
               int temp = AssignedDistance[j];
               String tempword = TempArray[j];
               AssignedDistance[j] = AssignedDistance[j + 1];
               TempArray[j] = TempArray[j + 1];
               AssignedDistance[j + 1] = temp;
               TempArray[j + 1] = tempword;
            }
      //in the case there are less than N words present in the dictionary,produces an array containing all the words
      if (N > dictionaryWords.size())
         return TempArray;

      //assign values to the final array which will be of size N opposed to size of entire dictionary
      for (int i = 0; i < N; i++) {
         LevenshteinDistanceArray[i] = TempArray[i];
      }
      return LevenshteinDistanceArray;
   }

   /**
    * Levenshtein distance between two words is returned
    * Credit -https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
    * This method and thus algorithm used, was adapted from geeksforgeeks.
    * 
    * @param wrongWord, word to be compared to
    * @param actualWord, word we want suggestions for
    */

   public int getLevenshteinDistance(String wrongWord, String actualWord) {
      //store results of sub problems in array
      int[][] LevDis = new int[wrongWord.length() + 1][actualWord.length() + 1];

      for (int i = 0; i <= wrongWord.length(); i++) {
         for (int j = 0; j <= actualWord.length(); j++) {
            if (i == 0) {
               //store subproblems
               LevDis[i][j] = j;
            } else if (j == 0) {
               LevDis[i][j] = i;
            }
            // find the minimum value among three operations below
            else {
               LevDis[i][j] = min(LevDis[i - 1][j - 1] +
                  NumberOfReplacement(wrongWord.charAt(i - 1), actualWord.charAt(j - 1)),
                  LevDis[i - 1][j] + 1,
                  LevDis[i][j - 1] + 1);
            }
         }
      }
      return LevDis[wrongWord.length()][actualWord.length()];
   }

   /**
    * Method to return integer of number of replacement
    * Check for distinct characters
    * Used in getLevenshteinDistance
    * 
    * @param a character compared to
    * @param b character compared to
    */

   public static int NumberOfReplacement(char a, char b) {
      return a == b ? 0 : 1;
   }

   /**
    * Returns minimum value from different operations
    * Used in getLevenshteinDistance
    */

   public static int min(int...numb) {
      return Arrays.stream(numb)
         .min().orElse(Integer.MAX_VALUE);
   }
}