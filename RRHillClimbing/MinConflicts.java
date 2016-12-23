
import java.util.*;
public class MinConflicts {
	private static int no_of_states = 0;
	private static int no_of_restarts = 0;
	private static int size = 0;
	private static int[] conflicts;
	
	//no of conflicts
	private static int heuristics(int col, int row, int[] arr){
		return  noOfDiaCollisions(col,row,arr) + noOfRowCollisions(col,row,arr);
	}
	
	static int getRand(int mod){
	    return (int) ((Math.random()*10) % mod);
	}
	
	private static void calculateConf(int[] arr){
		for(int i=0;i<size;i++){
			conflicts[i] = heuristics(i, arr[i], arr);
		}
	}
	private static boolean isHeuZero(int[] arr){
		
		for(int i=0;i<size;i++){
			if(conflicts[i]!=0)
				return false;
		}
		return true;
	}
	//row wise conflicts
	static int noOfRowCollisions(int col, int row, int arr[]){
		int count = 0;		
			for(int j=0;j<size;j++){
				if(arr[col]==arr[j] && j!=col)
					count++;
			}		
		return count;
	}
	
	//diagonal conflicts
	private static int noOfDiaCollisions(int col, int row, int[] arr){
		int count = 0;
		int leftAdd[] = new int[size];
		int rightSub[] = new int[size];
		for(int i=0;i<size;i++){
			leftAdd[i] = arr[i]+i;
		}
		for(int i=0;i<size;i++){
			rightSub[i] = arr[i]-i;
		}
		for(int i=0;i<size;i++){			
			if(leftAdd[col] == leftAdd[i] && i!= col)
				count++;			
		}
		for(int i=0;i<size;i++){			
			if(rightSub[col] == rightSub[i] && i!=col)
				count++;			
		}
		return count;
	}

	//min conflicts
	private static void minConflicts(int[] arr){
		boolean flag = false;		
		
		if(isHeuZero(arr)){
			System.out.println("SOLUTION FOUND!!!");
			printBoard(arr);
			return;
		}
		
		if(no_of_states<50){
			for(int i=0;i<size;i++){
				int pos = getRand(size);//select a random queen
				if(conflicts[pos]!=0){
					for(int j=0;j<size;j++){
						int oldval = arr[pos];//preserve the old position of the queen
						if(oldval!=j){
							arr[pos] = j;
							no_of_states++;
							if(heuristics(pos, j, arr)<conflicts[pos]){
								calculateConf(arr);//calculate the heuristics for each queen again after modification
								if(isHeuZero(arr)){
									System.out.println("SOLUTION FOUND!!!");
									printBoard(arr);
									flag = true;
									return;
								}
								else{
									minConflicts(arr);
									return;
								}
							}
							arr[pos] = oldval;
						}
					}
				}
			}
	}
		
		//if no solution is found restart 
		if(!flag){
			System.out.println("\nRESTART");
			no_of_restarts++;
			no_of_states = 0;
			NQueens current = new NQueens(size);
			current = current.createBoard();
			int currArr[] = current.getArray();
			calculateConf(currArr);
			System.out.println("Current state " );
			printBoard(currArr);
			minConflicts(currArr);
			return;
		}
		return;
	}
	
	// to print the chessBoard
	public static void printBoard(int[] arr){
		int chessboard[][] = new int[size][size];
		for(int i=0;i<size;i++){
			chessboard[arr[i]][i] = 1;
		}
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(chessboard[i][j] + " ");
			}			
			System.out.println();
		}
	}
	
	//driver
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter no of queens greater than 3 ");
		int n = s.nextInt();
		size = n;
		NQueens current = new NQueens(n);
		conflicts = new int[size];
		current = current.createBoard();
		int currArr[] = current.getArray();
		calculateConf(currArr);
		System.out.println("Current state");
		printBoard(currArr);
		minConflicts(currArr);
		System.out.println("No of state changes " + no_of_states);
		System.out.println("No of restarts " + no_of_restarts);
	}

}
