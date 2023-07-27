package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * Created by IntelliJ IDEA.
 * 画像の二値化
 * 閾値を取り、グレースケール化したときの色の平均値が
 * 閾値以上なら白、以下なら黒をセットする。
 */
object Binarization {
   private val threshold = 100
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")
      val outFile = "./rsc/" + file.getName.split('.')(0) + "_Binary-"+threshold+".png"
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "," + height)
      val startTime = System.currentTimeMillis()
//      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            val r = (rgb >> 16) & 0xff //R
            val g = (rgb >> 8) & 0xff //G
            val b = rgb & 0xff //B

            val gray = (r + g + b) / 3 //平均値を取る

            val cb = if (gray <= threshold) 0  else 255

            //色情報を座標ごとにセット
            img.setRGB(x, y, new Color(cb,cb,cb).getRGB)

         }
      }

      //書き込み
      ImageIO.write(img, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")
   }


}
