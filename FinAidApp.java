
/**
 * Calculates qualified financial aid for Brandeis HSP Applicants 
 * (This program takes care of step #4 on the DO google task for Processing Financial Aid Applications)
 * 
 * @author Vanessa Chalem
 * @version 1/2/14
 */

import java.util.Scanner;
public class FinAidApp{
    public static void main (String[] args){

        //creates Scanner object for user interaction/input
        Scanner input = new Scanner(System.in);

        //calls methods passing the Scanner obj as parameters and stores their return values in the corresponding variables
        int houseSize = houseSize(input);
        int inCollege = numberInCollege(input);
        String state = stateOfResidence(input);
        int ageOfOlderParent = 50;
        int income = grossIncome(input);
        int tuition = estimatedTuitionCosts(input);

        //print values to make sure methods work
        System.out.println("house size=" + houseSize);
        System.out.println("number of dependents in college=" + inCollege);
        System.out.println("state of residence =" + state);
        System.out.println("adjusted gross income=" + income);
        System.out.println("estimated tuition costs=" + tuition);
        System.out.println("");

        
        //calculates qualified financial aid
        
        //available income is income minus allowances
        //availableIncome should really be = income - (state taxes+income protection);
        int availableIncome = income;
		
        //income is then multiplied by 22 to 47% to arrive at total contribution (high income, higher % used)
        Double contribution = availableIncome*.30;
		
        //contribution is then divided by number in college
        Double finalContribution = contribution/inCollege;
        System.out.println("expected contribution from family= " + finalContribution);
		
        //qualified financial aid is tuition minus how much the family can contribute
        Double need = tuition-finalContribution;
        //after calculating need divide by appropriate number depending on the program
		
        Double qualifAid = 0.0;
        //if BIMA or Genesis divide by 10
        if(tuition == 56000 || tuition == 60000){
            qualifAid = need/10;
        //if Imapct or 3D Game divide by 20
       } else if(tuition == 46000 || tuition == 50000){
            qualifAid = need/20;
       //if Mock Trial divide by 40
       }else if(tuition == 78000){
           qualifAid = need/40;
       }else{
           System.out.println("error");
       }
       System.out.println("qualified financial aid=" + qualifAid);

    }
    //prompts user for house size
    public static int houseSize(Scanner in){
          System.out.println("Enter the Household Size from tax form (Field 6D on US 1040 Form):");
          int size = in.nextInt();
          return size;
    }

    //prompts user for number of dependents in household who are in college/private school
    public static int numberInCollege(Scanner in){
          System.out.println("Enter the Number in College. By doing +1 for the participant, +1 for each dependent in private school and +1 for each dependent in college)");
          int  inCollege = in.nextInt();
          return inCollege;
    }

    //prompts user for family’s State of Legal Residence
    public static String stateOfResidence(Scanner in){
        System.out.println("Enter the State of Legal Residence as found in address portion of US 1040 if int'l use MA");
        String state = in.next();
        return state;
    }

    //prompts user for family’s Adjusted Gross Income
    public static int grossIncome(Scanner in){
        System.out.println("Enter the Adjusted Gross Income which is the number from tax form US1040 field 37:");
        int income = in.nextInt();
        return income;
    }

    //prompts user for summer program and returns the estimated Tuition Costs
    public static int estimatedTuitionCosts(Scanner in){
        System.out.println("what program are they applying for?");
        String program = in.next();
        int tuition = 0;
        //make if/else statements less case/space/word sensitive
        if (program.equals("BIMA")){
              tuition = 56000;
        }else if (program.equals("Genesis")){
              tuition = 60000;
        }else if (program.equals("Impact")){
              tuition = 46000;
        }else if (program.equals("3D Game")){
              tuition = 50000;
        }else if (program.equals("Mocktrial")){
              tuition = 78000;
        }else{
                System.out.println("the program name isn't valid");
        }
        return tuition;

    }
}