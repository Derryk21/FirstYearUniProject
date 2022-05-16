package DictionaryTypes.TrieStructureComponents;

/**This class is used to store edges to traverse the trie data structure*/

public class Edge {
   char lastChar;
   private State newState;

   /** Constructor to initialize instance variables */
   public Edge(Character ch, State s) {
      lastChar = ch;
      newState = s;
   }

   /** Return last character */
   public char getLastChar() {
      return lastChar;
   }

   /** Method to call from another class to return last state */
   public State getLastState() {
      return newState;
   }
}