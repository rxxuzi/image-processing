package future

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.lang
import javax.imageio.ImageIO
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/*
 * 画像を大きくする
 * 非同期処理を採用
 *
 */
object ToLargeF {
   //拡大率
   private val dx : Int = 4
   private val dy : Int = 4

   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Large_Future.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得

      println(width + "," + height)
      val f :Future[Unit] = Future {
         Thread.sleep(1)
         println("Start")
      }
      val f2 = Future{
         Thread.sleep(100)
      }

      //小さくしたときのサイズ
      val newWidth = width * dx
      val newHeight = height *  dy

      val startTime = System.currentTimeMillis() //タイマースタート
      val newImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB)//書き込み用

      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)
      f.map { _ =>
         for (y <- 0 until height ) {
            for (x <- 0 until width ) {
               val rgb = img.getRGB(x, y)
               ca(x)(y)(0) = (rgb >> 16) & 0xff //R
               ca(x)(y)(1) = (rgb >> 8) & 0xff //G
               ca(x)(y)(2) = rgb & 0xff //B
            }
         }
//         Thread.sleep(1)
         if(!f.isCompleted) println("error")
      }

      f.map{_ => println("End")
         for (y <- 0 until newHeight) {
            for (x <- 0 until newWidth) {
               val px = x / dx
               val py = y / dy
               newImg.setRGB( x, y, new Color(ca(px)(py)(0), ca(px)(py)(1), ca(px)(py)(2)).getRGB)
            }
         }
      }
      Thread.sleep(1)

      //書き込み
      ImageIO.write(newImg, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
