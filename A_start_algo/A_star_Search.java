


import java.io.*;
import java.util.*;



public class A_star_Search {
	private ArrayList<Integer> start,goal;
	private Puzzle initialState,finalState;
	private static int n;
	private long noOfMoves = 0;
	private long noOfNodesExp = 1;	
	PriorityQueue<Puzzle> queue;
	private long level = 0;
	
	public A_star_Search(Puzzle start, Puzzle goal){
		initialState = start;
		finalState = goal;
		queue = new PriorityQueue<Puzzle>();
		this.start = new ArrayList<Integer>();
		this.goal = new ArrayList<Integer>();
	}

	/**
	 * Description : returns true is the puzzle is solvable
	 * @return boolean
	 */
	private boolean isSolvable(){
		if(countInversions()%2==0)
			return true;
		return false;
	}
	
	/**
	 * Description : converts any array into arrayList
	 * @param puzzle
	 * @return ArrayList
	 */
	ArrayList ArrayToList(Puzzle puzzle){
		ArrayList<Integer> arrayListofPuzzle = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				arrayListofPuzzle.add(puzzle.board[i][j]);
			}
		}
		return arrayListofPuzzle;
	}
	
	public void ArrayToList(){
		ArrayList<Integer> arrayListofPuzzle = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				start.add(initialState.board[i][j]);
				goal.add(finalState.board[i][j]);
			}
		}
		
	}
	
	/**
	 * Description : find the number of inverstions in the puzzle
	 * @return int count of inversions
	 */
	private int countInversions(){
		int count = 0;
		ArrayToList();

		for(int i=0;i<goal.size();i++){
			for(int j=0,k=0;j<start.size();j++,k++){
				if((goal.get(i)!=0) 
						&& goal.get(j)!=0){
				if((goal.indexOf(goal.get(i))>goal.indexOf(goal.get(j))) 
						&& (start.indexOf(goal.get(i))<start.indexOf(goal.get(j)))){
					{
						count++;
					}
				}
			}
			}
		}
		return count;
	}
	
	/**
	 * Description : find the manhattan distance
	 * @param puzzle
	 * @return int heuristics
	 */
	private int manhattanHeuristics(Puzzle puzzle){
		int h1 = 0; 
		int x,y,goalX,goalY;
		ArrayList<Integer> puzzleList = ArrayToList(puzzle);
		for(int i=0;i<puzzleList.size();i++){
			if(puzzleList.get(i)!=0){
				x = puzzle.getPositionX(puzzleList.get(i));
				y = puzzle.getPositionY(puzzleList.get(i));
				goalX = finalState.getPositionX(puzzleList.get(i));
				goalY = finalState.getPositionY(puzzleList.get(i));
				h1 += Math.abs(x-goalX) + Math.abs(y-goalY);
			}
		}
		return h1;
	}
	
	/**
	 * Description : cost function for A* f = g+h
	 * @param puzzle
	 * @param g
	 * @return int function
	 */
	private long function(Puzzle puzzle){
		long f = 0;
		long g = puzzle.getLevel();
		f = g + manhattanHeuristics(puzzle);
		return f;
	}
	
	/**
	 * Description : is puzzle the goal
	 * @param puzzle
	 * @return boolean
	 */
	private boolean isGoal(Puzzle puzzle){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(puzzle.board[i][j] != finalState.board[i][j])
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Description : expanding the node by moving the blank space
	 * @param puzzle
	 * @return list of nodes generated
	 */
	private PriorityQueue<Puzzle> searchNode(Puzzle puzzle){
	
		
		level = puzzle.getLevel() + 1;
		Puzzle p1 = new Puzzle(puzzle.up(),puzzle);
		p1.setLevel(level);
		p1.setPriority(function(p1));
		
		
		Puzzle p2 = new Puzzle(puzzle.down(),puzzle);
		p2.setLevel(level);
		p2.setPriority(function(p2));
		
		
		Puzzle p3 = new Puzzle(puzzle.left(),puzzle);
		p3.setLevel(level);
		p3.setPriority(function(p3));
		
		
		Puzzle p4 = new Puzzle(puzzle.right(),puzzle);
		p4.setLevel(level);
		p4.setPriority(function(p4));
		
		//if the state is not equal to parent and is not already visited add to the queue
		if(!p1.isEqual(p1.board,puzzle.board) && !isDoneState(p1))
			queue.add(p1);
		if(!p2.isEqual(p2.board,puzzle.board) && !isDoneState(p2))
			queue.add(p2);
		if(!p3.isEqual(p3.board,puzzle.board) && !isDoneState(p3))
			queue.add(p3);
		if(!p4.isEqual(p4.board,puzzle.board) && !isDoneState(p4))
			queue.add(p4);
		return queue;
	}
	
	private HashSet<Long> doneStates = new HashSet<Long>();

	/**
	 * Description : add puzzle which are already visited to done state
	 * @param state
	 */
	public void addToDoneStates(Puzzle state) {
		doneStates.add(state.getHash());
	}

	/**
	 * Description : to check if the puzzle state is already visited or not
	 * @param state
	 * @return boolean
	 */
	public boolean isDoneState(Puzzle state) {
		return doneStates.contains(state.getHash());
	}
	
	/**
	 * Description : construct the path for the solution
	 * @param p
	 */
	private void constructPath(Puzzle p){
		if(p.isEqual(p.board, initialState.board))
			return;
		else{
			constructPath(p.getParent());
			noOfMoves++;
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					System.out.print(p.board[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}
	static class InputReader{
		BufferedReader reader;
		StringTokenizer tokens;
		public InputReader(InputStream stream){
			reader = new BufferedReader(new InputStreamReader(stream));
			tokens = null;
		}
 
		public String next(){
			while(tokens == null || !tokens.hasMoreTokens()){
				try {
					tokens = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}
			return tokens.nextToken();

		}
 
		public Integer nextInt(){
			return Integer.parseInt(next());
		}
 
		public Long nextLong(){
			return Long.parseLong(next());
		}
	}

	//driver
	public static void main(String[] args){
		InputReader reader = new InputReader(System.in);
		PrintWriter writer = new PrintWriter(System.out);
		
		System.out.println("Enter n for the (n*n -1)-puzzle problem");
		int n = reader.nextInt();
		
		int[][] start = new int[n][n];
		int[][] goal = new int[n][n];
		Puzzle startState = new Puzzle(new int[n][n],null);
		Puzzle goalState = new Puzzle(new int[n][n],null);
		System.out.println("Enter the inital state");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				startState.board[i][j] = reader.nextInt();
			}
		}
		System.out.println("Enter the goal state");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				goalState.board[i][j] = reader.nextInt();
			}
		}
		A_star_Search search = new A_star_Search(startState,goalState);
		search.n = n;
		if(!search.isSolvable())
			System.out.println("The puzzle is not solvable");
		else{
			search.queue.add(search.initialState);// add initial state to the queue
			search.initialState.setPriority(search.function(search.initialState));
			System.out.println("The final path is");
			Puzzle current;
			while ((current = search.queue.poll()) != null) {
				if (search.isGoal(current)){ 
					search.constructPath(current);
					break;
				}
				search.noOfNodesExp++;
				search.addToDoneStates(current);
				search.searchNode(current);
			}
		}
			
			writer.println("Number of moves : "+ search.noOfMoves);
			writer.println("Number of nodes expanded :" + search.noOfNodesExp);
			writer.close();
	}
		
	
}
