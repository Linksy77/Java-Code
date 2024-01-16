package HW1;

public class BinaryNumber {
	/**
	 * int to store length of binary number.
	 */
	private int length;
	/**
	 * int[] to store individual bits of the binary number.
	 */
	private int data[];
	
	/**
	 * Constructor that takes parameter length and constructs a BinaryNumber object of that length consisting of only 0s.
	 * @param length	The length of the binary number.
	 */
	public BinaryNumber(int length) {
		
		this.length = length;
		data = new int[length];
		
		for(int i = 0; i < length; i++) {
			data[i] = 0;
		}
	}
	
	/**
	 * Constructor that takes parameter str and constructs a BinaryNumber object consisting of that assortment of 1s and 0s.
	 * @param str	The String representation of the binary number.
	 */
	public BinaryNumber(String str) {
		
		length = str.length();
		data = new int[length];
		char currNum;
		int intVers;
		
		for(int i = 0; i < length; i++) {
			if(str.charAt(i) != '1' && str.charAt(i) != '0') {
				throw new IllegalArgumentException("Invalid input: only 1s and 0s should be present");
			} else {
				currNum = str.charAt(i);
				intVers = ((Character) currNum).getNumericValue(currNum);
				data[i] = intVers;
			}
		}
	}
	
	/**
	 * Returns the length of a BinaryNumber object.
	 * @return
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Returns the data array consisting of each individual digit of the binary number. (Reference only)
	 * @return
	 */
	public int[] getInnerArray() {
		return data;
	}
	
	/**
	 * Returns the integer at index 'index' of the BinaryNumber object.
	 * @param index	The index of the digit you would like to retrieve from the Binary Number object.
	 * @return
	 */
	public int getDigit(int index) {
		if(index < length && index >= 0) {
			return data[index];
		} else {
			throw new ArrayIndexOutOfBoundsException("Index input is out of bounds: " + index);
		}
	}
	
	/**
	 * Converts the binary value stored in the Binary Number object to its respective decimal value/notation.
	 * @return
	 */
	public int toDecimal() {
		int decAnswer = 0;
		for(int i = length; i > 0; i--) {
			decAnswer += data[i] * ((int) Math.pow(2, i));
		}
		return decAnswer;
	}
	
	/**
	 * Shifts the binary number stored in the Binary Number object either left or right by a specific amount.
	 * @param direction Either -1 (left) or 1 (right): indicates the direction in which you would like to shift the binary number.
	 * @param amount Must be a positive integer: indicates the amount by which you would like to shift the binary number.
	 */
	public void bitShift(int direction, int amount) {
		int[] shiftedNums;
		if((direction == -1) && (amount > -1)) {
			shiftedNums = new int[(data.length + amount)];
			for(int i = 0; i < (data.length + amount); i++) {
				if(i < data.length) {
					shiftedNums[i] = data[i];
				} else {
					shiftedNums[i] = 0;
				}
			}
		} else if((direction == 1) && (amount > -1)) {
			shiftedNums = new int[(data.length - amount)];
			for(int i = 0; i < (data.length - amount); i++) {
				shiftedNums[i] = data[i];
			}
		} else if ((direction != 1) && (direction != -1)) {
			throw new IllegalArgumentException("Invalid direction input: " + direction);
		} else {
			throw new IllegalArgumentException("Invalid amount input: " + amount + ". Input must be nonnegative.");
		}
	}
	
