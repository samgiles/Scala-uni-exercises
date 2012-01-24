package weekone

/**
 * Write Five Fibonacci sequence functions and output the display from each function in a table.
 */
object Exercise1 {
	def main(args: Array[String]) : Unit = {
	  
	  // If arguments contains test then run the tests.
	  if (args.size > 0 && args(0).indexOf("test") != -1) {
		println("Function A:");
	  	testFibonacciFunction(fibonacciNumberA);
	  	println("Function B:");
	  	testFibonacciFunction(fibonacciNumberB);
	  	println("Function C:");
	  	testFibonacciFunction(fibonacciNumberC);
	  	println("Function D:");
	  	testFibonacciFunction(fibonacciNumberD);
	  	println("Function E:");
	  	testFibonacciFunction(fibonacciNumberE);
	  }
	  
	  val funcs: Array[(Double) => Double] = new Array[(Double) => Double](5);
	  
	  funcs(0) = fibonacciNumberA;
	  funcs(1) = fibonacciNumberB;	  
	  funcs(2) = fibonacciNumberC;	  
	  funcs(3) = fibonacciNumberD;
	  funcs(4) = fibonacciNumberE;
	  
	  printFibonacciFunctionTable(funcs);
	  
	}
	
	/**
	 * First implementation of the Fibonacci function.
	 * 
	 * Not suitable for n > 35 due to time complexity (O(2^n)).
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
	 * Fourth implementation of the Fibonacci function.
	 * 
	 * Uses the simple recurrence relation as in function A.  However rather than using a recursive function call, we simply iterate until we reach the nth value.
	 * 
	 * @param n The nth number of the Fibonacci sequence.
	 */
	def fibonacciNumberD(n: Double) : Double = {
	   
	   var n1 = 1; 			// Seed values for n = 1
	   var n0 = 0; 			// n = 0
	   
	   var i = 0; 			// iteration counter
	   
	   
	   while (i < (n - 1)) { 		// Iterate until we get the nth value.
	     var nth = n0 + n1; // get the nth value by adding the two previous.
	     n0 = n1; 			// assign the n0 value the old n1 value.
	     n1 = nth; 			// assign the n1 value the new nth value.
	     i = i + 1; 		// increment the counter
	   }
	   
	   n1; // Remember Scala returns last statement.
	}
	
	
	/**
	 * A collection that contains fibonacci numbers that have already been computed.       Used in the Fifth Implementation below.
	 */
	val fibs = new java.util.Vector[Double]();
	
	/**
	 * Fifth implementation of the Fibonacci function.
	 * 
	 * Caches values that have already been computed in the fibs variable.  (See above).
	 * 
	 * This method saves on recalculating values we have already calculated.
	 * 
	 * @param n The nth number of the Fibonacci sequence.
	 */
	def fibonacciNumberE(n: Double) : Double = {
	  
	  if (fibs.size() == 0) {
	    fibs.add(0); fibs.add(1); // set up the initial collection.
	  }
	  
	  var i = fibs.size() - 1; // get the last element index.
	  
	  if (i > n){             // If the collection is greater than the n number we are looking for, simply return the value at the collection index.
	    return fibs.get(n.toInt);
	  }
	  
	  while (fibs.size() <= n) {
	    fibs.add((fibs.elementAt(i) + fibs.elementAt(i - 1)));
	    i = i + 1;
	  }
	  
	  fibs.get(n.toInt);
	}
	
	/**
	 * Outputs a table of numbers in a sequence given an array of functions that take the nth value as its only argument and returns a double.
	 * 
	 * @param funcs An array of sequence functions.
	 */
	def printFibonacciFunctionTable(funcs: Array[(Double) => Double]) : Unit = {
	  var i = 0;
	  
	  printf("n\t");
	  funcs.foreach(func => { printf("F%d\t", i) ; i = i + 1;});
	  
	  println();
	  
	  i = 0; // Reset i;
	  
	  while (i < 11) {
	    printf("%d\t", i);
	    funcs.foreach(func => { printf("%.0f\t", func(i)); });
	    println();
	    i = i + 1;
	  }
	}
	
	/**
	 * Outputs to stdout the results of a few simple tests against a fibonacci function.
	 * @param testFunction The fibonacci function, he function passed here should only take a Double as its only argument, and return a Double
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
	  
	  if (testFunction(18) == 2584) {
	    println("Test 7 Pass");
	  } else {
	    println("Test 7 Fail");
	  }
	  
	  // Function A will slow to a painful speed if we look for n values greater than 35 due to the O(2^n) implementation.
	  if (testFunction(35) == 9227465) {
	    println("Test 8 Pass");
	  } else {
	    println("Test 8 Fail");
	  }
	}
}