//
// MERGESORT CODE WAS FOUND AT:
// http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
// I adjusted it to work for this assignment, but the
// original code is not my own.
//
import java.lang.Object;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Mergesort {
  private String[][] numbers;
  private String[][] helper;

  private int number;

  public void sort(String[][] values) {
    this.numbers = values;
    number = values.length;
    this.helper = new String[number][4];
    mergesort(0, number - 1);
  }

  private void mergesort(int low, int high) {
    // check if low is smaller then high, if not then the array is sorted
    if (low < high) {
      // Get the index of the element which is in the middle
      int middle = low + (high - low) / 2;
      // Sort the left side of the array
      mergesort(low, middle);
      // Sort the right side of the array
      mergesort(middle + 1, high);
      // Combine them both
      merge(low, middle, high);
    }
  }

  private void merge(int low, int middle, int high) {

    // Copy both parts into the helper array
    for (int i = low; i <= high; i++) {
		for( int y = 0; y < 4; y++ )
			helper[i][y] = numbers[i][y];
    }

    int i = low;
    int j = middle + 1;
    int k = low;
    // Copy the smallest values from either the left or the right side back
    // to the original array
    while (i <= middle && j <= high) {
      if (Integer.parseInt(helper[i][2]) <= Integer.parseInt(helper[j][2])) {
		for( int y = 0; y < 4; y++ )
			numbers[k][y] = helper[i][y];
        i++;
      } else {
		for( int y = 0; y < 4; y++ )
			numbers[k][y] = helper[j][y];
        j++;
      }
      k++;
    }
    // Copy the rest of the left side of the array into the target array
    while (i <= middle) {
		for( int y = 0; y < 4; y++ )
			numbers[k][y] = helper[i][y];
      k++;
      i++;
    }

  }
} 