package weekone
/**
 * Write Five Fibonacci sequence functions and output the display from each function in a table.
 */
object Exercise1 {
	def main(args: Array[String]) : Unit = {
	  println("Function A:");
	  testFibonacciFunction(fibonacciNumberA);
	  println("Function B:");
	  testFibonacciFunction(fibonacciNumberB);
	  println("Function C:");
	  testFibonacciFunction(fibonacciNumberC);
	}
	
	/**
	 * First implementation of the Fibonacci function.
	 * 
	 * Not suitable for n > 35 due to time complexity.
	 * 
	 * Takes the recurrence relation:
	 * 
	 * Fn = F(n-1) + F(n-2)
	 * 
	 * with seed values
	 * 
	 * F0 = 0
	 * F1 = 1
	 * 
	 * @param n The nth number of the fibonacci sequence to find, not suited for n > 35
	 */
	def fibonacciNumberA(n: Double) : Double = {
	  if (n == 0) {
	    return 0;
	  }
	  
	  if (n == 1) {
	    return 1;
	  }
	  
	  (fibonacciNumberA(n-1) + fibonacciNumberA(n-2)); // Last statement in a function is automatically returned in scala.
	}
	
	/**
	 * Second implementation of the Fibonacci function.
	 * 
	 * Uses Binets formula for fibonacci numbers.
	 * 
	 * Where:
	 * 
	 * Un = ((1 + sqrt(5))^n - (1 + sqrt(5))^n) / (2^n * sqrt(5))
	 * 
	 * @param n the nth number of the fibonacci sequence to find.
	 */
	def fibonacciNumberB(n: Double) : Double = {
	  val root5 = scala.math.sqrt(5);
	  val oneAddRoot5 = 1 + root5;
	  val oneMinusRoot5 = 1 - root5
	  
	  var top = (scala.math.pow(oneAddRoot5, n) - scala.math.pow(oneMinusRoot5, n)); // The top of the formula. i.e. the top portion of the division or Numerator.
	  var bottom = (scala.math.pow(2, n) * root5); // The lower portion of the division. or Denominator
	  
	  scala.math.floor(top / bottom); // return the floored result of the formula to correct error.
	}

	/**
	 * Third implementation of the Fibonacci function.
	 * 
	 * Uses Computation By rounding where:
	 * 
	 * Un = |_(goldenRatio^n / sqrt(5)) + (1 / 2)_|  (Floor)
	 * 
	 * @param n The nth number of the Fibonacci sequence.
	 */
	def fibonacciNumberC(n: Double) : Double = {
	  
	  val goldenRatio = (1 + scala.math.sqrt(5)) / 2;
	  val root5 = scala.math.sqrt(5);
	  
	  // Golden Ratio to the power of n
	  var gRPowN = scala.math.pow(goldenRatio, n);
	  
	  return scala.math.floor((gRPowN / root5) + 0.5);
	}
	
	/**
	 * Outputs to stdout the results of a few simple tests against a fibonacci function.
	 * @param testFunction The fibonacci function, takes a Double as its only argument, and returns a Double
	 */
	def testFibonacciFunction(testFunction : (Double) => Double) : Unit = {
	  if (testFunction(1) == 1) {
	    println("Test 1 Pass");
	  } else {
		  println("Test 1 Fail");
	  }
	  
	  if (testFunction(2) == 1) {
	    println("Test 2 Pass");
	  } else {
	    println("Test 2 Fail");
	  }
	  
	  if (testFunction(3) == 2) {
	    println("Test 3 Pass");
	  } else {
	    println("Test 3 fail");
	  }
	  
	  if (testFunction(4) == 3) {
	    println("Test 4 Pass");
	  } else {
	    println("Test 4 fail");
	  }
	  
	  if (testFunction(7) == 13) {
	    println("Test 5 Pass");
	  } else {
	    println("Test 5 fail");
	  }
	  
	  if (testFunction(26) == 121393) {
	    println("Test 6 Pass"); 
	  } else {
	    println("Test 6 Fail");
	  }
	}
}