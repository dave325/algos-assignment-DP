/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
    if(rodLength == 0){
      return 0;
    }
    int temp = Integer.MIN_VALUE;
    int comparison;
    for(int i = 1; i < rodLength; i++){
      // Check the rodlength - current iteration to return all possible outcomes 
      comparison = lengthPrices[i] + rodCuttingRecur(rodLength - i - 1,lengthPrices);
      // Return max value compared to temp
      temp = Math.max(temp, comparison);
    }
    return temp;
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    int[] arr = new int[rodLength];
    arr[0] = 0;
    int temp;
    for (int i = 0; i < rodLength; i++){
      temp = Integer.MIN_VALUE;
      // Load in information tabular 
      for(int j = 0; j < i; j++){
        temp = Math.max(temp, lengthPrices[i] + arr[i-j]);
      }
      arr[i] = temp;
    }
    return arr[rodLength - 1];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
     System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
