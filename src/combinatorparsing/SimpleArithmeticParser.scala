package combinatorparsing

import scala.util.parsing.combinator._

object Parser1 {
  def main(args: Array[String]): Unit = {
    val parser = new SimpleArithmeticParser();
    val expression = "2 * (3 + 7)"
    println("Parsing: " + expression);
    
    println(parser.parseAll(parser.expr, expression));
  }
}

/**
 * A simple Context free grammar for simple arithmetic expressions consisting of + - * / operators, parentheses and floating point numbers
 * 
 * Example from the "Artima" book "Programming in Scala" by Odersky, Spoon and Venners
 * 
 * expr ::= term ("+" term | "-" term}.
 * term ::= factor {"*" factor | "/" factor}.
 * factor ::= floatingPointNumber | "(" expr ")".
 */
class SimpleArithmeticParser extends JavaTokenParsers {
	def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term) // The ~ operator indicates sequential composition, therefore two consecutive symbols require the ~ operator to implicitly composite them
	def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor) // The rep operator is analogous to { ... } in the grammar, i.e. repetition. as opt operator is analogous to [...] optional.
	def factor: Parser[Any] = floatingPointNumber | "(" ~ expr ~ ")"
}