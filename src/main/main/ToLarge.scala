package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
/*
 * 画像を大きく
 *
 */
object ToLarge {
   //拡大率
   private val dx : Int = 4
   private val dy : Int = 5

   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Large.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")

      //小さくしたときのサイズ
      val newWidth = width * dx
      val newHeight = height *  dy

      val startTime = System.currentTimeMillis() //タイマースタート
      val newImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB)//書き込み用

      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      for (y <- 0 until height ) {
         for (x <- 0 until width ) {
            val rgb = img.getRGB(x, y)
            ca(x)(y)(0) = (rgb >> 16) & 0xff //R
            ca(x)(y)(1) = (rgb >> 8) & 0xff //G
            ca(x)(y)(2) = rgb & 0xff //B
         }
      }
      for (y <- 0 until newHeight) {
         for (x <- 0 until newWidth) {
            val px = x / dx
            val py = y / dy
            newImg.setRGB( x, y, new Color(ca(px)(py)(0), ca(px)(py)(1), ca(px)(py)(2)).getRGB)
         }
      }


      //書き込み
      ImageIO.write(newImg, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
