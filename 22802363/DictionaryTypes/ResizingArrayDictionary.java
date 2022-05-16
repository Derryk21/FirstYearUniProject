package DictionaryTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import DictionaryTypes.abstractClasses.ArrayTypeDictionary;

/**This class creates the resizing array data structure and implements DictionaryInterface*/

public class ResizingArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {
   String[] DictionaryArray = new String[5];

   /** Constructor to initialize instance variables */
   public ResizingArrayDictionary() {
      DictionaryArray = new String[5];
   }

   /* GENERAL METHODS handin 1*/
   /**
    * Creates Dictionary for resizing Array data structure
    * A counter is first used to obtain number of non duplicate words in 
    * input file. An array is then created which changes size according to
    * number of input words + 5
    * 
    * @param filepath , takes in the file path of input file
    */

   public void CreateDictionary(String filepath) {
      Integer WordCount = 0 + getNumberOfElements();
      Integer WordCounter = -1 + getNumberOfElements();

      //temporary array list to prevent duplicate words affecting the size of array
      ArrayList < String > TempList = new ArrayList < String > ();
      String TempWord = "";
      File LanguageFile = new File(filepath);

      //used to find the number of words in the Dictionary before resizing the array
      Scanner LanguageScanner1;
      try {
         LanguageScanner1 = new Scanner(LanguageFile);

         while (LanguageScanner1.hasNext()) {
            Boolean DuplicateWord = false;

            TempWord = LanguageScanner1.nextLine();
            for (int i = 0; i < TempList.size(); i++) {
               if (TempList.get(i).equals(TempWord))
                  DuplicateWord = true;
            }
            if (DuplicateWord != true) {
               TempList.add(TempWord);
               WordCount += 1;
            }
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      //used to create the dictionary
      Scanner LanguageScanner;
      try {
         LanguageScanner = new Scanner(LanguageFile);

         DictionaryArray = Arrays.copyOf(DictionaryArray, WordCount + 5);

         while (LanguageScanner.hasNext()) {
            Boolean DuplicateWordCheck = false;
            String WordAdd = LanguageScanner.nextLine();

            if (isWord(WordAdd) == true)
               DuplicateWordCheck = true;
            if (DuplicateWordCheck != true) {
               WordCounter += 1;
               DictionaryArray[WordCounter] = WordAdd;
            }
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * Returns boolean based on whether word is contained in array
    * A for loop runs through array structure and if word found returns true
    * @param word , word to be searched for
    */

   public boolean isWord(String word) {
      //run loop through dictionary and obtain whether the word is found in dictionary
      Boolean isWord = false;
      for (int i = 0; i < DictionaryArray.length; i++) {
         if (word.equals(DictionaryArray[i]) == true)
            isWord = true;
      }
      return isWord;
   }

   /**
    * New word added into resizing array data structure
    * Loops through array until null value found
    * When null value found, if word is not already contained in the
    * array then it is added at null position
    * If array is full, array size is doubled
    * 
    * @param word, word to be added into array
    */

   public void addNewWord(String word) {
      int wordcount = 0;
      int nullposition = 0;
      for (int i = 0; i < DictionaryArray.length; i++) {
         wordcount += 1;
         if (DictionaryArray[i] == null) {
            nullposition = i;
            break;
         }
      }
      //array is full, array size is doubled 
      //new word added at end position of array
      if (nullposition == 0 && isWord(word) == false && getNumberOfElements() != 0) {
         DictionaryArray = Arrays.copyOf(DictionaryArray, wordcount * 2);
         DictionaryArray[wordcount] = word;
         return;
      }
      if (isWord(word) == false) {
         DictionaryArray[nullposition] = word;
      }
   }

   /**
    * Word gets removed from resizing array data structure
    * Loop to determine if word exists and find position of word in array
    * New temporary array created that is used to add all words from
    * existing array excluding the removed word
    * The existing array is then set to the temporary array.
    * 
    * The size of the array is halved if it contains more than 5 words
    * and the array contains less than or equal to 1/4th of the number of words
    * in comparison to the size of the array
    * 
    * @param word, word to be removed from array
    */

   public void removeWord(String word) {
      String[] NewArray = new String[DictionaryArray.length];
      int PositionOfWord = -1;
      //loop to figure out if word exists in dictionary
      for (int i = 0; i < DictionaryArray.length; i++) {
         if (word.equals(DictionaryArray[i]) == true) {
            PositionOfWord = i;
         }
      }
      // Copy the elements except the index
      // from original array to the other array
      for (int i = 0, k = 0; i < NewArray.length; i++) {
         // if the index is
         // the removal element index
         if (i == PositionOfWord) {
            continue;
         }
         // if the index is not
         // the removal element index
         NewArray[k++] = DictionaryArray[i];
      }
      // make old array equivalent to new array that excludes deleted word
      for (int i = 0; i < NewArray.length; i++) {
         DictionaryArray[i] = NewArray[i];
      }
      if (DictionaryArray.length > 5 && getNumberOfElements() <= 0.25 * DictionaryArray.length) {
         DictionaryArray = Arrays.copyOf(DictionaryArray, DictionaryArray.length / 2);
      }
      //prevent array size becoming smaller than size 5
      if (DictionaryArray.length < 5) {
         DictionaryArray = Arrays.copyOf(DictionaryArray, 5);
      }
   }

   /**
    * Returns words in created resizing array data structure
    * Loops through array and adds word into array list
    */

   public ArrayList < String > getDictionaryWords() {
      ArrayList < String > NewArrayList = new ArrayList < String > ();
      for (int i = 0; i < getNumberOfElements(); i++) {
         NewArrayList.add(i, DictionaryArray[i]);
      }
      return NewArrayList;
   }

   /**
    * Returns number of words inside array structure
    * Loops through array and increases word counter
    */

   public int getNumberOfElements() {
      Integer NumElements = -1;

      for (int i = 0; i < DictionaryArray.length; i++) {
         NumElements += 1;
         if (DictionaryArray[i] == null) {
            break;
         }
      }
      return NumElements;
   }
}