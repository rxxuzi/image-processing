package example

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 画像の新規作成
 */
object p1_Inverse_Mono_Click {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./output/"+ file.getName.split('.')(0) + "_Inverse_MONO.png" //出力用パス
      val img = ImageIO.read(file)//imageIOで画像を取得
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            ca(x)(y)(0) = (rgb >> 16) & 0xff //R
            ca(x)(y)(1) = (rgb >> 8) & 0xff //G
            ca(x)(y)(2) = rgb & 0xff //B

            val gray = 255 - (ca(x)(y)(0) + ca(x)(y)(1) + ca(x)(y)(2)) / 3
            val grayRgb = (gray << 16) | (gray << 8) | gray //グレースケールに変換


            //色情報を座標ごとにセット
            out.setRGB(x, y, grayRgb)
         }
      }

      //書き込み
      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
