package ca.mcgill.comp251.a2;
import java.util.*;

class Assignment implements Comparator<Assignment>{
  int number;
  int weight;
  int deadline;


  protected Assignment() {
  }

  protected Assignment(int number, int weight, int deadline) {
    this.number = number;
    this.weight = weight;
    this.deadline = deadline;
  }



  /**
   * This method is used to sort to compare assignment objects for sorting. 
   */
  @Override
  public int compare(Assignment a1, Assignment a2) {
    // TODO Implement this
    if (a1.weight == a2.weight) {
      if (a1.deadline == a2.deadline) 
        return 0;
      if(a1.deadline < a2.deadline)
        return -1;
      return 1;
    }
    if (a1.weight < a2.weight) 
      return 1;
    return -1;
  }
}

public class HW_Sched {
  ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
  int m;
  int lastDeadline = 0;

  protected HW_Sched(int[] weights, int[] deadlines, int size) {
    for (int i=0; i<size; i++) {
      Assignment homework = new Assignment(i, weights[i], deadlines[i]);
      this.Assignments.add(homework);
      if (homework.deadline > lastDeadline) {
        lastDeadline = homework.deadline;
      }
    }
    m =size;
  }


  /**
   * 
   * @return Array where output[i] corresponds to the assignment 
   * that will be done at time i.
   */
  public int[] SelectAssignments() {
    //TODO Implement this
    //Sort assignments
    //Order will depend on how compare function is implemented
    Collections.sort(Assignments, new Assignment());


    // If homeworkPlan[i] has a value -1, it indicates that the 
    // i'th timeslot in the homeworkPlan is empty
    //homeworkPlan contains the homework schedule between now and the last deadline
    int[] homeworkPlan = new int[lastDeadline];
    for (int i=0; i < homeworkPlan.length; ++i) {
      homeworkPlan[i] = -1;
    }

    for (Assignment a : Assignments) {
      int i = a.deadline - 1;
      while(homeworkPlan[i] != -1 ) {
        i--;
        if (i == -1)
          break;
      }

      if (i != -1)
        homeworkPlan[i] = a.number;

    }

    return homeworkPlan;
  }
}




