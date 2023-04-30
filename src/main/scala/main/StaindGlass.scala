package main

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

/*
 * 画像の新規作成
 */
object StaindGlass {
   def main(args: Array[String]): Unit = {
      val file = new File("./rsc/Original.png")//読み込むファイル
      val outFile = "./rsc/"+ file.getName.split('.')(0) + "_Staind.png" //出力用パス
      val img = ImageIO.read(file)
      val width = img.getWidth() //画像の横pxを取得
      val height: Int = img.getHeight() //画像の縦pxを取得
      println(width + "px * " + height + "px")

      val startTime = System.currentTimeMillis()

      //コア数
      val coreNum = 1000
      println("Number of Core : "+coreNum )
      //コアを乱数で決める
      //core Array
      val core = new Array[Core](coreNum)
      for (i <- 0 until coreNum) {
         core(i) = new Core
         core(i).x = (math.random * width).toInt
         core(i).y = (math.random * height).toInt
         core(i).color = new Color(img.getRGB(core(i).x , core(i).y))
      }

      for(x  <- 0 until width){
         for(y <- 0 until height){
            var newC :Color = null
            var mini  = 100000d
            for(i <- 0 until coreNum){
               val length = math.sqrt(math.pow(core(i).x - x, 2) + math.pow(core(i).y - y, 2))
               if(length < mini){
                  mini = length
                  newC = core(i).color
               }
            }
            img.setRGB(x,y,newC.getRGB)
         }
      }

      //書き込み
      ImageIO.write(img, "png", new File(outFile))
      val endTime = System.currentTimeMillis()
      println("Done")
      println("Time : "+(endTime - startTime) + "ms")
   }

   private case class Core(){
      var x ,y = 0
      var color :Color =  null
   }
}

