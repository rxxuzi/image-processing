package main

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * エッジ検出
 * ピクセル座標(x,y)と右の座標(x+1,y)、
 * 下の座標(x,y+1)の明るさの差を計算し、
 * その差が閾値より大きい時に(x,y)をエッジとして検出
 */
object Edge {
   private val threshold = 37
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")
      val outFile = "./rsc/ "+ file.getName.split('.')(0) + " .png"
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      //最終的な色 (0 or 255)
      var a = 255

      for (y <- 0 until height) {
         for (x <- 0 until width) {
            //try catch Arrayindexout
            try{
               //x,y座標のrgbを取得
               val rgb_n = img.getRGB(x, y)
               val r = (rgb_n >> 16) & 0xff
               val g = (rgb_n >> 8) & 0xff
               val b = rgb_n & 0xff

               //x+1,y座標のrgbを取得
               val rgb_r = img.getRGB(x+1, y)
               val r_r = (rgb_r >> 16) & 0xff
               val g_r = (rgb_r >> 8) & 0xff
               val b_r = rgb_r & 0xff

               //x,y+1座標のrgbを取得
               val rgb_d = img.getRGB(x, y+1)
               val r_d = (rgb_d >> 16) & 0xff
               val g_d = (rgb_d >> 8) & 0xff
               val b_d = rgb_d & 0xff

               if((math.abs(r_r - r) > threshold) || (math.abs(g_r - g) > threshold) || (math.abs(b_r - b) > threshold)) {
                  //右のピクセルと比べて閾値より大きかったら0
                  a = 0
               }else if ((math.abs(r_d - r) > threshold) || (math.abs(g_d - g) > threshold) || (math.abs(b_d - b) > threshold)) {
                  //下のピクセルと比べて閾値より大きかったら0
                  a = 0
               }else{
                  a = 255
               }

            }
            catch{
               case e: ArrayIndexOutOfBoundsException =>
                  a = 255
            }


            //色情報を座標ごとにセット
            out.setRGB(x, y, new Color(a,a,a).getRGB)

         }
      }

      //書き込み
      ImageIO.write(out, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : " + (endTime - startTime) + "ms")
   }


}
