package example

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 諧調数変換
 */
object p1_Level_Click {
   private final val numLevel = 100 //諧調数

   def main(args: Array[String]): Unit = {

      val file = new File("./rsc/Gray.png")//読み込むファイル
      val outFile = "./output/"+ file.getName.split('.')(0) + "_Level("+ numLevel +")_Click.png" //出力用パス
      val img = ImageIO.read(file)//imageIOで画像を取得
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      val z1 : Double = 255 / numLevel
      val z2 : Double = 255 / (numLevel-1)


      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)

            var g  = ((rgb >> 16) & 0xff) / z1 //Gray
            g = g * z2 // z' = kz2
            val gray = if(g > 255)  255 else g.toInt
            //色情報を座標ごとにセット
            out.setRGB(x, y, new Color(gray, gray, gray).getRGB)
         }
      }

      //書き込み
      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")
   }
}
