


import java.util.Scanner;

public class RRHillClimb {
	private static int no_of_restarts = 0;
	private static int no_of_states = 0;
	private static int size = 0;
	
	//no of conflicts
	private static int heuristics(int[] arr){
		return  noOfDiaCollisions(arr) + noOfRowCollisions(arr);
	}
	
	//row wise conflicts
	static int noOfRowCollisions(int[] arr){
		int count = 0;
		
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){
				if(arr[i]==arr[j])
					count++;
			}
			
		}
		return count;
	}
	
	//diagonal conflicts
	private static int noOfDiaCollisions(int[] arr){
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
			for(int j=i+1;j<size;j++){
				if(leftAdd[i] == leftAdd[j])
					count++;
			}
		}
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){
				if(rightSub[i] == rightSub[j])
					count++;
			}
		}
		return count;
	}
	
	//hill climbing with random restart
	private static void hillClimbing(int[] arr, int h){		
		//no conflicts
		if(h == 0){
			System.out.println("\nSOLUTION FOUND!!!");
			printBoard(arr);
			return;
		}
		else{
			int newArr[] = {-1};//to check if restart is needed
			for(int i=0;i<size;i++){
				int row = arr[i];
				for(int j=0;j<size;j++){
					//ignoring the row already having a queen
					if(row!=j){
						arr[i] = j;						
						int newh = heuristics(arr);// heuristics of new state
						if(newh<h){							
							newArr[0] = j;
							no_of_states++;
							hillClimbing(arr, newh);
							return;
						}
					}
				}
				arr[i] = row;
			}
			
			//if the algorithm gets stuck, restart it
			if(newArr[0]==-1){
				System.out.println("\nRESTART");
				no_of_restarts++;
				NQueens current = new NQueens(size);
				current = current.createBoard();
				int currArr[] = current.getArray();
				h = heuristics(currArr);
				System.out.println("Current state with h = " + h);
				printBoard(currArr);
				hillClimbing(currArr,h);
				
			}
			return;	
		}
		
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
		current = current.createBoard();
		int currArr[] = current.getArray();
		int hr = heuristics(currArr);
		System.out.println("Current state with h = " + hr);
		printBoard(currArr);
		hillClimbing(currArr,hr);
		System.out.println("No of restarts " + no_of_restarts);
		System.out.println("No of state changes " + no_of_states);
	}


}
