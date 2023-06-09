package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.math.random

/*
 * 画像にノイズを加える
 */
object Noise {
   //ノイズ率
   private [this] val NoiseRate = 10

   def main(args: Array[String]): Unit = {
      println("Noise Rate " + NoiseRate)
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Noise_Rate-" + NoiseRate + "%.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rate = random() * 100
            if (rate < NoiseRate) {
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
