package main

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
object GrayScale {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Gray.png"

      val img = ImageIO.read(file)
      val width = img.getWidth()
      val height = img.getHeight()
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      for (x <- 0 until width) {
         for (y <- 0 until height) {
            val rgb = img.getRGB(x, y)
            val r = (rgb >> 16) & 0xff //赤の要素を抜き出す
            val g = (rgb >> 8) & 0xff  //緑の要素を抜き出す
            val b = rgb & 0xff         //青の要素を抜き出す
            val gray = (r + g + b) / 3 //三色の平均値
            val grayRgb = (gray << 16) | (gray << 8) | gray
            out.setRGB(x, y, grayRgb)
         }
      }
      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }
}
