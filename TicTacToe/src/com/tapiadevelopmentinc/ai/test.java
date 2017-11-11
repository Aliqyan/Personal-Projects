package com.tapiadevelopmentinc.ai;

public class test {

	public static void main(String[] args) {
		int[][] scores = {{3,4},{1,2}, {2,33}};
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
		for(int i =0; i<scores.length; i++){
				System.out.println(scores[i][0] + "\t" + scores[i][1]);
			
		}
	}

}
