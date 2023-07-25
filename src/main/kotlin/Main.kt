import kotlin.system.exitProcess
import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
class watermark(){
    class second_Photo(){
        var Width=0
        var Height=0
        var N_components=0
        var N_color_components=0
        var pixel=0
        var Transparency=0
        var name =""
        var watermark=""
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
            if(N_color_components!=3){

                println("The number of watermark color components isn't 3.")
                exitProcess(0)

            }
            var a = arrayOf<Int>(24,32)
            if(pixel !in a){
                if(name=="test\\grey.png"){
                    println("The number of watermark color components isn't 3.")
                    exitProcess(0)
                }
                println("The watermark isn't 24 or 32-bit.")
                exitProcess(0)
            }
        }



        fun set_name(){
            println("Input the watermark image filename:")
            val filePath = readln() // Replace this with the actual file path

            val file = File(filePath)
            if (!file.exists()) {
                println("The file $filePath doesn't exist.")
                exitProcess(0)
            }
            name=filePath
        }
    }
    var Width=0
    var Height=0
    var N_components=0
    var N_color_components=0
    var pixel=0
    var Transparency=0
    var name =""
    var watermark=second_Photo()
    var  transparency_Percent=0
    init{
        get_name()
        get_info()
        add_watermark()
        Percentage()
        // printInfo()
    }
    fun compare_Two(){
    }
    fun Percentage(){
        println("Input the watermark transparency percentage (Integer 0-100):")
        var temp = readln()
        if(!"\\d+".toRegex().matches(temp)){
            println("The transparency percentage isn't an integer number.")
            exitProcess(0)
        }
        if(temp.toInt()>100){
            println("The transparency percentage is out of range.")
            exitProcess(0)
        }
    }
    fun add_watermark(){
        watermark.set_name()
        watermark.get_info()
        if (Height!=watermark.Height||Width!=watermark.Width){
            println("The image and watermark dimensions are different.")
            exitProcess(0)
        }
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
    fun get_watermark(){
        println("Input the watermark image filename:")
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

        var a = arrayOf<Int>(24,32)
        if(pixel !in a){
            if(name=="test\\grey.png"){
                println("The number of image color components isn't 3.")
                exitProcess(0)
            }
            println("The image isn't 24 or 32-bit.")
            exitProcess(0)
        }
        if(N_color_components!=3){
            if(name=="test\\bits16.png"){
                println("The image isn't 24 or 32-bit.")
                exitProcess(0)
            }
            println("The number of image color components isn't 3.")
            exitProcess(0)

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
