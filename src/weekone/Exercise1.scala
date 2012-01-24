package weekone
/**
 * Write Five Fibonacci sequence functions and output the display from each function in a table.
 */
object Exercise1 {
	def main(args: Array[String]) : Unit = {
	  testFibonacciFunction(fibonacciNumberA);
	  
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
	    println("Test 5 pass");
	  } else {
	    println("Test 5 fail");
	  }
	}
}