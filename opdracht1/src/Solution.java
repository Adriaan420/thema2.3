import java.util.Stack;

/** the solution is a sequence of cards placed on the board according to the card positions
 example without border
 */

public class Solution extends Stack<Candidate>{


	// The board is an 2D array.
	//   0 1 2 3
	// 0 . . - .
	// 1 - - - .
	// 2 . - - -
	// 3 . . - .

	private static final int RIJEN = 4;
	private static final int KOLOMMEN = 4;

	private static final int[] rowArray     = { 0, 1, 1, 1, 2, 2, 2, 3 };
	private static final int[] columnArray  = { 2, 0, 1, 2, 1, 2, 3, 2 };

	private Candidate[][] board;

	// card positions on the board
	// the first card position on the board are
	// {0,2}, {1,0}. {1,1}
	private int[] row;
	private int[] column;
	//  indices of adjacent cards in the solution.
	//                 0   1  2   3   4    5     6    7
	int [] [] check = {{},{},{1},{0},{2},{3,4},{5,6},{7}};


	public Solution(){
		this.board	=	new Candidate[RIJEN][KOLOMMEN];
		this.row	= 	rowArray;
		this.column = 	columnArray;

	}

	// Checks whether a candidate with card CardChar is in
	// an adjacent position of the board position (row, column)
	// @param row, column, candidate
	// @return Boolean indicating if cardChar is found.
	// can be used in the methods fits and isCorrect
	private boolean bordersCard(int row, int column, char cardChar){
	boolean cardFound = false;
		if(row > 0 && board[row - 1][column] != null && board[row - 1][column].getCardChar() == cardChar){
			cardFound = true;
		}
		if(row + 1 < RIJEN && board[row + 1][column] != null && board[row + 1][column].getCardChar() == cardChar){
			cardFound = true;
		}
		if(column > 0 && board[row][column - 1] != null && board[row][column - 1].getCardChar() == cardChar){
			cardFound = true;
		}
		if(column + 1 < KOLOMMEN && board[row][column + 1] != null && board[row][column + 1].getCardChar() == cardChar){
			cardFound = true;
		}
		return cardFound;
	}


	/**
	 * Checks whether candidate card of same kind.
	 * Checks whether by placing candidate the solution so far still complies with the rules
	 * @param candidate
	 * @return boolean indicating whether this candidate can be put in the
	 * next free position.
	 */
	//Opdracht 4
	public boolean fits(Candidate candidate){

		int nextLocationIndex = this.size();
		char currentChar = candidate.getCardChar();

		int nextRowCoordinate = row[nextLocationIndex];
		int nextColCoordinate = column[nextLocationIndex];

		return !bordersCard(nextRowCoordinate, nextColCoordinate, currentChar) ;

	}

	public void record(Candidate candidate){
		int i = this.size();                    // i = index in this stack of next for the next candidate
		board[row[i]][column[i]] = candidate;   // x = row, y = column
		this.push(candidate);
	}

	public boolean complete(){
		return this.size() == 8 && isCorrect();
	}

	public void show(){
		System.out.println(this);
	}

	public Candidate eraseRecording(){

		int i = this.size()-1;           // i= index of the candidate that is removed from this Stack;
		board[row[i]][column[i]] = null; // remove candidate from board
		return this.pop();
	}

	// can be used in method isCorrect
	private char mustBeAdjacentTo(char card){

		if(card=='A'){
			return 'K';
		}
		if (card=='K'){
			return 'Q';
		}
		if (card=='Q'){
			return 'J';
		}
		return '?'; //error
	}

	/**
	 * Checks whether the rules below are fulfilled
	 * For the positions that can be checked for solution so far.
	 * Rules:
	 * Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king).
	 * Elke heer grenst aan een vrouw (queen).
	 * Elke vrouw grenst aan een boer (jack).
	 * @return true if all checks are correct.
	 */
	// uses methods borderCard and mustBeAdjacent to
	private boolean isCorrect() {
		boolean isCorrect = true;
		Candidate candidate;
		char mustBeAdjacentTo;
		int row;
		int column;
		for(int index = 0; index < rowArray.length; index++){
			row = rowArray[index];
			column = columnArray[index];
			candidate = board[row][column];
			if(candidate != null){
				mustBeAdjacentTo = mustBeAdjacentTo(candidate.getCardChar());
				if( mustBeAdjacentTo != '?' && !bordersCard(
						row,
						column,
						mustBeAdjacentTo )
						) {
					isCorrect = false;
				}
			}
		}
		return isCorrect;
	}


	/**
	 * @return a representation of the solution on the board
	 */
	public String toString(){
		String indentation = " ";
		StringBuilder buffer = new StringBuilder();
		buffer.append(indentation).append(indentation);
		buffer.append("\n");
		for(int j = 0; j < RIJEN; j++){
			for (int x = 0; x < KOLOMMEN; x++){
				buffer.append(indentation);
				if(board[x][j] != null){
					buffer.append(board[x][j].getCardChar());
				}else{
					buffer.append('-');
				}
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

}