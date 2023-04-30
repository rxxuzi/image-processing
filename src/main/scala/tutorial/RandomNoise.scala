package tutorial

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.math.random

object RandomNoise {
   def main(args: Array[String]): Unit = {
      val path = "./rsc/pics/Noise.png"
      val noise_rate = 50
      val width  = 25
      val height = 25
      val img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)
      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height, 3)

      makeNoise()

      ImageIO.write(img, "png", new File(path))



      def makeNoise(): Unit = {
         for (y <- 0 until height) {
            for (x <- 0 until width) {
               val rate = math.random() * 100
               if (rate < noise_rate) {
                  val z = 0
                  img.setRGB(x, y, z)
               }else{
                  val z = 255
                  img.setRGB(x, y, new Color(z, z, z).getRGB)
               }
            }
         }
      }

   }

}
