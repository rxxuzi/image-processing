package example

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 線形な濃度変換
 */
object p2_Linear_Click {
   private final val GMAX = 255
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Gray.png")//読み込むファイル
      val outFile = "./output/"+ file.getName.split('.')(0) + "_GrayLINEAR.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      //横px 縦px 色情報
//      val gray = Array.ofDim[Int](width, height)
      val a = 50
      val b = 200
      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            var g = (rgb >> 16) & 0xff //R
            g = (GMAX * (g - a).toDouble / (b - a).toDouble).toInt
            g = if (g < 0)  0 else if (g > GMAX)  GMAX else g
            val grayRgb = (g << 16) | (g << 8) | g
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
