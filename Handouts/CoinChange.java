//----------------------------------------------------------------------------
//  CoinChange.java
//  Implementation of Dynamic Programming for the Coin Change problem.
//----------------------------------------------------------------------------

class CoinChange{
   
   static final int INFINITY = 1_000_000; // stand in for infinity, may not
                                          // work for large N, or large d[i]
   
   public static void main( String[] args ){
      
      int i, j; 
      int N = 15;                    // value to be paid
      //int[] d = {0, 1, 2, 5, 7, 9};  // coin system (disregard index 0)
      int[] d = {0, 2, 4, 6};
      int n = d.length-1;            // number of coin types
      int[][] C = new int[n+1][N+1]; // table of optimal subproblem values
      
      // Compute C[][] from bottom up
      
      // Initialize column 0
      for(i=1; i<=n; i++){
         C[i][0] = 0;
      }
      // Compute rows 1 through n, columns 1 through N
      for(i=1; i<=n; i++){
         for(j=1; j<=N; j++){
            if( i==1 && j<d[1] ){
               C[1][j] = INFINITY;
            }else if( i==1 ){
               C[1][j] = 1+C[1][j-d[1]];
            }else if( j<d[i] ){
               C[i][j] = C[i-1][j];
            }else{
               C[i][j] = Math.min( C[i-1][j], 1+C[i][j-d[i]] );
            }               
         }
      }
      
      // print out C[][]
      System.out.println();
      PrintTable(C, d);
      System.out.println();
      
      // Print out optimum coin disbursal
      PrintOptimumCoins(C, d, n, N);
      System.out.println("\n");
           
   }

   static void PrintTable(int[][] T, int[] d){
      int i, j;
      int n = d.length-1;
      int N = T[0].length-1;
      System.out.print("i\td    j =");
      for(j=0; j<=N; j++) System.out.printf("%3d ", j);
      System.out.println();
      for(i=1; i<=n; i++){
         System.out.print(i+"\t"+d[i]+"\t");
         for(j=0; j<=N; j++){
            if( T[i][j]<INFINITY ){
               System.out.printf("%3d ",T[i][j]);
            }else{
               System.out.printf("%3s ", "*");
            }
         }
         System.out.println();
      }  
   }
   
   static void PrintCoinSubset(int[] d, int k){
      System.out.print("{");
      for(int i=1; i<k; i++) System.out.print(d[i]+", ");
      System.out.print(d[k]+"}");
   }

   static void PrintOptimumCoins(int[][] T, int[] d, int i, int j){
      if( j==0 ){
         return;
      }else if( T[i][j]>=INFINITY ){
         System.out.println("Amount "+j+" cannot be paid using coins in the set ");
         PrintCoinSubset(d, i);
         System.out.println();
      }else if( i==1 ){
         System.out.println("Pay one coin of type "+d[1]);
         PrintOptimumCoins(T, d, 1, j-d[1]);
      }else if( j<d[i] || T[i][j]==T[i-1][j] ){
         PrintOptimumCoins(T, d, i-1, j);
      }else{
         System.out.println("Pay one coin of type "+d[i]);
         PrintOptimumCoins(T, d, i, j-d[i]);         
      }
   }

}


