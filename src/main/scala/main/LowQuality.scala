package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
/*
 * Created by IntelliJ IDEA.
 * 低解像度化
 */
object LowQuality {
   //間隔
   val d = 3
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_LowQuality_D-" + d + ".png"

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
         if (y % d == 0) keyY = y
         for (x <- 0 until width) {
            if(x % d == 0) keyX = x

            val rgb = img.getRGB(x, y)

            ca(x)(y)(0) = (rgb >> 16) & 0xff
            ca(x)(y)(1) = (rgb >> 8) & 0xff
            ca(x)(y)(2) = rgb & 0xff

            if(y % d != 0 || x % d != 0){
               ca(x)(y)(0) = ca(keyX)(keyY)(0)
               ca(x)(y)(1) = ca(keyX)(keyY)(1)
               ca(x)(y)(2) = ca(keyX)(keyY)(2)
            }

            out.setRGB(x, y, new Color(ca(x)(y)(0), ca(x)(y)(1), ca(x)(y)(2)).getRGB)
         }
      }


      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")
   }


}
