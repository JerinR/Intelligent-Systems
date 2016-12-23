

public class NQueens {
	static int n;
	int chessBoard[][];
	static int arr[];
	
	public NQueens(int number){
		n = number;
		chessBoard = new int[n][n];
		arr = new int[n];
	}
	static int getRand(int mod){
	    return (int) ((Math.random()*10) % mod);
	}
	
	//
	public static NQueens createBoard(){
		NQueens queen = new NQueens(n);
		for(int i=0;i<n;i++){			
			{
				arr[i] = getRand(n);
				queen.chessBoard[arr[i]][i] = 1;
								
			}
		}
		return queen;
	}
	
	//the array of rows having queens
	public int[] getArray(){
		return arr;
	}
	
	

}
