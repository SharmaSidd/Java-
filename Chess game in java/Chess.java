package chess;

import java.util.NoSuchElementException;

public class Chess 
{
	BoardSquare[][] board = new BoardSquare[8][8];
	
/**
 * creates a new Chess object by using a 2-D array.
 */
	public Chess() 
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[0].length; j++) 
			{
				board[i][j] = BoardSquare.EMPTYSQUARE;
			}
		}
	}

/**
 * this copy constructor is called to initialize a new Chess object. It is a 
 * deep copy of the parameter object.
 * @param otherGame
 */
	public Chess(Chess otherGame) 
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[0].length; j++) 
			{
				board[i][j] = otherGame.board[i][j];
			}
		}
	}

/**
 * This method places piece in the square at the column and row position 
 * indicated by the values of col and row.If the values of either col or row 
 * are invalid, then an exception is thrown.
 * @param piece
 * @param col
 * @param row
 * @throws NoSuchElementException
 */
	public void setEntry(BoardSquare piece, char col, int row) 
						throws NoSuchElementException 
	{
		if (validPosition(row, col) == 0) 
		{
			throw new NoSuchElementException();
		} 
		else if (piece == null) 
		{
			throw new NullPointerException();
		} 
		else 
		{
			board[row - 1][toInteger(col)] = piece;
		}
	}

/**
 * This method will just return the contents of the square at the column and 
 * row position indicated by col and row. If they are invalid, an exception is
 * thrown.
 * @param col
 * @param row
 * @return
 * @throws NoSuchElementException
 */
	public BoardSquare getEntry(char col, int row) throws 
								NoSuchElementException 
	{
		if (validPosition(row, col) == 0) 
		{
			throw new NoSuchElementException();
		} 
		else 
		{
			return board[row - 1][toInteger(col)];
		}
	}

/**
 * This method returns the number of squares in the current object’s board that 
 * are occupied by the given piece.
 * @param piece
 * @return
 */
	public int count(BoardSquare piece) 
	{
		if (piece == null) 
		{
			throw new NullPointerException();
		}
		int count = 0;
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[0].length; j++) 
			{
				if (board[i][j].equals(piece))
					count++;
			}
		}
		return count;
	}

/**
 * counts the number of kings on board, and determines the winner based 
 * on the criteria given.
 * @return
 */
	public boolean blackWon() 
	{
		int countBlackKings = 0, countWhiteKings = 0;
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[0].length; j++) 
			{
				if (board[i][j].isBlack() && board[i][j].isKing()) 
				{
					countBlackKings++;
				}
				if (board[i][j].isWhite() && board[i][j].isKing()) 
				{
					countWhiteKings++;
				}
			}
		}
		
		// if there no white kings and there are some black kings
		if (countBlackKings > 0 && countWhiteKings == 0) 
		{
			return true;
		} else 
		{
			return false;
		}
	}

/**
 * counts the number of kings on board, and determines the winner based 
 * on the criteria given.
 * @return
 */
	public boolean whiteWon() 
	{
		int countBlackKings = 0, countWhiteKings = 0;
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[0].length; j++) 
			{
				if (board[i][j].isBlack() && board[i][j].isKing()) 
				{
					countBlackKings++;
				}
				if (board[i][j].isWhite() && board[i][j].isKing()) 
				{
					countWhiteKings++;
				}
			}
		}
		
		// if there no black kings but some white kings
		if (countBlackKings == 0 && countWhiteKings > 0) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

