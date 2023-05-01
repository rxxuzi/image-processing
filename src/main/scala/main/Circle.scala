package main

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO


/*
 * 画像の新規作成
 */
object Circle {
   private final val dr = 5 //描画半径
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル

      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Circle_dr-" + dr + ".png" //出力パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得

      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px") //画像の大きさを表示

      val startTime = System.currentTimeMillis()
      val center = (width/2, height/2) //center X , center Y
      val diagonal = Math.sqrt( width*width + height*height ) //対角線の長さを計算
      val circleCount = diagonal.toInt / dr / 2
      println("Number of circles : " + circleCount)

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            for(r <- 0 until diagonal.toInt /2 by dr*2 ){
               //波紋を生成
               if(Math.sqrt( (x-center._1)*(x-center._1) + (y-center._2)*(y-center._2) ) < r && Math.sqrt( (x-center._1)*(x-center._1) + (y-center._2)*(y-center._2) ) >= r-dr){
                  img.setRGB(x, y, Color.BLACK.getRGB)
               }
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
