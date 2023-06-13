package example

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 線形補間法
 */
object p1_Size_Click {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Gray.png")//読み込むファイル
      val outFile = "./output/"+ file.getName.split('.')(0) + "_Size_Click.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")

      val Ratio = 2 //比
      val width2 = width * Ratio
      val height2 = height * Ratio
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB)//出力用
      //横px 縦px 色情報
      val color = Array.ofDim[Int](width, height ,3)
      val g = Array.ofDim[Int](width2, height2)
      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            color(x)(y)(0) = (rgb >> 16) & 0xff //R
            color(x)(y)(1) = (rgb >> 8) & 0xff //G
            color(x)(y)(2) = rgb & 0xff //B
         }
      }
      for (y <- 0 until height2) {
         val j1 = y / Ratio
         val j2 = if(j1 + 1 > y - 1) height - 1 else j1 + 1 //選択領域のサイズ
         val q  = (y * Ratio - j1).toDouble

         for (x <- 0 until width2) {
            val i1 = x / Ratio
            val i2 = if(i1 + 1 > x - 1) width - 1 else i1 + 1 //選択領域のサイズ
            val p  = (x * Ratio - i1).toDouble

            //線形補間法
            val r = {
               (1 - p) * (1 - q) * color(i1)(j1)(0) + p +
               p * q   * color(i1)(j2)(0) +
               (1 - p) * q   * color(i2)(j1)(0) +
               p * (1 - q) * color(i2)(j2)(0)
            }.toInt

            out.setRGB(x, y, new Color(r,r,r).getRGB)
         }
      }

      //書き込み
      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