/**
 * This method checks the validity of the movement of a piece, by taking into
 * account its starting position and ending position. The hasEmptyPath() method
 * is being called here, if that method return false, this method automatically
 * returns false. Before all this, the validPosition() method is called to
 * check if the position data is correct or not.
 * @param startCol
 * @param startRow
 * @param endCol
 * @param endRow
 * @return
 */
	public boolean validMove(char startCol, int startRow, char endCol, int 
			endRow) 
	{
		int startColInt = toInteger(startCol);
		int endColInt = toInteger(endCol);
		
		// firstly the validity of the position data is checked
		if ((validPosition(startRow, startCol) == 0 || validPosition(endRow, 
				endCol) == 0)) 
		{
			return false;
		}
		
		// checks if the starting and ending piece are of different colors
		if ((board[startRow - 1][startColInt].isBlack()) && (board[endRow - 1]
				[endColInt].isWhite())
				|| (board[startRow - 1][startColInt].isWhite()) && (board
						[endRow - 1][endColInt].isBlack())) 
		{
			return true;
		}
		
		// checks if the starting and ending piece are of different colors
		if ((board[startRow - 1][startColInt].isBlack()) && (board[endRow - 1]
				[endColInt].isBlack())
				|| (board[startRow - 1][startColInt].isWhite()) && (board
						[endRow - 1][endColInt].isWhite())) 
		{
			return false;
		}

		else {
			//checks if the piece is a white pawn
			if ((board[startRow - 1][startColInt].isWhite()) && (board
					[startRow - 1][startColInt].isPawn()))
			{ 
				if ((endRow == startRow + 1) && (endColInt == startColInt)) 
				{
					return true;
				} else 
				{
					return false;
				}
			}
			
			//checks for black pawn
			if ((board[startRow - 1][startColInt].isBlack()) && (board
					[startRow - 1][startColInt].isPawn())) 
			{
				if ((endRow == (startRow - 1)) && (endColInt == startColInt)) 
				{
					return true;
				} else 
				{
					return false;
				}
			}
			
			// checks for knight, since the color does'nt matter, its not 
			//checked
			if ((board[startRow - 1][startColInt].isKnight())) 
			{
				if ((Math.abs(startRow - endRow) == 2 && Math.abs(endColInt - 
						startColInt) == 1)
						|| (Math.abs(startRow - endRow) == 1 && Math.abs
						(toInteger(endCol) - startColInt) == 2)) 
				{
					return true;
				} else 
				{
					return false;
				}
			}
			
			// checks for rook, since the color does'nt matter, its not checked
			if (board[startRow - 1][startColInt].isRook()) 
			{
				int i = Math.abs(endRow - startRow);
				int z = Math.abs(toInteger(endCol) - toInteger(startCol));
				if (hasEmptyPath(endRow, startRow, startCol, endCol) == false) 
				{
					return false;
				} else {
					if ((endRow == startRow + i) && (endColInt == startColInt)) 
					{
						return true;
					} else if ((endRow == startRow - i) && (endColInt == 
							startColInt))
					{
						return true;
					} else if ((endRow == startRow) && (endColInt == 
							startColInt - z)) 
					{
						return true;
					} else if ((endRow == startRow) && (endColInt == 
							startColInt + z)) 
					{
						return true;
					}
				}
			}
			
			// check for bishop
			if ((board[startRow - 1][startColInt].isBishop())) 
			{
				if (hasEmptyPath(endRow, startRow, startCol, endCol) == false) 
				{
					return false;
				}
				int i = Math.abs(endRow - startRow);
				if ((endRow == startRow + i) && (endColInt == startColInt + i))
				{
					return true;
				} else if ((endRow == startRow - i) && (endColInt == 
						startColInt + i)) 
				{
					return true;
				} else if ((endRow == startRow - i) && (endColInt == 
						startColInt - i)) 
				{
					return true;
				} else if ((endRow == startRow + i) && (endColInt == 
						startColInt - i)) 
				{
					return true;
				} else 
				{
					return false;
				}
			}
			
			// check for queen
			if ((board[startRow - 1][startColInt].isQueen())) 
			{
				int i = Math.abs(endRow - startRow);
				int z = Math.abs(toInteger(endCol) - toInteger(startCol));
				if (hasEmptyPath(endRow, startRow, startCol, endCol) == false) 
				{
					return false;
				}

				if ((endRow == startRow + i) && (endColInt == startColInt + z)) 
				{
					return true;
				} else if ((endRow == startRow - i) && (endColInt ==
						startColInt + z)) 
				{
					return true;
				} else if ((endRow == startRow - i) && (endColInt == 
						startColInt - z)) 
				{
					return true;
				} else if ((endRow == startRow + i) && (endColInt == 
						startColInt - z)) 
				{
					return true;
				} else if ((endRow == startRow + i) && (endColInt ==
						startColInt)) 
				{
					return true;
				} else if ((endRow == startRow - i) && (endColInt ==
						startColInt)) 
				{
					return true;
				} else if ((endRow == startRow) && (endColInt == 
						startColInt - z)) 
				{
					return true;
				} else if ((endRow == startRow) && (endColInt == 
						startColInt + z)) 
				{
					return true;
				} else {
					return false;
				}
			}
			
			//check for king
			if ((board[startRow - 1][startColInt].isKing())) 
			{
				if ((endRow == startRow + 1) && (endColInt == startColInt)) 
				{
					return true;
				} else if ((endRow == startRow - 1) && (endColInt == 
						startColInt)) 
				{
					return true;
				} else if ((endRow == startRow) && (endColInt == startColInt 
						+ 1)) 
				{
					return true;
				} else if ((endRow == startRow) && (endColInt == startColInt - 
						1)) 
				{
					return true;
				} else if ((endRow == startRow + 1) && (endColInt == 
						startColInt + 1)) 
				{
					return true;
				} else if ((endRow == startRow + 1) && (endColInt == 
						startColInt - 1)) 
				{
					return true;
				} else if ((endRow == startRow - 1) && (endColInt == 
						startColInt + 1)) 
				{
					return true;
				} else if ((endRow == startRow - 1) && (endColInt == 
						startColInt - 1)) 
				{
					return true;
				}
			}
			return false;
		}
	}

/**
 * this method puts the piece from the starting position to end position if
 * the pice has a valid move.	
 * @param startCol
 * @param startRow
 * @param endCol
 * @param endRow
 * @return
 */
	public boolean move(char startCol, int startRow, char endCol, int endRow) 
	{
		if (validMove(startCol, startRow, endCol, endRow)) 
		{
			int endRowLimit = endRow - 1;
			int startRowLimit = startRow - 1;
			int endColInt = toInteger(endCol);
			int startColInt = toInteger(startCol);
			board[endRowLimit][endColInt] = board[startRowLimit][startColInt];
			board[startRowLimit][startColInt] = BoardSquare.EMPTYSQUARE;
			return true;
		}
		return false;
	}

