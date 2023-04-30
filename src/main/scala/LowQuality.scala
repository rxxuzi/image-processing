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
   val d = 2
   val img = ImageIO.read(file)
   val width = img.getWidth()
   val height: Int = img.getHeight()
   val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

   var c = new Array[Int](width)(height)

   for (y <- 0 until height ){
      for (x <- 0 until width ){
         val rgb = img.getRGB(x,y)
         val r = (rgb >> 16) & 0xff
         val g = (rgb >>  8) & 0xff
         val b = rgb & 0xff

         val grayScale = (r + g + b) / 3
         c(x)(y) = grayScale
         out.setRGB(x, y, new Color(grayScale, grayScale, grayScale).getRGB)

      }
   }

   ImageIO.write(out, "png", new File(outFile))
   println("Done")


}
