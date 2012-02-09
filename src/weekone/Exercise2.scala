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
    
    
    var modules = new Array[Module](4);
   
    val sepSessions = new Array[Session](2);
    sepSessions(0) = new Session(new Time(9,0), new Time(1,0), new Room("MI", 34), "Dr A. Lecturer", SessionType.Lecture, Day.Tuesday);
    sepSessions(1) = new Session(new Time(10,0), new Time(1,0), new Room("MI", 34), "Dr A. Lecturer", SessionType.Workshop, Day.Tuesday);
    
    val collabSessions = new Array[Session](1);
    collabSessions(0) = new Session(new Time(13, 0), new Time(2, 0), new Room("MI", 34), "Dr A. Lecturer", SessionType.Workshop, Day.Monday);
    
    
    val concurrentDistSessions = new Array[Session](2);
    concurrentDistSessions(0) = new Session(new Time(17, 0), new Time(1, 0), new Room("MC", 214), "Dr A. Lecturer", SessionType.Lecture, Day.Monday);
    concurrentDistSessions(1) = new Session(new Time(18, 0), new Time(1, 0), new Room("MI", 34), "Dr A. Lecturer", SessionType.Workshop, Day.Monday);
    
    val databaseSystems = new Array[Session](2);
    databaseSystems(0) = new Session(new Time(18, 0), new Time(1, 0), new Room("MI", 34), "Dr A.Lecturer", SessionType.Workshop, Day.Wednesday);
    databaseSystems(1) = new Session(new Time(19, 0), new Time(2, 0), new Room("MC", 1), "Dr A. Lecturer", SessionType.Lecture, Day.Wednesday);
    
    
    modules(0) = new Module("Software Engineering Practices", new ModuleCode(5, "CS", 6), sepSessions);
    modules(1) = new Module("Collaborative Development", new ModuleCode(5, "CS", 12), collabSessions);
    modules(2) = new Module("Database Systems", new ModuleCode(5, "CI", 17), databaseSystems);
    modules(3) = new Module("Distributed and Concurrent Systems", new ModuleCode(5, "CS", 4), concurrentDistSessions);
    
    val semester = new Semester(modules);
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
      
      return "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n %s\t%s\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n%s".format(moduleName, moduleCode, sessionString);
    }
  }
  
  /**
   * Represents a type of the Module Session.
   */
  object SessionType extends Enumeration {
    type SessionType = Value;
    val Lecture, Tutorial, Workshop = Value;
  }
  
  object Day extends Enumeration {
    type Day = Value;
    val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value;
  }
  
  /**
   * Represents a Module session.
   */
  class Session (startTime: Time, duration: Time, roomNumber: Room, lecturer: String, sessionType: SessionType.Value, day : Day.Value) {
    
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
    override def toString(): String = "%s     \t%s\t%s\t%s\t%s\t%s".format(day, getStartTime, getEndTime, roomNumber, lecturer, getSessionType); 
  }
  
  /**
   * Represents a Time, simply has a number of hours and a number of minutes.
   */
  class Time (hour : Int, minutes: Int) {
    
    /**
     * Get the duration of the time in minutes.
     */
    val duration: Int = (hour * 60) + minutes;
    
    /**
     * Get the hours.
     */
    val getHour: Int = hour;
    
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
    val getMinute: Int = minutes
    
    /**
     * Adds two time values together.
     */
    def +(operand: Time): Time = {
      return addMinutes((operand.getHour * 60) + operand.getMinute);
    }
    
    /**
     * Override the toString method to return the Time in 24 hour format.
     */
    override def toString(): String = "%02d:%02d".format(getHour, getMinute);
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