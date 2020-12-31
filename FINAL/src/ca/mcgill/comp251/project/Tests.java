package ca.mcgill.comp251.project;
import org.junit.Assert;
import org.junit.Test;

public class Tests {
  
  @Test 
  public void nullCase() {
    int num_states = 0;
    int[] delegates = new int[] {};
    int[] votes_Biden = new int[] {};
    int[] votes_Trump = new int[] {};
    int[] votes_Undecided = new int[] {};
    int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
     Assert.assertEquals(-1, mySolution);
  }
   @Test
    public void openCase1() {
//       3
//       7 2401 3299 0
//       6 2401 2399 0
//       2 750 750 99
       int num_states = 3;
       int[] delegates = new int[] {7, 6, 2};
       int[] votes_Biden = new int[] {3299, 2301, 750};
       int[] votes_Trump = new int[] {3299, 2399, 750};
       int[] votes_Undecided = new int[] {2, 0, 99};
       int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(52, mySolution);
    }
    @Test
    public void openCase2() {
//        3
//        7 100 200 200
//        8 100 300 200
//        9 100 400 200
        int num_states = 3;
        int[] delegates = new int[] {7, 8, 9};
        int[] votes_Biden = new int[] {100, 100, 100};
        int[] votes_Trump = new int[] {200, 300, 400};
        int[] votes_Undecided = new int[] {200, 200, 200};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(-1, mySolution);
    }
    @Test
    public void openCase3() {
//        3
//        32 0 0 20
//        32 0 0 20
//        64 0 0 41
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {0, 0, 0};
        int[] votes_Trump = new int[] {0, 0, 0};
        int[] votes_Undecided = new int[] {20, 20, 41};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(32, mySolution);
    }
    //Trump
    //tie
    //enter algorithm
    //undecided will not change outcome
    @Test
    public void closeCase4() {
//        3
//        32 0 20 0
//        32 0 20 0
//        64 0 0 41
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {0, 0, 0};
        int[] votes_Trump = new int[] {20, 20, 0};
        int[] votes_Undecided = new int[] {0, 0, 41};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(-1, mySolution);
    }
    // Trump
    // no tie
    // does not enter algorithm
    // undecided will not change outcome
    @Test
    public void closeCase5() {
//        3
//        32 0 20 0
//        32 30 0 0
//        64 0 20 0
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {0, 30, 0};
        int[] votes_Trump = new int[] {20, 0, 20};
        int[] votes_Undecided = new int[] {0, 0, 0};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(-1, mySolution);
    }
    // Trump
    // tie
    // does not enter algorithm
    // undecided will not change outcome
    @Test
    public void closeCase6() {
//        3
//        32 0 20 0
//        32 0 20 0
//        64 20 0 0
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {0, 0, 20};
        int[] votes_Trump = new int[] {20, 20, 0};
        int[] votes_Undecided = new int[] {0, 0, 0};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(-1, mySolution);
    }
    // Trump
    // tie
    // does not enter algorithm
    // undecided will not change outcome
    @Test
    public void closeCase7() {
//        3
//        32 20 20 0
//        32 20 20 0
//        64 20 20 0
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {20, 20, 20};
        int[] votes_Trump = new int[] {20, 20, 20};
        int[] votes_Undecided = new int[] {0, 0, 0};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(-1, mySolution);
    }
    // Biden
    // does not enter algorithm
    // undecided will not change outcome
    @Test
    public void closeCase8() {
//        3
//        32 20 20 0
//        32 20 0 0
//        64 20 0 0
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {20, 20, 20};
        int[] votes_Trump = new int[] {20, 0, 0};
        int[] votes_Undecided = new int[] {0, 0, 0};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(0, mySolution);
    }
    // Biden
    // does not enter algorithm
    // undecided will not change outcome
    @Test
    public void closeCase9() {
//        3
//        32 30 20 5
//        32 0 20 5
//        64 6 0 5
        int num_states = 3;
        int[] delegates = new int[] {32, 32, 64};
        int[] votes_Biden = new int[] {30, 0, 6};
        int[] votes_Trump = new int[] {20, 20, 0};
        int[] votes_Undecided = new int[] {5, 5, 5};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(0, mySolution);
    }
    // Biden
    // Enter Algorithm
    @Test
    public void closeCase10() {
//       3
//       7 300 500 400
//       6 700 200 40
//       2 750 750 5
        int num_states = 3;
        int[] delegates = new int[] {7, 6, 2};
        int[] votes_Biden = new int[] {300, 700, 750};
        int[] votes_Trump = new int[] {500, 200, 750};
        int[] votes_Undecided = new int[] {400, 40, 5};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(3, mySolution);
    }
    // Biden
    // Enter Algorithm
    @Test
    public void closeCase11() {
//       3
//       3 400 500 109 // 105 votes needed
//       6 200 200 101 //51 votes needed
//       3 750 650 202 // 52 votes needed
        int num_states = 3;
        int[] delegates = new int[] {3, 6, 3};
        int[] votes_Biden = new int[] {400, 200, 750};
        int[] votes_Trump = new int[] {500, 200, 650};
        int[] votes_Undecided = new int[] {109, 101, 202};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(103, mySolution);
    }
    //Biden
    @Test
    public void closeCase12() {
//       5
//       3 400 500 109 // 105 votes needed
//       6 200 200 101 //51 votes needed
//       3 750 650 202 // 52 votes needed
//       6 200 200 101 //51 votes needed
//       3 750 650 202 // 52 votes needed
        int num_states = 5;
        int[] delegates = new int[] {3, 6, 3, 6, 3};
        int[] votes_Biden = new int[] {400, 200, 750, 200, 750};
        int[] votes_Trump = new int[] {500, 200, 650, 200, 650};
        int[] votes_Undecided = new int[] {109, 101, 202, 101, 202};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(102, mySolution);
    }
    //Biden
    @Test
    public void closeCase13() {
//       5
//       5 400 500 1000 // 551 votes needed
//       6 200 200 101 //51 votes needed
//       2 750 650 202 // 52 votes needed
//       4 20 200 50 // trump claims this one
//       2 750 650 202 // 52 votes needed

        int num_states = 5;
        int[] delegates = new int[] {5, 6, 2, 4, 2};
        int[] votes_Biden = new int[] {400, 200, 750, 20, 750};
        int[] votes_Trump = new int[] {500, 200, 650, 200, 650};
        int[] votes_Undecided = new int[] {1000, 101, 202, 50, 202};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(155, mySolution);
    }
    //Biden
    @Test
    public void closeCase14() {
//       5
//       2 400 500 1000 // 551 votes needed
//       1 200 200 101 //51 votes needed
//       2 750 650 202 // 52 votes needed
//       3 20 200 50 // trump claims this one
//       2 750 650 202 // 52 votes needed

        int num_states = 5;
        int[] delegates = new int[] {2, 2, 2, 3, 2};
        int[] votes_Biden = new int[] {400, 200, 750, 20, 750};
        int[] votes_Trump = new int[] {500, 200, 650, 200, 650};
        int[] votes_Undecided = new int[] {1000, 101, 202, 50, 202};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(155, mySolution);
    }

