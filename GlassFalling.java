
/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  /**
   * 
   * @param floors
   * @param sheets
   * @return int
   */
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return

    /**
     * If floors have reached its limit, then return the floor Used as fail safe if
     * sheet does not break
     */
    if (floors == 1 || floors == 0)
      return floors;

    /**
     * If sheets are broken, return the floor it is broken at s
     */
    if (sheets == 1)
      return floors;

    /**
     * Variable used to compare against the result returned from recursion
     */
    int minCheck = Integer.MAX_VALUE;
    /**
     * Store the result of the recurstion
     */
    int result;

    /**
     * loop through each floor, recursively checking for the value if the sheet
     * breaks and if the sheet does not break
     */
    for (int x = 1; x <= floors; x++) {
      // Increase by 1 because recursion will return the value 1 level below
      // First option is if the sheet breaks, second parameter is if the sheet does not break, check the lower floors
      result = 1 + Math.max(glassFallingRecur(x - 1, sheets - 1), glassFallingRecur(floors - x, sheets));
      minCheck = Math.min(minCheck, result);
    }

    return minCheck;

  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  /**
   * 
   * @param floors
   * @param sheets
   * @param arr
   * @return int
   */
  public int glassFallingMemoized(int floors, int sheets, int[][] arr) {
    // Fill in here and change the return

    /**
     * Store results of each recursion
     */
    int result;

    // Set the default for the first and zero floors
    for (int i = 1; i <= floors; i++) {
      arr[i][1] = 1;
      arr[i][0] = 0;
    }

    // Set the floor value for first sheet to each floor
    for (int j = 1; j <= floors; j++)
      arr[1][j] = j;

    /**
     * Fill remaining substructures using 
     * 2 foro loops 
     */
    for (int i = 2; i <= floors; i++) {
      for (int j = 2; j <= sheets; j++) {
        arr[i][j] = Integer.MAX_VALUE;
        for (int x = 1; x <= j; x++) {
          //  Check the value of the previous substructures and select the max
          result = 1 + Math.max(arr[i - 1][x - 1], arr[i][j - x]);
          // If the resulting arrays are less, set current 
          // iteration to result
          if (result < arr[i][j])
            arr[i][j] = result;
        }
      }
    }

    // eggFloor[n][k] holds the result
    return arr[floors][sheets];
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {

    /**
     * If floors have reached its limit, then return the floor Used as fail safe if
     * sheet does not break
     */
    if (floors == 1 || floors == 0)
      return floors;

    /**
     * If sheets are broken, return the floor it is broken at s
     */
    if (sheets == 1)
      return floors;

    /**
     * Variable used to compare against the result returned from recursion
     */
    int minCheck = Integer.MAX_VALUE;
    /**
     * Store the result of the recurstion
     */
    int result;
    /**
     * loop through each floor, recursively checking for the value if the sheet
     * breaks and if the sheet does not break
     */
    for (int x = floors; x >= 1; x--) {
      try {
        // Increase by 1 because recursion will return the value 1 level below
      // First option is if the sheet breaks, second parameter is if the sheet does not break, check the lower floors
        result = 1 + Math.max(glassFallingBottomUp(x - 1, sheets - 1), glassFallingBottomUp(floors - x, sheets));
        minCheck = Math.min(minCheck, result);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    return minCheck;
  }

  public static void main(String args[]) {
    GlassFalling gf = new GlassFalling();

    // Do not touch the below lines of code, and make sure
    // in your final turned-in copy, these are the only things printed
    int minTrials1Recur = gf.glassFallingRecur(27, 2);
    int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
    int minTrials2Mem = gf.glassFallingMemoized(100, 3, new int[101][4]);
    int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
    System.out.println(minTrials1Recur + " " + minTrials1Bottom);
    System.out.println(minTrials2Mem + " " + minTrials2Bottom);
  }
}