	/**
	 * Computes the bitwise or result of two BinaryNumber objects.
	 * @param bn1 Representative of the first binary number.
	 * @param bn2 Representative of the second binary number.
	 * @return
	 */
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		int[] bworArr;
		if(bn1.getLength() != bn2.getLength()) {
			throw new IllegalArgumentException("Invalid inputs; binary numbers must be of same length");
		} else {
			bworArr = new int[bn1.getLength()];
			for(int i = 0; i < bn1.getLength(); i++) {
				if((bn1.getInnerArray()[i] == 1) || (bn2.getInnerArray()[i] == 1)) {
					bworArr[i] = 1;
				} else {
					bworArr[i] = 0;
				}
			}
		}
		return bworArr;
	}
	
	/**
	 * Computes the bitwise and result of two BinaryNumber objects.
	 * @param bn1 Representative of the first binary number.
	 * @param bn2 Representative of the second binary number.
	 * @return
	 */
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		int[] bwandArr;
		if(bn1.getLength() != bn2.getLength()) {
			throw new IllegalArgumentException("Invalid inputs; binary numbers must be of same length");
		} else {
			bwandArr = new int[bn1.getLength()];
			for(int i = 0; i < bn1.getLength(); i++) {
				if((bn1.getInnerArray()[i] == 1) && (bn2.getInnerArray()[i] == 1)) {
					bwandArr[i] = 1;
				} else {
					bwandArr[i] = 0;
				}
			}
		}
		return bwandArr;
	}
	
	/**
	 * Returns the String representation of the binary number stored in the BinaryNumber object.
	 */
	public String toString() {
		String numString = "";
		for(int i = 0; i < length; i++) {
			numString += String.valueOf(data[i]);
		}
		return numString;
	}
	
	/**
	 * Adds the values stored by the BinaryNumber object the method is called on and the BinaryNumber object it receives as a parameter.
	 * @param aBinaryNumber The binary number you would like to add to that of the BinaryNumber object you are calling the method on.
	 */
	public void add(BinaryNumber aBinaryNumber) {
		if(this.length < aBinaryNumber.getLength()) { // Checking if calling object is shorter in length than parameter aBinaryNumber
			this.prepend(aBinaryNumber.getLength() - this.length); // Use of prepend on calling object to increase its length to match that of aBinaryNumber.
		} else if(aBinaryNumber.getLength() < this.length) { // Checking if aBinaryNumber is shorter in length than calling object
			aBinaryNumber.prepend(aBinaryNumber.getLength()); // Use of prepend on parameter aBinaryNumber to increase its length to match that of the calling object.
		} else { // A.k.a. if neither number is greater than the other/both numbers are of the same length...
			int carryNum = 0; // int to store the value being carried during addition
			int digitSum = 0; // int to store the sum of values
			for(int i = this.length - 1; i >= 0; i--) { // Traversing calling object and aBinaryNumber from back to front
				digitSum += carryNum + this.data[i] + aBinaryNumber.getDigit(i); // Adding all values from index position i and assigning the sum to digitSum
				if(digitSum >= 2) { // Checking if digitSum >= 2, as this would mean that there is a value to be carried in the addition
					carryNum = 1; // carrySum is incremented by 1 due to the fact that the sum of digits was greater than 1 or 0
					digitSum -= 2; // digitSum decreased by 2, as the '2' value has now been carried over to the following index position
				} else {
					this.data[i] = digitSum; // If digitSum < 2, then value of 0 or 1 is the value of the sum of digits, and it is therefore the new value at index i of the data
					digitSum = 0; // Changing digitSum back to 0 before continuing with next iteration of the loop
				}
			}
			if(carryNum > 0) { // After for loop is done traversing both BinaryNumber objects, if carryNum > 0, there is another position to be added
				this.prepend(carryNum); // Extra 1 position added
				data[0] = carryNum; // Value at new position = carryNum
			}
		}
	}
	
	/**
	 * Adds 0s to the front of a BinaryNumber object.
	 * @param amount The number of 0s to be added to the front of the BinaryNumber object.
	 */
	public void prepend(int amount) {
		int[] moddedData = new int[data.length + amount];
		for(int i = 0; i < amount; i++) {
			moddedData[i] = 0;
		}
		for(int i = amount; i < moddedData.length; i++) {
			moddedData[i] = data[i - amount];
		}
	}
	
}
