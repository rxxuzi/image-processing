package main

object Main {
   def main(args: Array[String]): Unit = {
      println("Hello World!")
      //date mm/dd/yyyy
      println("Date -> "+new java.util.Date)
      //java version
      println("Jdk version ->"+  System.getProperty("java.version"))
      //java vendor
      println("Jdk vendor ->"+ System.getProperty("java.vendor"))

      println("Jdk home ->"+ System.getProperty("java.home"))
      //java vendor url
      println("Jdk vendor url ->"+ System.getProperty("java.vendor.url"))
      //java class path
      println("Jdk class path ->"+ System.getProperty("java.class.path"))
      //user dir
      println("Jdk user dir ->"+ System.getProperty("user.dir"))
      //user name
      println("Jdk user name ->"+ System.getProperty("user.name"))
      //OS name
      println("Jdk os name ->"+ System.getProperty("os.name"))
      //os version
      println("Jdk os version ->"+ System.getProperty("os.version"))
      //os arch
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
