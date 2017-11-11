package com.tapiadevelopmentinc.ai;

import java.util.Scanner;

public class TicTacToeAI {
	static String[][] boxValues = {{"-","-","-"},{"-","-","-"},{"-","-","-"}};
	public static void main(String[] args) {
		display(boxValues);
		Scanner keyboard = new Scanner(System.in);
		int turn = 0;
		/*
		while(turn < 9){
			System.out.println("Me");
			System.out.print("Row:");
			int row = keyboard.nextInt();
			System.out.print("Col:");
			int col = keyboard.nextInt();
			boxValues[row][col] = "O";
			System.out.println();
			display(boxValues);
			System.out.println("Computer");
			miniMax(boxValues, 0, 0);
			System.out.println();
			display(boxValues);
			System.out.println();

		}
		*/
		
		//int move= miniMax(boxValues, 0, 0);
		miniMax(boxValues, 0, 1);
		System.out.println();
		display(boxValues);
	}
	/*
	public static int miniMax(String[][] grid, int score, int turn){
		if(isTie(grid)){
			//System.out.println(0);
			return 0-turn;
		}else if(checkIndividual(grid ,"X")){
			//System.out.println(10);
			return 10-turn;
		}else if(checkIndividual(grid ,"O")){
			//System.out.println(-10);
			return -10-turn;
		}
		//find what choices i have
		int[][] choices = new int[9][2];
		String[][] temp = new String[3][3];	
		int numChoices = 0;
		for(int i =0; i<grid.length; i++){
			for(int j = 0; j<grid[i].length;j++){
				temp[i][j] = grid[i][j];
				if(grid[i][j].equals("-")){
					choices[numChoices][0] = i;
					choices[numChoices][1] = j;
					numChoices++;
				}
			}
		}
		int[][] scores = new int[numChoices][2];
		
		if(turn%2 ==0){
			if(numChoices == 1)	grid[choices[0][0]][choices[0][1]] = "X";
			for(int i = 0; i<numChoices;i++){
				//if(turn ==0) System.out.println("Call #" + i);
				//System.out.println("Turn " + turn + " " + (char)(97+i));
				temp[choices[i][0]][choices[i][1]] = "X";
				//display(temp);
				//System.out.println("");
				scores[i][0] = miniMax(temp, score, turn+1);
				temp[choices[i][0]][choices[i][1]] = "-";

				scores[i][1] = i;
			}
			//insertion sort
			for (int i=1;i<scores.length; ++i){	// tracks the sorted/unsorted side
				int[] nextNumber = scores[i];
				int j;
				for (j=i-1; j>=0; j--){	// does the shifting
					if (scores[j][0] > nextNumber[0])	// keep shifting if we are smaller than current element in the sorted side
						scores[j+1] = scores[j];
					else
						break;	// we don't need to shift anymore
					
				}
				scores[j+1] = nextNumber;
			}
			//display(choices, numChoices);
			//System.out.println("\n\n\n");
			//display(grid);
			//System.out.println("max score =" + scores[numChoices-1][0]);
			//System.out.println("move position is" + choices[scores[numChoices-1][1]][0] + "," + choices[scores[numChoices-1][1]][1] );
			if(turn == 0){
			//	System.out.println("The best move is above");
				boxValues[choices[scores[numChoices-1][1]][0]][choices[scores[numChoices-1][1]][1]] = "X";
			}
			return scores[numChoices-1][0];

		}else{
			if(numChoices == 1)	grid[choices[0][0]][choices[0][1]] = "O";
			for(int i = 0; i<numChoices;i++){
				//System.out.println("Turn " + turn + " " + (char)(97+i));
				temp[choices[i][0]][choices[i][1]] = "O";
				//display(temp);
				scores[i][0] = miniMax(temp, score, turn+1);
				scores[i][1] = i;
			}
			//insertion sort
			for (int i=1;i<scores.length; ++i){	// tracks the sorted/unsorted side
				int[] nextNumber = scores[i];
				int j;
				for (j=i-1; j>=0; j--){	// does the shifting
					if (scores[j][0] > nextNumber[0])	// keep shifting if we are smaller than current element in the sorted side
						scores[j+1] = scores[j];
					else
						break;	// we don't need to shift anymore
					
				}
				scores[j+1] = nextNumber;
			}
			//display(choices, numChoices);
			//System.out.println("\n\n\n");
			//display(grid);
			//System.out.println("min score =" + scores[0][0]);
			//System.out.println("move position is" + choices[scores[0][1]][0] + "," + choices[scores[0][1]][1] );
			return scores[0][0];
		}
		
		
		
	}
	*/
	
public static int miniMax(String[][] grid, int score, int turn){
		if(isTie(grid)){
			System.out.println(0);
			return 0;
		}else if(checkIndividual(grid ,"X")){
			System.out.println(10);
			return 10;
		}else if(checkIndividual(grid ,"O")){
			System.out.println(-10);
			return -10;
		}
		//find what choices i have
		int[][] choices = new int[9][2];
		String[][] temp = new String[3][3];	
		int numChoices = 0;
		for(int i =0; i<grid.length; i++){
			for(int j = 0; j<grid[i].length;j++){
				temp[i][j] = grid[i][j];
				if(grid[i][j].equals("-")){
					choices[numChoices][0] = i;
					choices[numChoices][1] = j;
					numChoices++;
				}
			}
		}
		int[][] scores = new int[numChoices][2];
		
		if(turn%2 ==0){
			if(numChoices == 1)	grid[choices[0][0]][choices[0][1]] = "X";
			for(int i = 0; i<numChoices;i++){
				if(turn ==0) System.out.println("Call #" + i);
				System.out.println("Turn " + turn + " " + (char)(97+i));
				temp[choices[i][0]][choices[i][1]] = "X";
				display(temp);
				System.out.println("");
				scores[i][0] = miniMax(temp, score, turn+1);
				temp[choices[i][0]][choices[i][1]] = "-";

				scores[i][1] = i;
			}
			//insertion sort
			for (int i=1;i<scores.length; ++i){	// tracks the sorted/unsorted side
				int[] nextNumber = scores[i];
				int j;
				for (j=i-1; j>=0; j--){	// does the shifting
					if (scores[j][0] > nextNumber[0])	// keep shifting if we are smaller than current element in the sorted side
						scores[j+1] = scores[j];
					else
						break;	// we don't need to shift anymore
					
				}
				scores[j+1] = nextNumber;
			}
			display(choices, numChoices);
			System.out.println("\n\n\n");
			display(grid);
			System.out.println("max score =" + scores[numChoices-1][0]);
			System.out.println("move position is" + choices[scores[numChoices-1][1]][0] + "," + choices[scores[numChoices-1][1]][1] );
			if(turn == 0){
				System.out.println("The best move is above");
				boxValues[choices[scores[numChoices-1][1]][0]][choices[scores[numChoices-1][1]][1]] = "X";
			}
			return scores[numChoices-1][0];

		}else{
			if(numChoices == 1)	grid[choices[0][0]][choices[0][1]] = "O";
			for(int i = 0; i<numChoices;i++){
				System.out.println("Turn " + turn + " " + (char)(97+i));
				temp[choices[i][0]][choices[i][1]] = "O";
				display(temp);
				scores[i][0] = miniMax(temp, score, turn+1);
				scores[i][1] = i;
			}
			//insertion sort
			for (int i=1;i<scores.length; ++i){	// tracks the sorted/unsorted side
				int[] nextNumber = scores[i];
				int j;
				for (j=i-1; j>=0; j--){	// does the shifting
					if (scores[j][0] > nextNumber[0])	// keep shifting if we are smaller than current element in the sorted side
						scores[j+1] = scores[j];
					else
						break;	// we don't need to shift anymore
					
				}
				scores[j+1] = nextNumber;
			}
			display(choices, numChoices);
			System.out.println("\n\n\n");
			display(grid);
			System.out.println("min score =" + scores[0][0]);
			System.out.println("move position is" + choices[scores[0][1]][0] + "," + choices[scores[0][1]][1] );
			return scores[0][0];
		}
		
		
		
	}
	
		
	public static void display(int[][] x, int count){
		for(int i =0; i< count; i++){
			for(int j = 0; j<2;j++){
				System.out.print(x[i][j] + "   ");
			}
			System.out.println("\n");
		}		
	}
	public static void display(String[][] x){
		for(int i =0; i<x.length; i++){
			for(int j = 0; j<x[i].length;j++){
				System.out.print(x[i][j] + "   ");
			}
			System.out.println("\n");
		}		
	}
    public static boolean isTie(String[][] x) {
        boolean value = true;
        for(int i =0 ; i< 3; i++){
            for(int j =0 ; j< 3; j++){
                if(x[i][j].equals("-")){
                    value = false;
                }
            }
        }
        return value;
    }
    public static boolean checkIndividual(String[][] grid, String letter) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == letter && grid[i][1] == letter && grid[i][2] == letter) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == letter && grid[1][i] == letter && grid[2][i] == letter) {
                return true;
            }

        }
        if(grid[0][0] == letter && grid[1][1] == letter && grid[2][2] == letter) {
            return true;
        }
        if(grid[0][2] == letter && grid[1][1] == letter && grid[2][0] == letter){
            return true;
        }
        return false;
       
    }

}
