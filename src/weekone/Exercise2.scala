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
    
    
    var modules = new Array[Module](1);
   
    var sessions = new Array[Session](2);
    sessions(0) = new Session(new Time(9,0), new Time(1,0), new Room("MI", 34), "Dr A. Lecturer", SessionType.Lecture);
    sessions(1) = new Session(new Time(10,0), new Time(1,0), new Room("MI", 34), "Dr A. Lecturer", SessionType.Workshop);
    
    modules(0) = new Module("Software Engineering Practices", new ModuleCode(5, "CS", 6), sessions);
    
    var semester = new Semester(modules);
    
    print(semester);
  }
  
  /**
   * Represents a Semester.
   */
  class Semester (modules: Array[Module]) {
    override def toString(): String = {
      var string = "";
      modules.foreach(m => string = string + m + "\n");
      string;
    }
  }
  
  /**
   * Represents a module.
   */
  class Module (moduleName: String, moduleCode: ModuleCode, sessions: Array[Session]) {
    override def toString(): String = {
      val sessionString = {
        var string = "";
        sessions.foreach(s => string = string + s + "\n");
        string;
      }: String;
      
      return "%s\t%s\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n%s".format(moduleName, moduleCode, sessionString);
    }
  }
  
  /**
   * Represents a type of the Module Session.
   */
  object SessionType extends Enumeration {
    type SessionType = Value;
    val Lecture, Tutorial, Workshop = Value;
  }
  
  /**
   * Represents a Module session.
   */
  class Session (startTime: Time, duration: Time, roomNumber: Room, lecturer: String, sessionType: SessionType.Value) {
    
    def getSessionType() = sessionType;
    
    /**
     * Get the start time of the session.
     */
    def getStartTime(): Time = startTime;
    
    /**
     * Get the end time of the modules session.
     */
    def getEndTime(): Time = startTime + duration;
    
    /**
     * Returns the String representation of a session.
     */
    override def toString(): String = "%s\t%s\t%s\t%s\t%s".format(getStartTime, getEndTime, roomNumber, lecturer, getSessionType); 
  }
  
  /**
   * Represents a Time, simply has a number of hours and a number of minutes.
   */
  class Time (hour : Int, minutes: Int) {
    
    /**
     * Get the duration of the time in minutes.
     */
    def duration(): Int = (hour * 60) + minutes;
    
    /**
     * Get the hours.
     */
    def getHour(): Int = hour;
    
    /**
     * Adds a number of Minutes to the time.
     */
    private def addMinutes(mins: Int): Time = {
      val hours = mins / 60;
      
      val newHours = hour + hours;
      
      val remainderMinutes = mins - (hours * 60);
      
      val newMinutes = minutes + remainderMinutes;
      
      if (newMinutes > 60) {
        return addMinutes(newMinutes)
      } else {
        if (newMinutes == 60) {
          return new Time(newHours + 1, 0);
        } else {
          return new Time(newHours, newMinutes);
        }
      }
    }
    
    /**
     * Get the minutes.
     */
    def getMinute(): Int = minutes
    
    /**
     * Adds two time values together.
     */
    def +(operand: Time): Time = {
      return addMinutes((operand.getHour * 60) + operand.getMinute);
    }
    
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
  
  class ModuleCode (level: Int, courseCode: String, moduleNumber: Int) {
    /**
     * Overrides the toString method to return the ModuleCode in the correct format.
     */
    override def toString(): String = "%d%s%03d".format(level, courseCode, moduleNumber);
  }
  
}