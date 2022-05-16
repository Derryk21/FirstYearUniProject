package DictionaryTypes.abstractClasses;

import java.util.Arrays;

	/**Abstract class that extends Dictionary*/

public abstract class ArrayTypeDictionary extends Dictionary {

   /* ARRAY SPECIFIC METHODS handin2*/
   /**
    * This method sorts words in an array up until a final index
    * An element in the array is compared with rest of elements in 
    * the array. If the element selected is greater alphabetically 
    * it is placed to the right of element it is compared to.
    * 
    * @param arr, array to be sorted
    * @param finalIndexForSort, final index of word to be sorted
    */

   public void insertionSort(String[] arr, int finalIndexForSort) {
      for (int i = 0; i < finalIndexForSort; i++) {
         for (int k = i + 1; k > 0; k--) {
            if (arr[k].compareTo(arr[k - 1]) < 0) {
               String temporary = arr[k];
               arr[k] = arr[k - 1];
               arr[k - 1] = temporary;
            }
         }
      }
   }
   
   /**
    * This method sorts words in an array up until a final index
    * Recursion is used to break up the array into sub problems.
    * The words are compared in these sub arrays and then merged 
    * back together.
    * 
    * @param arr, array to be sorted
    * @param finalIndexForSort, final index of word to be sorted
    */
   
   public void mergeSort(String[] arr, int finalIndexForSort) {
      //+1 used to include final index
      sort(arr, 0, finalIndexForSort + 1);
   }

   /** This method is the sorting section of the merge sort algorithm
    * 
    * @param lo, start element of array to be sorted
    * @param hi, end element of array to be sorted
    */
   
   public void sort(String[] a, int lo, int hi) {
      int N = hi - lo;
      if (N <= 1) return;
      // recursively sort left and right halves
      int mid = lo + N / 2;
      sort(a, lo, mid);
      sort(a, mid, hi);
      // merge sorted halves (see previous slide)
      merge(a, lo, mid, hi);
   }
   
   /** This method merges the sub arrays of merge sort
    * 
    * @param lo, end element of array to be merged
    * @param mid, middle element of array to be merged
    * @param hi, end element of array to be merged
    */

   public void merge(String a[], int lo, int mid, int hi) {
      int N = hi - lo;
      String[] aux = new String[N];
      // merge into auxiliary array
      int i = lo, j = mid;
      for (int k = 0; k < N; k++) {
         if (i == mid) aux[k] = a[j++];
         else if (j == hi) aux[k] = a[i++];
         else if (a[j].compareTo(a[i]) < 0) aux[k] = a[j++];
         else aux[k] = a[i++];
      }
      // copy back
      for (int k = 0; k < N; k++) {
         a[lo + k] = aux[k];
      }
   }

   /**
    * This method sorts words using a combination of merge and insertion sort
    * 
    * Insertion sort is first used on segments of given size
    * Merge sort is then used to combine the segments into a combined sorted array
    * 
    * @param arr, array to be sorted
    * @param size, segments of given size to be sorted
    * @param finalIndexForSort, final index of word to be sorted
    */
   
   public void hybridSort(String[] arr, int size, int finalIndexForSort) {
      finalIndexForSort = finalIndexForSort + 1;
      String[] SortedArraySegments = new String[arr.length];
      int r = 0;
      if (arr.length <= size) {
         insertionSort(arr, finalIndexForSort);
         return;
      }
      if (arr.length > size) {
         for (int i = 0; i < finalIndexForSort; i += size) {
            String[] testtArray = insertionSortReturn(Arrays.copyOfRange(arr, i, Math.min(finalIndexForSort, i + size)), Arrays.copyOfRange(arr, i, Math.min(finalIndexForSort, i + size)).length - 1);
            for (int k = 0; k < testtArray.length; k++) {
               SortedArraySegments[r] = testtArray[k];
               r += 1;
            }
            insertionSort(Arrays.copyOfRange(arr, i, Math.min(finalIndexForSort, i + size)), Arrays.copyOfRange(arr, i, Math.min(finalIndexForSort, i + size)).length - 1);
         }
      }
      //used to copy unsorted array segments into main array
      int s = r;
      for (; s < arr.length; s++) {
         SortedArraySegments[s] = arr[s];
      }
      //copy old array into new array
      for (int e = 0; e < arr.length; e++) {
         arr[e] = SortedArraySegments[e];
      }
      mergeSort(arr, finalIndexForSort - 1);
   }

   /**
    * This method returns an array to be used in hybrid sort It works the same as
    * insertion sort except it returns the array
    * 
    * @param arr, array to be sorted
    * @param finalIndexForSort, final index of word to be sorted
    */
   
   public String[] insertionSortReturn(String[] arr, int finalIndexForSort) {
      for (int i = 0; i < finalIndexForSort; i++) {
         for (int k = i + 1; k > 0; k--) {
            if (arr[k].compareTo(arr[k - 1]) < 0) {
               String temporary = arr[k];
               arr[k] = arr[k - 1];
               arr[k - 1] = temporary;
            }
         }
      }
      return arr;
   }
}