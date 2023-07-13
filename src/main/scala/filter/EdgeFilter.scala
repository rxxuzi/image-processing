package filter

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object EdgeFilter {
   private val className = this.getClass.getSimpleName.stripSuffix("$")

   val file : File = new File("./rsc/Gray.png")//読み込むファイル
   val dirPath = "./output/filter/"//フォルダパス
   val fileType = ".png"//ファイルの拡張子
   val outputFile = new File(dirPath+className+fileType)//替えた画像を係存するファイル
   def main(args: Array[String]): Unit = {
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val startTime = System.currentTimeMillis()
      val out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY)
      //横px 縦px 色情報
      val zf = Array.ofDim[Int](width, height) //色情報を入れる
      val zg = Array.ofDim[Int](width, height) //色情報を入れる
//      val matrix = Array.ofDim[Int](3,3)

      val matrix = Array( Array(-1,-1,-1),Array(-1,8,-1),Array(-1,-1,-1))
      val a = 9
      val w = 1
      val zmax = 255
      for (x <- 0 until width) {
         for (y <- 0 until height) {
            val rgb = img.getRGB(x, y)
            zf(x)(y) = (rgb >> 16) & 0xff
         }
      }

      for (x <- 1 to  width - 2) {
         for (y <- 1 to  height - 2) {
            var z = zf(x)(y)

            for (i <- -1 to 1) {
               for (j <- -1 to 1) {
                  z += matrix(i + 1)(j + 1) * zf(x + i)(y + j)
               }
            }

//            z = z / a


            if(z < 0) z = 0
            if(z > 255) z = 255
            zg(x)(y) = z
         }
      }

      for(x <- 0 until width){
         for(y <- 0 until height){
            out.setRGB(x,y,new Color(zg(x)(y),zg(x)(y),zg(x)(y)).getRGB)
         }
      }
      //書き込み
      ImageIO.write(out, "png", outputFile)
      val endTime = System.currentTimeMillis()
      println("Done")
      println("File ->" + outputFile.getPath)


   }

}