/**
 * method made to check the validity of the row and col data provided.
 * @param row
 * @param col
 * @return
 */
	public int validPosition(int row, char col) 
	{
		if ((row < 9 && row > 0) && (col <= 'h' && col >= 'a')) 
		{
			return 1;
		}
		return 0;
	}

/**
 * method made to convert the incoming char type col data to integer 
 * @param col
 * @return
 * @throws NoSuchElementException
 */
	public int toInteger(char col) throws NoSuchElementException 
	{
		int value;
		switch (col) 
		{
		case 'a':
			value = 0;
			break;
		case 'b':
			value = 1;
			break;
		case 'c':
			value = 2;
			break;
		case 'd':
			value = 3;
			break;
		case 'e':
			value = 4;
			break;
		case 'f':
			value = 5;
			break;
		case 'g':
			value = 6;
			break;
		case 'h':
			value = 7;
			break;
		default:
			value = -1;
		}
		return value;
	}

/**
 * method made to check whether the path from starting and ending position 
 * for that piece is clear or not, if not this method returns false value 
 * which is called in the validMove() method.It first identifies the piece,
 * then its direction, and then checks the path from start to end.
 * @param endRow
 * @param startRow
 * @param startCol
 * @param endCol
 * @return
 */
	
	public boolean hasEmptyPath(int endRow, int startRow, char startCol, 
			char endCol) 
	{ // if its a rook or queen
		if (board[startRow - 1][toInteger(startCol)].isRook() || 
				board[startRow - 1][toInteger(startCol)].isQueen()) 
		{ // if the piece is moving vertically upwards
			if ((endRow > startRow) && (toInteger(startCol)) == 
					(toInteger(endCol))) 
			{
				for (int i = endRow - 1; i > startRow - 1; i--) 
				{
					if (!board[i][toInteger(startCol)].isEmptySquare()) 
					{
						return false;
					}
				}
			} // // if the piece is moving vertically downwards
			else if ((endRow < startRow) && (toInteger(startCol)) == 
					(toInteger(endCol))) 
			{
				for (int i = endRow - 1; i < startRow - 1; i++) 
				{
					if (!board[i][toInteger(startCol)].isEmptySquare()) 
					{
						return false;
					}
				}
			} // if the piece is moving horizontally 
			else if ((endRow == startRow) && (toInteger(startCol)) > 
			(toInteger(endCol))) 
			{
				for (int i = toInteger(endCol); i < toInteger(startCol); i++) 
				{
					if (!board[startRow][i].isEmptySquare()) 
					{
						return false;
					}
				}
			}  // if the piece is moving horizontally
			else if ((endRow == startRow) && (toInteger(startCol)) < 
					(toInteger(endCol))) {
				for (int i = toInteger(startCol) + 1; i < toInteger(endCol); 
						i++) 
				{
					if (!board[startRow][i].isEmptySquare()) 
					{
						return false;
					}
				}
			}
		} //if the piece is a bishop or queen
		else if (board[startRow - 1][toInteger(startCol)].isBishop()
				|| board[startRow - 1][toInteger(startCol)].isQueen()) 
		{// if the piece is moving diagonally down to the right
			if ((startRow > endRow) && (toInteger(startCol) > toInteger
					(endCol))) 
			{
				for (int i = endRow - 1; i < startRow; i++) 
				{
					for (int j = toInteger(endCol); j < toInteger(startCol); 
							j++) 
					{
						if (!board[i][j].isEmptySquare()) 
						{
							return false;
						}
					}
				}

			} // if the piece is moving diagonally down to the left
			else if ((startRow > endRow) && (toInteger(startCol) < 
					toInteger(endCol))) 
			{
				for (int i = endRow - 1; i < startRow; i++) 
				{
					for (int j = toInteger(endCol); j > toInteger(startCol); 
							j--) 
					{
						if (!board[i][j].isEmptySquare()) 
						{
							return false;
						}
					}
				}

			} // if the piece is moving diagonally upwards to the left
			else if ((startRow < endRow) && (toInteger(startCol) > 
			toInteger(endCol))) 
			{
				for (int i = endRow - 1; i > startRow; i--) 
				{
					for (int j = toInteger(endCol); j < toInteger(startCol); 
							j++) 
					{
						if (!board[i][j].isEmptySquare()) 
						{
							return false;
						}
					}
				}
			} // if the piece is moving diagonally upwards to the right
			else if ((startRow < endRow) && (toInteger(startCol) < 
					toInteger(endCol))) 
			{
				for (int i = endRow - 1; i < startRow; i++) 
				{
					for (int j = toInteger(endCol); j > toInteger(startCol); 
							j--) 
					{
						if (!board[i][j].isEmptySquare()) 
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
}