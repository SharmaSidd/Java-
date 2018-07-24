import cmscMarqueeLib.Cell;
import cmscMarqueeLib.ConfigValues;
import cmscMarqueeLib.DataManager;
import cmscMarqueeLib.Alphabet;

public class MarqueeDataManager implements DataManager {
	static final Cell RED_UNIT_CELL = new Cell(ConfigValues.FOREGROUND_COLOR);
	static final Cell WHITE_UNIT_CELL = new Cell(ConfigValues.BACKGROUND_COLOR);
	String message;
	int animationPattern, count;
	Cell[][] paddedArray;

	public MarqueeDataManager(String message, int animationPattern) {
		// constructor with message and animationPattern as parameters
		this.message = message;
		this.animationPattern = animationPattern;
		this.paddedArray = concatenatedCell(this.message);
		count = 0;
		

	}

	public Cell[][] step() {
		// step method for returning padded array 
		if (ConfigValues.MARQUEE_WIDTH + count != this.paddedArray[0].length) {
			count++;

		} else {
			count = 2;
		}

		Cell[][] display = getSubArray(count, this.paddedArray);
		return display;
	}

	private Cell[][] concatenatedCell(String message) {
		// Method for returning message as an array object 
		char Character;
		Cell[][] first, second;
		Cell[][] IntArray;
		Cell[][] ArrayCommencement = new Cell[ConfigValues.MARQUEE_HEIGHT]
				                             [ConfigValues.MARQUEE_WIDTH];
		for (int rows = 0; rows < ConfigValues.MARQUEE_HEIGHT; rows++) {
			for (int columns = 0; columns < ConfigValues.MARQUEE_WIDTH; 
					columns++) {
				ArrayCommencement[rows][columns] = WHITE_UNIT_CELL;
			}
		}
		Cell[][] display = ArrayCommencement;
		Cell[][] ArrayEnd = ArrayCommencement;
		Character = message.charAt(0);
		if (message.length() == 1) {
			ArrayCommencement = append(ArrayCommencement, GenerateBlankCell());
			first = append(ArrayCommencement, integerToCell(Alphabet.toIntArray
					(Character)));
			display = first;
		} else {
			ArrayCommencement = append(ArrayCommencement, GenerateBlankCell());
			first = append(ArrayCommencement, integerToCell(Alphabet.toIntArray
					(Character)));
			IntArray = append(first, GenerateBlankCell());
			first = IntArray;
			if (message.length() != 1) {
				for (int i = 1; i < message.length(); i++) {
					Character = message.charAt(i);
					second = integerToCell(Alphabet.toIntArray(Character));
					if (i != message.length() - 1) {
						display = append(append(first, second), 
								  GenerateBlankCell());
						first = display;
					} else {
						display = append(first, second);
						first = display;
					}
				}
			}
		}
		IntArray = append(display, ArrayEnd);
		display = IntArray;
		return display;
	}

	private Cell[][] append(Cell[][] first, Cell[][] second) {
		// method for returning appended array
		Cell[][] cell = new Cell[first.length][first[0].length + 
		                        second[0].length];
		for (int rows = 0; rows < (ConfigValues.CHARACTER_HEIGHT); rows++) {
			for (int columns = 0; columns < first[0].length; columns++) {
				cell[rows][columns] = first[rows][columns];
			}
			for (int columns = 0; columns < second[rows].length; columns++) {
				cell[rows][columns + first[rows].length] =second[rows][columns];
			}
		}
		return cell;
	}

	private Cell[][] integerToCell(int[][] IntegerArray) {
		// method to return array of characters 
		Cell[][] cellOfCharacter = new Cell[ConfigValues.CHARACTER_HEIGHT]
				                           [ConfigValues.CHARACTER_WIDTH];

		for (int rows = 0; rows < (ConfigValues.CHARACTER_HEIGHT); rows++) {
			for (int columns = 0; columns < ConfigValues.CHARACTER_WIDTH; 
					columns++) {
				if (IntegerArray[rows][columns] == 1) {
					cellOfCharacter[rows][columns] = RED_UNIT_CELL;
				} else {
					cellOfCharacter[rows][columns] = WHITE_UNIT_CELL;
				}
			}
		}

		return cellOfCharacter;
	}

	private Cell[][] getSubArray(int count, Cell[][] paddedArray) {
		// method to return the sub array of the message array 
		Cell[][] windowArray = new Cell[ConfigValues.MARQUEE_HEIGHT]
				                        [ConfigValues.MARQUEE_WIDTH];
		for (int rows = 0; rows < (ConfigValues.MARQUEE_HEIGHT); rows++) {
			for (int columns = count, windowColumn = 0; columns < ConfigValues.
					MARQUEE_WIDTH + count; columns++, windowColumn++) {
				windowArray[rows][windowColumn] = paddedArray[rows][columns];
			}
		}
		return windowArray;
	}

	private Cell[][] GenerateBlankCell() {
		// method for creating a blank array column 
		Cell[][] cell = new Cell[ConfigValues.CHARACTER_HEIGHT]
				                [ConfigValues.EMPTY_COLUMNS_BETWEEN_CHARS];
		for (int rows = 0; rows < ConfigValues.CHARACTER_HEIGHT; rows++) {
			for (int columns = 0; columns < ConfigValues.
					EMPTY_COLUMNS_BETWEEN_CHARS; columns++) {
				cell[rows][columns] = WHITE_UNIT_CELL;
			}
		}
		return cell;
	}
}
