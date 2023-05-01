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

      val center = (width/2, height/2)
      val dr = 2
      val diagonal = Math.sqrt( width*width + height*height )
      for (y <- 0 until height) {
         for (x <- 0 until width) {
            for(r <- 0 until diagonal.toInt /2 by dr*2){
               //波紋を生成
               if(Math.sqrt( (x-center._1)*(x-center._1) + (y-center._2)*(y-center._2) ) < r && Math.sqrt( (x-center._1)*(x-center._1) + (y-center._2)*(y-center._2) ) >= r-dr){
                  img.setRGB(x, y, Color.BLACK.getRGB)
               }
            }
         }
      }

      val circleCount = diagonal.toInt / dr / 2
      println("Number of circles : " + circleCount)

      //書き込み
      ImageIO.write(img, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
