package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 画像の新規作成
 */
object Circle {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Circle.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()

      val centerX = width/2
      val centerY = height/2
      val radius = width/2
      val dr = 10
      for (y <- 0 until height) {
         for (x <- 0 until width) {
            if(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2) <= Math.pow(radius, 2)) {
               img.setRGB(x, y, new Color(0,0,0).getRGB)
            }
         }
      }

      //書き込み
      ImageIO.write(img, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
