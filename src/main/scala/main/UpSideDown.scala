package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 画像の上下逆転
 */
object UpSideDown {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_UpSideDown.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      val newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            ca(x)(y)(0) = (rgb >> 16) & 0xff //R
            ca(x)(y)(1) = (rgb >> 8) & 0xff //G
            ca(x)(y)(2) = rgb & 0xff //B

            //色情報を座標ごとにセット

         }
      }

      for(x <- 0 until width; y <- 0 until height){
         newImg.setRGB(width - x - 1, height - y - 1, new Color(ca(x)(y)(0), ca(x)(y)(1), ca(x)(y)(2)).getRGB)
      }


      //書き込み
      ImageIO.write(newImg, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
