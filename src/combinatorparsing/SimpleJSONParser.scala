package combinatorparsing

import scala.util.parsing.combinator._

object Parser2 {
  def main(args: Array[String]): Unit = {
   val jsonString = """{ "address_book": { "name" : "John Smith", "address": { "street": "100 Some Street", "city": "London", "postcode": "SW1 8AJ"}, "phone_numbers": [ "123458", "0876321"]}}""";
   val simpleParser = new SimpleJSONParser();
   val enhancedParser = new EnhancedJSONParser();
   println("Simple Parser:")
   println(simpleParser.parseAll(simpleParser.obj, jsonString))
   println("Enhanced Parser:")
   println(enhancedParser.parseAll(enhancedParser.obj, jsonString))
   
  }
}	

/**
 * value   ::= obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false".
 * obj     ::= "{" [members] "}".
 * arr     ::= "[" [values] "]".
 * members ::= member {"," member }.
 * member  ::= stringLiteral ":" value.
 * values  ::= value {"," value }.
 */
class SimpleJSONParser extends JavaTokenParsers {
	def value: Parser[Any] = obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false"
	def obj: Parser[Any] = "{" ~ opt(members) ~ "}"
	def arr: Parser[Any] = "[" ~ opt(values) ~ "]"
	def members: Parser[Any] = member ~ rep("," ~ member)
	def member: Parser[Any] = stringLiteral ~ ":" ~ value
	def values: Parser[Any] = value ~ rep("," ~ value )
}

class EnhancedJSONParser extends JavaTokenParsers {
	def value: Parser[Any] = obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false"
	def obj  : Parser[Any] = "{" ~ repsep(member, ",") ~ "}"  // The repsep combinator parses a possibly empty sequence of terms separated by a given separator string.
	def arr  : Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
	def member : Parser[Any] = stringLiteral ~ ":" ~ value
}