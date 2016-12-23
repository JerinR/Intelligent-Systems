



class Puzzle implements Comparable<Puzzle>{
	int[][] board;
	private long priority;	
	private Puzzle parent;
	private long level;
	
	public Puzzle(int[][] arr, Puzzle parent){
		board = arr;
		this.parent = parent;
	}
	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}
	
	public Puzzle getParent() {
		return parent;
	}
	public void setParent(Puzzle parent) {
		this.parent = parent;
	}


	
	/**
	 * Description : get the x coordinate of an integer in a puzzle
	 * @param number
	 * @return int position
	 */
	public int getPositionX(int number){
		int x = 0;
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				if(board[i][j] == number){
					x = i;
					return x;
				}
			}
		}
		return x;
	}
	
	/**
	 * Description : get the y coordinate of an integer in a puzzle
	 * @param number
	 * @return int position
	 */
	public int getPositionY(int number){
		int y = 0;
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				if(board[i][j] == number){
					y = j;
					return y;
				}
			}
		}
		return y;
	}
	
	/**
	 * Description : shifting the empty space to the right
	 * @return array
	 */
	public int[][] right(){
		int[][] p = new int[board.length][board.length];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				p[i][j] = board[i][j];
			}
		}
		int x = getPositionX(0);
		int y = getPositionY(0);

		if (y + 1 < board.length) {
			p[x][y] = p[x][y + 1];
			p[x][y + 1] = 0;
		}
		return p;
	}
	
	/**
	 * Description : shifting the empty space to the left
	 * @return array
	 */
	public int[][] left(){
		int[][] p = new int[board.length][board.length];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				p[i][j] = board[i][j];				
			}
		}
		int x = getPositionX(0);
		int y = getPositionY(0);

		if (y > 0) {
			p[x][y] = p[x][y-1];
			p[x][y-1] = 0;
		}
		return p;
	}
	
	/**
	 * Description : shifting the empty space to the down
	 * @return array
	 */
	public int[][] down(){
		int[][] p = new int[board.length][board.length];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				p[i][j] = board[i][j];
			}
		}
		int x = getPositionX(0);
		int y = getPositionY(0);

		if (x + 1 < board.length) {
			p[x][y] = p[x + 1][y];
			p[x + 1][y] = 0;
		}

		return p;
	}
	
	/**
	 * Description : shifting the empty space to the up
	 * @return array
	 */
	public int[][] up(){
		int[][] p = new int[board.length][board.length];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				p[i][j] = board[i][j];
			}
		}
		int x = getPositionX(0);
		int y = getPositionY(0);

		if (x > 0) {
			p[x][y] = p[x - 1][y];
			p[x - 1][y] = 0;
		}

		return p;
	}
	
	/**
	 * Description : to check if two array are equal or not
	 * @param ar1
	 * @param ar2
	 * @return boolean
	 */
	public boolean isEqual(int[][]ar1, int[][]ar2){

		for(int i =0;i<ar1.length;i++){
			for(int j=0;j<ar1.length;j++){
				if(ar1[i][j]!=ar2[i][j])
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Description : make a unique number for a puzzle board state to store for visited state
	 * @return int
	 */
	public long getHash() {
		long h = 0;
		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board.length; ++j)
				h = h * 10 + (board[i][j]);
		return h;
	}
	
	@Override
	public int compareTo(Puzzle otherPriority) {
		if(priority>otherPriority.priority)
			return 1;
		else if(priority<otherPriority.priority)
			return -1;
		return 0;
	}

	
}