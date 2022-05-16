package DictionaryTypes;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;

import DictionaryTypes.abstractClasses.ArrayTypeDictionary;

/**This class creates the array data structure and implements DictionaryInterface*/

public class ArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {
   String[] DictionaryArray = new String[30000];
   /** Constructor to initialize data structure */
   public ArrayDictionary() {
      DictionaryArray = new String[30000];
   }

   /* GENERAL METHODS Hand-in 1*/
   /**
    * Creates Dictionary for Array data structure
    * Words are read one by one from input file and if
    * word is not a duplicate it will be added into array
    * 
    * @param filepath , takes in the file path of input file
    */

   public void CreateDictionary(String filepath) {
      // + getNumberOfElements is used so that addNewWord method 
      //can be called before create dictionary and words will 
      //still be added
      int nullposition = 0 + getNumberOfElements();

      File LanguageFile = new File(filepath);

      Scanner LanguageScanner;
      try {
         LanguageScanner = new Scanner(LanguageFile);

         while (LanguageScanner.hasNext()) {
            Boolean wordExists = false;

            String WordAdd = LanguageScanner.nextLine();
            //call isWord to prevent duplicates
            if (isWord((WordAdd)) == true)
               wordExists = true;

            if (wordExists == false) {
               DictionaryArray[nullposition] = WordAdd;
               nullposition += 1;
            }
         }
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   /**
    * Returns boolean based on whether word is contained in array
    * A for loop runs through array structure and if word found returns true
    * @param word , word to be searched for
    */

   public boolean isWord(String word) {
      Boolean isWord = false;

      for (int i = 0; i < DictionaryArray.length; i++) {
         if (word.equals(DictionaryArray[i]) == true)
            isWord = true;
      }
      return isWord;
   }

   /**
    * New word added into array data structure
    * Loops through array until null value found
    * When null value found, if word is not already contained in the
    * array then it is added at null position
    * 
    * @param word, word to be added into array
    */

   public void addNewWord(String word) {
      //used to return from method if data structure is full
      if (getDictionaryWords().size() == 30000)
         return;

      int nullposition = 0;
      for (int i = 0; i < DictionaryArray.length; i++) {

         if (DictionaryArray[i] == null) {
            nullposition = i;
            break;
         }
      }
      //checks isWord to prevent adding duplicate values
      if (isWord(word) == false) {
         DictionaryArray[nullposition] = word;
      }
   }

   /**
    * Word gets removed from array data structure
    * Loop to determine if word exists and find position of word in array
    * New temporary array created that is used to add all words from
    * existing array excluding the removed word
    * The existing array is then set to the temporary array.
    * 
    * @param word, word to be removed from array
    */

   public void removeWord(String word) {
      String[] NewArray = new String[30000];
      int PositionOfWord = -1;
      //loop to figure out if word exists in dictionary
      for (int i = 0; i < DictionaryArray.length; i++) {
         if (word.equals(DictionaryArray[i]) == true) {
            PositionOfWord = i;
         }
      }
      //elements copied from old array to temp array excluding removed word
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
   }

   /**
    * Returns words in created array data structure
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