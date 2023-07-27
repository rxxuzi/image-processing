package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 画像から赤成分、緑成分、青成分だけを抜き出す
 */
object RGB {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル

      val newFilePath_R = "./rsc/"+ file.getName.split('.')(0) + "_OnlyRedComponent.png" //出力用パス(R)
      val newFilePath_G = "./rsc/"+ file.getName.split('.')(0) + "_OnlyGreenComponent.png" //処算碁盤(G)
      val newFilePath_B = "./rsc/"+ file.getName.split('.')(0) + "_OnlyBlueComponent.png" //処算碁盤(B)

      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()

      //新規画像
      val newImgR = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      val newImgG = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      val newImgB = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            val rgb = img.getRGB(x, y)
            ca(x)(y)(0) = (rgb >> 16) & 0xff //R
            ca(x)(y)(1) = (rgb >> 8) & 0xff //G
            ca(x)(y)(2) = rgb & 0xff //B

            //色情報を座標ごとにセット
            newImgR.setRGB(x, y, new Color(ca(x)(y)(0), 0,0).getRGB)
            newImgG.setRGB(x, y, new Color(0, ca(x)(y)(1),0).getRGB)
            newImgB.setRGB(x, y, new Color(0, 0, ca(x)(y)(2)).getRGB)

         }
      }

      //書き込み
      ImageIO.write(newImgR, "png", new File(newFilePath_R))
      ImageIO.write(newImgG, "png", new File(newFilePath_G))
      ImageIO.write(newImgB, "png", new File(newFilePath_B))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")

   }


}
