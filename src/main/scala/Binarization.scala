import java.io.File
import javax.imageio.ImageIO
import java.awt._
import java.awt.image.BufferedImage

object Binarization {
  def main(args: Array[String]): Unit = {
    val file = new File("./rsc/Soda.png")
    val outFile = new File("./rsc/Soda_bin.png")
    //Image Binarization save as outFile
    val img = ImageIO.read(file)
    val width = img.getWidth
    val height = img.getHeight
    val bw = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY)
    val g = bw.createGraphics()
    g.drawImage(img, 0, 0, null)
    g.dispose()
    ImageIO.write(bw, "png", outFile)



  }
}
