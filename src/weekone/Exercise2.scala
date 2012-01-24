package weekone

/**
 * Exercise 2:
 * Write a program to implement the following specification and use it to create a record of and a printout of your Semester 2 timetable.
 * 
 * Specification:
 * 
 * 	A semester consists of 4 modules. Each module has two sessions. A session can be a lecture, a tutorial or a
 *	workshop, has a start time (consisting of hours and minutes), a duration (a positive integer), a room (consisting of a
 *	building code and a room number), a module code (consisting	of a level, course code and module number) and a lecturer who
 *	has a name.
 * 
 */
object Exercise2 {
  def main(args: Array[String]) : Unit = {

  }
  
  class Semester (modules: Array[Module]) {
    
  }
  
  class Module (moduleCode: ModuleCode, sessions: Array[Session]) {
    
  }
  
  class Session (startTime: Time, duration: Time, roomNumber: Room ) {
    
  }
  
  class Time (hour : Int, minutes: Int) {
    
    /**
     * Get the duration of the time in minutes.
     */
    def duration(): Int = {
      (hour * 60) + minutes;
    }
    
    /**
     * Get the hours.
     */
    def getHour(): Int = hour;
    
    /**
     * Get the minutes.
     */
    def getMinute(): Int = minutes
    
    /**
     * Override the toString method to return the Time in 24 hour format.
     */
    override def toString(): String = "%02d:%02d".format(getHour(), getMinute());
  }
  
  class Room (buildingCode: String, roomNumber: Int) {
    /**
     * Override the toString method to return the Room number as a single value.
     */
    override def toString(): String = "%s%d".format(buildingCode, roomNumber);
  }
  
  class ModuleCode (level: Int, courseCode: String, moduleNumber: Int, lecturer: String) {
    
  }
  
}