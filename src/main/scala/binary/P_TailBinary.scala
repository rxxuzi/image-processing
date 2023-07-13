package binary

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object P_TailBinary {

   private val className = this.getClass.getSimpleName.stripSuffix("$")

   private val tv = 150

   val file : File = new File("./rsc/Gray.png")//読み込むファイル
   val dirPath = "./output/binary/"
   val fileType = ".png"//ファイルの拡張子
   val outputFile = new File(dirPath+className+"tv_"+tv+fileType)//替えた画像を係存するファイル
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

      val matrix = Array( Array(1,1,1),Array(1,1,1,1),Array(1,1,1))
      val a = 9
      val w = 1
      val zmax = 255
      for (x <- 0 until width) {
         for (y <- 0 until height) {
            val rgb = img.getRGB(x, y)
            zf(x)(y) = (rgb >> 16) & 0xff
         }
      }

      for (x <- 1 to width - 2) {
         for (y <- 1 to height - 2) {
            var z = zf(x)(y)

            zg(x)(y) = if(z < tv) 0  else zmax
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

      def cx (r: Int, g: Int, b:Int): Unit ={
         val rgb = new Color(r,g,b).getRGB
      }


   }

}
