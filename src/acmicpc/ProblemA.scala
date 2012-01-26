package acmicpc

object ProblemA {
	def main(args: Array[String]) : Unit = {
			testFunctions();
	}
  
  /**
   * Runs an a-C-m program.
   */
  def process(a: Int, m: Int, in: Int, program: String): Int = {
    var input = in;
    
    program.foreach(c => {
      
      if (c == 'A'){
        input = input + a;
      } else if (c == 'M') {
        input = input * m;
      }
    });
    
    input;
  }
  
  
  def testFunctions(): Unit = {
    if (process(1, 2, 2, "AAAM") == 10) {
      println("Test 1 Pass");
    } else {
      println("Test 1 Fail");
    }
    
    if (process(5, 3, 2, "AAAM") == 51) {
      println("Test 2 Pass");
    } else {
      println("Test 2 Fail");      
    }
  }
}