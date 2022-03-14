package br.com.erudio.math;

import br.com.erudio.exception.ResourceNotFoundException;

public class SimpleMath {

	private static String function;

	public static void setFunction(String function) {
		SimpleMath.function = function;
	}

	public static Double result(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double numToReturn = 0D;
		switch (function) {
			case "sum":
				numToReturn = convertToDouble(numberOne) + convertToDouble(numberTwo);
				return numToReturn;
			case "subtraction":
				numToReturn = convertToDouble(numberOne) - convertToDouble(numberTwo);
				return numToReturn;
			case "multiplication":
				numToReturn = convertToDouble(numberOne) * convertToDouble(numberTwo);
				return numToReturn;
			case "division":
				if (isZero(numberTwo)) {
					throw new ResourceNotFoundException("Please, insert a valid number!");
				}
				numToReturn = convertToDouble(numberOne) * convertToDouble(numberTwo);
				return numToReturn;
			case "average":
				numToReturn = (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
				return numToReturn;
			case "squareRoot":
				numToReturn = Math.sqrt(convertToDouble(numberOne));
				return numToReturn;
		}
		throw new ResourceNotFoundException("Please, insert a valid function!");
	}
		
	private static Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private static boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	private static boolean isZero(String numberTwo) {
		if (convertToDouble(numberTwo) == 0D) return true;
		return false;
	}
}
