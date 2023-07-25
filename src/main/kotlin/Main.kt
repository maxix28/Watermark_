import kotlin.system.exitProcess
import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
class watermark(){
    var Width=0
    var Height=0
    var N_components=0
    var N_color_components=0
    var pixel=0
    var Transparency=0
    var name =""
    init{
        get_name()
        get_info()
        printInfo()
    }
    fun get_name(){
        println("Input the image filename:")
        val filePath = readln() // Replace this with the actual file path

        val file = File(filePath)
        if (!file.exists()) {
            println("The file $filePath doesn't exist.")
            exitProcess(0)
        }
        name=filePath
    }

    fun get_info(){
        val image: BufferedImage? =ImageIO.read(File(name))
        if (image != null) {
            Width = image.width
            Height = image.height
            N_components = image.colorModel.numComponents
            N_color_components = image.colorModel.numColorComponents
            pixel = image.colorModel.pixelSize
            Transparency = image.transparency
        }
    }
    fun printInfo(){
        println("""
Image file: $name
Width: $Width
Height: $Height
Number of components: $N_components
Number of color components: $N_color_components
Bits per pixel: $pixel
    """.trimIndent())
        when(Transparency) {
            1 -> println("Transparency: OPAQUE")
            2 -> println("Transparency: BITMASK")
            3 -> println("Transparency: TRANSLUCENT")
        }
    }
}

fun main() {
    val game = watermark()
}
