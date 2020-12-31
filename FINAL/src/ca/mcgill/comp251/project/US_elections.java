package ca.mcgill.comp251.project;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {

	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided){
      int[] votesToWin = new int[num_states];
      int bidens_bois = 0;
      int trumps_toddlers = 0;
      int total_delegates = 0;
      int available_delegates = 0;
      
      // loop to determine the number of delegetes guarenteed to Biden and Trump and calculate the 
      // number of votes required for Biden to win each undecided state
	  for (int i = 0; i < num_states; i++) {
	    total_delegates += delegates[i];
        if (votes_Trump[i] + votes_Undecided[i] < votes_Biden[i]) {
          bidens_bois += delegates[i];
          votesToWin[i] = 0;
        }
        else if (votes_Biden[i] + votes_Undecided[i] <= votes_Trump[i]) {
          trumps_toddlers += delegates[i];
          votesToWin[i] = 0;
        }
        else {
          available_delegates += delegates[i];
          int diff = Math.abs(votes_Biden[i]-votes_Trump[i]);
          int remainder = votes_Undecided[i] - diff;
          if (votes_Biden[i] > votes_Trump[i]) {
            votesToWin[i] = remainder/2 + 1;
          }
          else {
            votesToWin[i] = diff + remainder/2 + 1;
          }
        }
      }
	  
	  int delegates_needed = total_delegates/2 + 1 - bidens_bois;
	  
	  // determining if Biden has already won
	  if (delegates_needed <= 0) {
	    return 0;
	  }
	  
	  // determining if Trump has already won
	  if (trumps_toddlers >= Math.ceil(total_delegates/2.0)) {
	    return -1;
	  }
	  
	  int min_votes = findMinVoteCount(delegates_needed, delegates, votesToWin, num_states-1);
	  
	  if (min_votes == Integer.MAX_VALUE) {
	    return -1;
	  }
	  
	  return min_votes;

	}
	

	/**
	 * Uses principles of knapsack problem to dynamically build the minVotes solution, bottom-up.
	 * @param delegates_needed delegates biden must obtain to win the election
	 * @param delegates delegates of each state 
	 * @param votesToWin votes to win each state, 0 for decided states
	 * @param num_states total number of states
	 * @return
	 */
	public static int findMinVoteCount(int delegates_needed, int[] delegates, int[] votesToWin, int num_states) {
	
	  int[][] table = new int[num_states + 1][delegates_needed + 1];
	  int j, w = 0;
      for ( j = 0; j <= num_states; j++) 
      { 
          for (w = 0; w <= delegates_needed; w++)  
          { 
            if (j == 0) {
              if (votesToWin[j] == 0) {
                table[j][w] = Integer.MAX_VALUE;
              }
              else if (delegates[j] >= w) 
                table[j][w] = votesToWin[j];
              else
                table[j][w] = Integer.MAX_VALUE;
            }
            else if (votesToWin[j] == 0) {
              table[j][w] = table[j - 1][w];
            }
            else if (delegates[j] >= w){
              table[j][w] = Math.min(votesToWin[j], table[j - 1][w]);
              
            }
            else {
              
              int a = votesToWin[j] + table[j - 1][(w - delegates[j])];
              if (a < 0) {
                a = Integer.MAX_VALUE;
              }
              table[j][w] = Math.min(a, table[j - 1][w]);   
            }
          }
      }
      
      return table[num_states][delegates_needed]; 
  }
	
	
	public static void main(String[] args) {
	 try {
			String path = args[0];
      File myFile = new File(path);
      Scanner sc = new Scanner(myFile);
      int num_states = sc.nextInt();
      int[] delegates = new int[num_states];
      int[] votes_Biden = new int[num_states];
      int[] votes_Trump = new int[num_states];
 			int[] votes_Undecided = new int[num_states];	
      for (int state = 0; state<num_states; state++){
			  delegates[state] =sc.nextInt();
				votes_Biden[state] = sc.nextInt();
				votes_Trump[state] = sc.nextInt();
				votes_Undecided[state] = sc.nextInt();
      }
      sc.close();
      int answer = solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
      	System.out.println(answer);
    	} catch (FileNotFoundException e) {
      	System.out.println("An error occurred.");
      	e.printStackTrace();
    	}
  	}
	
	// public static int findMinVoteCount1(int delegates_needed, int[] delegates, int[] votesToWin, int i, int currentVoteCount) {
    //  
    //  if (delegates_needed <= 0) {
    //    return currentVoteCount;
    //  }
    //  
    //  if (i < 0) 
    //      return Integer.MAX_VALUE;
    //  
    //  if (votesToWin[i] == 0) {
    //    return findMinVoteCount1(delegates_needed, delegates, votesToWin, i-1, currentVoteCount);
    //  }
    //  
    //    return Math.min(findMinVoteCount1(delegates_needed-delegates[i], delegates, votesToWin, i-1, currentVoteCount + votesToWin[i]),
    //        findMinVoteCount1(delegates_needed, delegates, votesToWin, i-1, currentVoteCount)); 
    //}

}