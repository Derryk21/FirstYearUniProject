package DictionaryTypes.TrieStructureComponents;

import java.util.ArrayList;

/**This class is used to store states to traverse the trie data structure*/

public class State {
   Integer StateId;
   Boolean acceptState;
   String CurrentwordUpUntil;
   private static ArrayList < Edge > outgoingEdges = new ArrayList < > ();

   /** Constructor */
   public State() {
      //remove objects created from previous calls
      outgoingEdges.removeAll(outgoingEdges);
   }

   /** Return ArrayList */
   public ArrayList < Edge > getArrayList() {
      return State.outgoingEdges;
   }

   /** Return Boolean state */
   public Boolean getAcceptState() {
      return this.acceptState;
   }

   /** Return current word */
   public String getCurrentwordUpUntil() {
      return this.CurrentwordUpUntil;
   }

   /** Change value of current word */
   public void setCurrentwordUpUntil(String b) {
      this.CurrentwordUpUntil = b;
   }

   /** Change value of current boolean state */
   public void setAcceptState(Boolean b) {
      this.acceptState = b;
   }
   /**Constructor 
   Used to initialize instance variables*/
   public State(int NN, boolean accept, String wordUpUntil) {
      StateId = NN;
      acceptState = accept;
      CurrentwordUpUntil = wordUpUntil;
   }

   /**
    * Method to remove link 
    * Method not used
    */
   public void removeLink(State s, Character ch) {
      s.acceptState = false;
   }

   /**
    * Method to add a new state and a new edge.
    * It calls a method to determine whether the current word exists. 
    * A new state is created and edge added if existing state not found.
    * 
    * @param s takes in a state
    * @param ch takes in a character
    */
   public void addLink(State s, Character ch) {
      StateId += 1;
      CurrentwordUpUntil = CurrentwordUpUntil + ch;

      //State is moved to next state if existing state found
      if (compareOutgoingEdges(StateId, ch, CurrentwordUpUntil) != null && acceptState != true) {
         s = compareOutgoingEdges(StateId, ch, CurrentwordUpUntil);
         return;
      }
      //new state and edge created
      State finalState = new State(StateId, acceptState, CurrentwordUpUntil);
      outgoingEdges.add(new Edge(ch, finalState));
   }

   /**
    * Takes in word and returns existing state.
    * A word is taken in and an existing state containing that
    * word is searched for. If the state is found, the state
    * is returned
    * 
    * @param NN compares the position of a character in a word
    * @param ch the current character of an edge
    * @param CurrentWordUpUntil is the current word
    */
   public State compareOutgoingEdges(int NN, char ch, String CurrentWordUpUntil) {
      for (int i = 0; i < outgoingEdges.size(); i++) {
         Integer size = outgoingEdges.get(i).getLastState().CurrentwordUpUntil.length();
         //State searched for and compare its current character with the character position in string
         //Aswell as compare the substring of state matches entirety of current word that is taken in as parameter
         if (outgoingEdges.get(i).getLastChar() == ch && outgoingEdges.get(i).getLastState().StateId == NN && outgoingEdges.get(i).getLastState().CurrentwordUpUntil.equals(CurrentWordUpUntil.substring(0, size)))
            return outgoingEdges.get(i).getLastState();
      }
      return null;
   }

   /**
    * Override toString 
    * Returns current word
    */
   public String toString() {
      return CurrentwordUpUntil;

   }

   /**
    * This method return an arraylist of outgoing words from all states
    */
   public ArrayList < String > test() {
      ArrayList < String > testt = new ArrayList < String > ();
      for (int i = 0; i < outgoingEdges.size(); i++) {
         testt.add(outgoingEdges.get(i).getLastState().CurrentwordUpUntil);
      }
      return testt;
   }

   /**
    * This method return an arraylist of characters from all states
    */
   public ArrayList < Character > testing() {
      ArrayList < Character > testt = new ArrayList < Character > ();
      for (int i = 0; i < outgoingEdges.size(); i++) {
         testt.add(outgoingEdges.get(i).getLastChar());
      }
      return testt;
   }

}