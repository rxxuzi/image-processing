import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
/*
 * Created by IntelliJ IDEA.
 * 低解像度化
 */
object LowQuality {
   val file = new File("./rsc/Soda.png")
   val outFile = "./rsc/Soda_LowQuality.png"
   //to small image 1/2
   val d = 2
   val img = ImageIO.read(file)
   val width = img.getWidth()
   val height: Int = img.getHeight()
   val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

   var c = List()
   for (i <- 0 until width by d){
      for (j <- 0 until height by d){
         val rgb = img.getRGB(i, j)
         val r = (rgb >> 16) & 0xff
         val g =

      }
   }

   ImageIO.write(out, "png", new File(outFile))
   println("Done")


}
