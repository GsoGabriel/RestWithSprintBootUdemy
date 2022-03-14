package br.com.erudio.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {
	
	private static final String template = "Hello, %s!";
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "calc/{function}/{numberOne}/{numberTwo}")
	public Double calculator(@PathVariable("function") String func,
								@PathVariable("numberOne") String numberOne,
								@PathVariable("numberTwo") String numberTwo) {
		SimpleMath.setFunction(func);
		return SimpleMath.result(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
		return sum;
	}
	
	@RequestMapping(value = "sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double sub = convertToDouble(numberOne) - convertToDouble(numberTwo);
		return sub;
	}
	
	@RequestMapping(value = "mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception  {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double mult = convertToDouble(numberOne) * convertToDouble(numberTwo);
		return mult;
	}
	
	@RequestMapping(value = "div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo) || isZero(numberTwo)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double div = convertToDouble(numberOne) / convertToDouble(numberTwo);
		return div;
	}
	

	@RequestMapping(value = "avr/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double avr(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double avr = (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
		return avr;
	}
	
	@RequestMapping(value = "sqrt/{numberOne}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable("numberOne") String numberOne) throws Exception {
		if (!isNumeric(numberOne)) {
			throw new ResourceNotFoundException("Please, insert a valid number!");
		}
		Double sqrt = Math.sqrt(convertToDouble(numberOne));
		return sqrt;
	}

	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	private boolean isZero(String numberTwo) {
		if (convertToDouble(numberTwo) == 0D) return true;
		return false;
	}
}
