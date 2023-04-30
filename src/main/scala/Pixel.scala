import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * Created by IntelliJ IDEA.
 * 低解像度化
 */
object Pixel {
   //間隔
   val d = 3
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Mostima.png")
      val outFile = "./rsc/Mostima_Pixel.png"
      val img = ImageIO.read(file)
      val width = img.getWidth()
      val height: Int = img.getHeight()
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()

      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      var keyX = 0
      var keyY = 0

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            val r = (rgb >> 16) & 0xff
            val g = (rgb >> 8) & 0xff
            val b = rgb & 0xff

            if(x % d == 0 && y % d == 0){
               keyX = x
               keyY = y
            }

            ca(x)(y)(0) = r
            ca(x)(y)(1) = g
            ca(x)(y)(2) = b

            if(y % d != 0 || x % d != 0){
               ca(x)(y)(0) = 0
               ca(x)(y)(1) = 0
               ca(x)(y)(2) = 0
//               ca(x)(y)(0) = ca(keyX)(keyY)(0)
//               ca(x)(y)(1) = ca(keyX)(keyY)(1)
//               ca(x)(y)(2) = ca(keyX)(keyY)(2)
            }
//            if (y % d != 0 && x % d != 0) {
//               ca(x)(y)(0) = 0
//               ca(x)(y)(1) = 0
//               ca(x)(y)(2) = 0
//               //               ca(x)(y)(0) = ca(keyX)(keyY)(0)
//               //               ca(x)(y)(1) = ca(keyX)(keyY)(1)
//               //               ca(x)(y)(2) = ca(keyX)(keyY)(2)
//            }


            out.setRGB(x, y, new Color(ca(x)(y)(0), ca(x)(y)(1), ca(x)(y)(2)).getRGB)

         }
      }


      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")
   }


}
