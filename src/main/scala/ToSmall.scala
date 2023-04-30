import java.io.File
import javax.imageio.ImageIO

object ToSmall {
   def main(args: Array[String]) {
      val file = new File("./rsc/Soda.png")
      val outFile = new File("./rsc/Soda_bin.png")
      //to small image 1/2
      val smallTo = 2
      val img = ImageIO.read(file)
      val width = img.getWidth()
      val height = img.getHeight()
      val newWidth = width/2
      val newHeight = height/2


   }
}
