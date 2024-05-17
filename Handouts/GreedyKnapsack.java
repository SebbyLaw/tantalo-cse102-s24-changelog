//----------------------------------------------------------------------------
//  GreedyKnapsack.java
//  Implementation of Greedy Algorithm for both the continuous and discrete
//  versions of the Knapsack problem. Note continuous case only works if 
//  function select() is set to be v[i]/w[i]. Also discrete case may not be
//  optimum on certain instances.
//----------------------------------------------------------------------------

class GreedyKnapsack{
   
   public static void main( String[] args ){
      
      int i; 
      double W = 9;                          // capacity of knapsack
      double[] v = {0, 5, 5, 9, 4, 4, 12};   // values of objects (disregard index 0)
      double[] w = {0, 1, 4, 3, 4, 1, 6};    // weights of objects (disregard index 0)
      int n = v.length-1;                    // number of objects
            
      double[] x_cont = {0, 0, 0, 0, 0, 0, 0};  // solution to continuous case [0, 1]
      double[] x_disc = {0, 0, 0, 0, 0, 0, 0};  // solution to discrete case {0, 1}
      double weight, value;
      
      // solve continuous case using greedy algorithm
      weight = 0.0;
      value  = 0.0;
      while( weight<W ){
         i = findBestObject(v, w, x_cont);
         if( i==0 ){
            break;
         }
         //System.out.println("best object = "+i);
         if( weight+w[i]<=W ){
            x_cont[i] = 1;
            weight += w[i];
            value  += v[i];
         }else{
            x_cont[i] = (W-weight)/w[i];
            weight = W;
            value += x_cont[i]*v[i];
         }
      }
      
      // print continuous solution
      System.out.println("\nContinuous Solution:");
      printSolution(x_cont);
      System.out.println("Value  = " + value );
      System.out.println("Weight = " + weight);  
      System.out.println();
      
      // solve discrete case using greedy algorithm
      weight = 0.0;
      value  = 0.0;
      while( weight<W ){
         i = findBestObject(v, w, x_disc);
         if( i==0 ){
            break;
         }
         if( weight+w[i]<=W ){
            x_disc[i] = 1;
            weight += w[i];
            value  += v[i];
         }else{
            x_disc[i] = -1;
         }
      }
      
      // print discrete solution
      System.out.println("Discrete Solution:");
      printSolution(x_disc);
      System.out.println("Value  = " + value );
      System.out.println("Weight = " + weight);
      System.out.println();      
   }
   
   // printSolution()
   // Print a formatted solution encoded as a bit array x[]
   static void printSolution(double[] x){
      int n = x.length-1;
      int i;
      for(i=1; i<=n; i++){
         //System.out.print(x[i]+" ");
         if( x[i]>0 ){
            System.out.printf("%5.1f%% of object %d%n", 100*x[i], i);
         }
      }
   }
      
   // findBestObject()
   // Returns the index of the "best" remaining object, or returns 0 if
   // no objects remain.
   static int findBestObject(double[] v, double[] w, double[] x){
      int n = v.length-1;
      int i, i_best = 0;
      double temp, max = 0;
      for(i=1; i<=n; i++){
         temp = select(i, v, w);
         if( x[i]==0 && (temp>max) ){
            max = temp;
            i_best = i;
         }         
      }
      return i_best;
   }
   
   // select()
   // Selection function for greedy knapsack problem
   static double select(int i, double[] v, double[] w){
      return v[i]/w[i];
   }

}


