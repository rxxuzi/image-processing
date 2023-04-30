import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/*
 * 画像の新規作成
 */
object StaindGlass {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Mostima.png")//読み込むファイル
      val outFile = "./rsc/Mostima_Copy.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")
      val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

      //横px 縦px 色情報
      val ca = Array.ofDim[Int](width, height ,3)

      //コア数
      val coreNum = 5000
      //コアを乱数で決める
      //core Array
      val core = new Array[Core](coreNum)
      for (i <- 0 until coreNum) {
         core(i) = new Core
         core(i).x = (math.random * width).toInt
         core(i).y = (math.random * height).toInt
         core(i).color = new Color(img.getRGB(core(i).x , core(i).y))
      }



      //書き込み
      ImageIO.write(img, "png", new File(outFile))
      println("Done")
   }

   private case class Core(){
      var x ,y = 0
      var color :Color =  null
   }
}

