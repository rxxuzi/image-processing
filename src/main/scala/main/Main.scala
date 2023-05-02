package main

object Main {
   def main(args: Array[String]): Unit = {
      println("Hello World!")
      //println date mm/dd/yyyy
      println("Date -> "+new java.util.Date)
      //println java version
      println("Jdk version ->"+  System.getProperty("java.version"))
      //println java vendor
      println("Jdk vendor ->"+ System.getProperty("java.vendor"))
      println("Jdk home ->"+ System.getProperty("java.home"))
      println("Jdk vendor url ->"+ System.getProperty("java.vendor.url"))
      println("Jdk class path ->"+ System.getProperty("java.class.path"))
      println("Jdk user dir ->"+ System.getProperty("user.dir"))
      println("Jdk user name ->"+ System.getProperty("user.name"))
      println("Jdk os name ->"+ System.getProperty("os.name"))
      println("Jdk os version ->"+ System.getProperty("os.version"))
      println("Jdk os arch ->"+ System.getProperty("os.arch"))

      //println scala version
      println("Version ->" + util.Properties.versionString)
      //println scala home
      println("Scala Home ->" + util.Properties.scalaHome)
      //println java home
      println("Java Home ->" + util.Properties.javaHome)
      //println os name
      println("OS Name ->" + util.Properties.osName)
      //println is win
      println("Is Win ->" + util.Properties.isWin)
      //copyright
      println("Copyright -> " +util.Properties.copyrightString)

      val e = System.identityHashCode()
      println("HashCode -> " + e)

      println("Pid -> " + System.getProperty("pid"))
   }
}
