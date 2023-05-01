package tutorial

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Hough {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/pics/Noise.png")
      val path = "./rsc/pics/Hough.png"
      val img = ImageIO.read(file)
      val width = img.getWidth
      val height = img.getHeight
      val diagonal =  Math.sqrt(width * width + height * height)
      val newImge = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB)

      //array list
      var list = List[Int]()

      for (x <- 0 until width) {
         for (y <- 0 until height) {
            val pixel = img.getRGB(x, y)
            val z = pixel & 0xff //make it 8 bits
            print(z + ",")
         }
         println()
      }
   }

}
