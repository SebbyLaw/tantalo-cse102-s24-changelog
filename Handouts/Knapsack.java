//----------------------------------------------------------------------------
//  Knapsack.java
//  Implementation of Dynamic Programming for the 0-1 kapsack problem.
//----------------------------------------------------------------------------

class Knapsack{
   
   public static void main( String[] args ){
      
      int i, j; 
      
      //int W = 10;                    // capacity of knapsack
      //int[] v = {0, 8, 10, 12, 11};  // values of objects (disregard index 0)
      //int[] w = {0, 5, 3, 2, 4};     // weights of objects (disregard index 0)
      //int n = v.length-1;            // number of objects
      
      int W = 9;                          // capacity of knapsack
      int[] v = {0, 5, 5, 9, 4, 4, 12};   // values of objects (disregard index 0)
      int[] w = {0, 1, 4, 3, 4, 1, 6};    // weights of objects (disregard index 0)
      int n = v.length-1;                 // number of objects
      
      int[][] V = new int[n+1][W+1];
      
      // Compute V[][] iteratively
      // Initialize row 1
      for(j=0; j<=W; j++){
         if( j<w[1] ){
            V[1][j] = 0;
         }else{
            V[1][j] = v[1];
         }
      }
      // Compute rows 2 through n
      for(i=2; i<=n; i++){
         for(j=0; j<=W; j++){
            if( j<w[i] ){
               V[i][j] = V[i-1][j];
            }else{
               V[i][j] = Math.max( V[i-1][j], v[i]+V[i-1][j-w[i]] );
            }               
         }
      }
      
      // print out V[][]
      System.out.println();
      PrintTable(V, w, v, W);
      System.out.println();
      
      // Print out an optimum set of objects
      System.out.print("steal the following objects: ");
      PrintOptimumSet(V, w, n, W);
      System.out.println("\n");
            
   }

   static void PrintTable(int[][] T, int[] w, int[] v, int W){
      int i, j;
      int n = v.length-1;
      System.out.print("i\tw\tv    j =");
      for(j=0; j<=W; j++) System.out.printf("%3d ", j);
      System.out.println();
      for(i=1; i<=n; i++){
         System.out.print(i+"\t"+w[i]+"\t"+v[i]+"\t");
         for(j=0; j<=W; j++){
            System.out.printf("%3d ",T[i][j]);
         }
         System.out.println();
      }  
   }

   static void PrintOptimumSet(int[][] T, int[] w, int i, int j){
      if( T[i][j]!=0 ){
         if( i>0 && T[i][j]==T[i-1][j] ){
            PrintOptimumSet(T, w, i-1, j);
         }else{ 
            if( i>0 ){
               PrintOptimumSet(T, w, i-1, j-w[i]);
            }
            System.out.print(i+" ");
         }
      }
   }

}