    //Biden
    @Test
    public void closeCase15() {
//       5
//       5 400 500 1000 // 551 votes needed
//       1 200 200 101 //51 votes needed
//       2 750 650 202 // 52 votes needed
//       4 20 200 50 // trump claims this one
//       2 750 650 202 // 52 votes needed

        int num_states = 5;
        int[] delegates = new int[] {5, 1, 2, 4, 2};
        int[] votes_Biden = new int[] {400, 200, 750, 20, 750};
        int[] votes_Trump = new int[] {500, 200, 650, 200, 650};
        int[] votes_Undecided = new int[] {1000, 101, 202, 50, 202};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(654, mySolution);
    }
    //Biden
    @Test
    public void closeCase16() {
//       5
//       5 400 500 1000 // 551 votes needed
//       1 200 200 101 //51 votes needed
//       2 750 650 2 // biden claims this one
//       4 20 200 50 // trump claims this one
//       2 750 650 2 // biden claims this one

        int num_states = 5;
        int[] delegates = new int[] {5, 1, 2, 4, 2};
        int[] votes_Biden = new int[] {400, 200, 750, 20, 750};
        int[] votes_Trump = new int[] {500, 200, 650, 200, 650};
        int[] votes_Undecided = new int[] {1000, 101, 2, 50, 2};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(551, mySolution);
    }
    //Biden
    @Test
    public void closeCase17() {
//       5
//       1 400 500 1000 // 551 votes needed
//       1 200 200 101 //51 votes needed
//       2 750 650 2 // biden claims this one
//       4 20 200 50 // trump claims this one
//       3 750 650 2 // biden claims this one

        int num_states = 5;
        int[] delegates = new int[] {1, 1, 2, 4, 3};
        int[] votes_Biden = new int[] {400, 200, 750, 20, 750};
        int[] votes_Trump = new int[] {500, 200, 650, 200, 650};
        int[] votes_Undecided = new int[] {1000, 101, 2, 50, 2};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(51, mySolution);
    }
    @Test
    public void closeCase18() {
//       5
//       1 400 400 501 // 251 votes needed
//       1 6500 6500 3 // 2 votes needed
//       1 750 750  1// 1 votes needed
//       1 20 20 1 // 1 votes needed
//       1 750 750 2 // 2 votes needed

        int num_states = 5;
        int[] delegates = new int[] {1, 1, 1, 1, 1};
        int[] votes_Biden = new int[] {400, 6500, 750, 20, 750};
        int[] votes_Trump = new int[] {400, 6500, 750, 20, 750};
        int[] votes_Undecided = new int[] {501, 3, 1, 1, 2};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(4, mySolution);
    }
    @Test
    public void closeCase19() {
//       5
//       1 400 400 501 // 251 votes needed
//       1 6500 6500 3 // 2 votes needed
//       1 750 750  0// trump
//       1 20 20 0 // trump
//       1 751 750 0 // biden

        int num_states = 5;
        int[] delegates = new int[] {1, 1, 1, 1, 1};
        int[] votes_Biden = new int[] {400, 6500, 750, 20, 751};
        int[] votes_Trump = new int[] {400, 6500, 750, 20, 750};
        int[] votes_Undecided = new int[] {501, 3, 0, 0, 0};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(253, mySolution);
    }

    @Test
    public void closeCase20() {
//       8
//       1 400 400 501 // 251 votes needed
//       4 6500 6500 3 // 2 votes needed
//       1 750 750  0// trump
//       2 20 20 0 // trump
//       1 751 750 0 // biden
//       5 400 500 1000 // 551 votes needed
//       1 200 200 101 //51 votes needed
//       2 750 650 202 // 52 votes needed

        int num_states = 8;
        int[] delegates = new int[] {1, 4, 1, 2, 1, 5, 1, 2};
        int[] votes_Biden = new int[] {400, 6500, 750, 20, 751, 400, 200, 750};
        int[] votes_Trump = new int[] {400, 6500, 750, 20, 750, 500, 200, 650};
        int[] votes_Undecided = new int[] {501, 3, 0, 0, 0, 1000, 101, 202};
        int mySolution = US_elections.solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        Assert.assertEquals(356, mySolution);
    }
}