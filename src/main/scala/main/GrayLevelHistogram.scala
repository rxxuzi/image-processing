package main

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object GrayLevelHistogram {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/pics/Gray.png")

      val img = ImageIO.read(file)
      val width = img.getWidth()
      val height = img.getHeight()
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()

      val grayLevel = Array.ofDim[Int](width,height)

      for (x <- 0 until width) {
         for (y <- 0 until height) {
            val rgb = img.getRGB(x, y)
            val r = (rgb >> 16) & 0xff //赤の要素を抜き出す
            val g = (rgb >> 8) & 0xff //緑の要素を抜き出す
            val b = rgb & 0xff //青の要素を抜き出す
            val gray = (r + g + b) / 3 //三色の平均値

            grayLevel(x)(y) = gray
         }
      }

      for(i<- 0 until 256){
         print(i+"| ")
         for(x <- 0 until width){
            for(y <- 0 until height){
               if(grayLevel(x)(y) == i){
                  print("*")
               }
            }
         }
         println()
      }
      println("-+------------------------------------------------------px")
      val endTime = System.currentTimeMillis()

      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }

}
