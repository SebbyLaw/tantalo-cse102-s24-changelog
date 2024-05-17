//----------------------------------------------------------------------------
//  MatrixChainMultiplication.java
//  Implementation of Dynamic Programming for the Matrix-Chain Multiplication
//  problem.
//----------------------------------------------------------------------------

class MatrixChainMultiplication{
   
   public static void main( String[] args ){
      
      int i, j, k, d, min, temp, min_k; 
      //int[] p = {5, 10, 3, 12, 5, 50, 6};  // sizes of matrices in the chain
      int[] p = {10,20,30,10,40,10};       // sizes of matrices in the chain
      int n = p.length-1;                  // number of matrices in the chain
      int[][] M = new int[n+1][n+1];       // table of optimal subproblem values
      int[][] S = new int[n+1][n+1];       // table of optimal split points
      
      
      // Initialize lower triangles to 0
      for(i=1; i<=n; i++){
         for(j=1; j<=i; j++){
            M[i][j] = 0;
            S[i][j] = 0;
         }
      }

      // Compute off diagonals (d=j-i)
      for(d=1; d<n; d++){
         for(i=1; i<=(n-d); i++){
            j = i+d;
            min_k = k = i;
            min = M[i][k]+M[k+1][j]+p[i-1]*p[k]*p[j];
            for(k=i+1; k<j; k++){
               temp = M[i][k]+M[k+1][j]+p[i-1]*p[k]*p[j];
               if( temp<min ){
                  min = temp;
                  min_k = k;
               }
            }
            M[i][j] = min;
            S[i][j] = min_k;
         }
      }
      
      // print out M[][]
      System.out.println();
      PrintTable(M);
      System.out.println();
      
      // print out S[][]
      System.out.println();
      PrintTable(S);
      System.out.println();
      
      // print optimal parenthesization
      System.out.println();
      PrintParenthesization(S, 1, n);
      System.out.println();
      System.out.println();
           
   }

   static void PrintTable(int[][] T){
      int i, j;
      int n = T[0].length-1;
      System.out.println();
      for(i=1; i<=n; i++){
         for(j=1; j<=n; j++){
            System.out.printf("%5d ", T[i][j]);
         }
         System.out.println();
      }  
   }
   
   static void PrintParenthesization(int[][] T, int i, int j){
      int s = T[i][j];
      if( i<j ){
         System.out.printf("(");
         PrintParenthesization(T, i, s);
         System.out.printf("*");
         PrintParenthesization(T, s+1, j);
         System.out.printf(")");
      }else{
         System.out.printf("A_%d", i);
      } 
   }

}


