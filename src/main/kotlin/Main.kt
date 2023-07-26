import java.awt.Color
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
    var out_File=""
    var  transparency_Percent=0

    init{
        get_name()
        get_info()
        add_watermark()
        Percentage()
        set_OutFile()
        compare_Two()
    }

        fun compare_Two(){
            val image: BufferedImage =ImageIO.read(File(name))
            val watermark: BufferedImage =ImageIO.read(File(watermark.name))
            val output = BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB)

            for (x in 0 .. image.width-1) {               // For every column.
                for (y in 0 .. image.height-1) {          // For every row
                    // val color = Color(image.getRGB(x, y))  // Read color from the (x, y) position

                    val i = Color(image.getRGB(x, y))
                    val w = Color(watermark.getRGB(x, y))              // Access the Blue color value
                    // Use color.red in case the Red color is needed

                    val r =Math.abs ((Width * w.red + (100 - Width) * i.red) / 1000)
                    val g = Math.abs(Width * w.green + (100 - Width) * i.green) / 1000
                    val b = Math.abs(Width * w.blue + (100 - Width) * i.blue) / 1000

                    var alpha=transparency_Percent

 output.setRGB(x,y,Color(r, g, b,alpha).rgb)
                }
            }

            val imageFile = File(out_File)
            ImageIO.write(output, out_File.split(".")[1], imageFile)

            println("The watermarked image $out_File has been created.")
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
        transparency_Percent=temp.toInt()
    }
    fun add_watermark(){
        //  println("Input the image filename:")

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
    fun set_OutFile(){
        println("Input the output image filename (jpg or png extension):")
        var input = readln()
        if(!".+\\.(jp|pn)g".toRegex().matches(input)){
            println("The output file extension isn't \"jpg\" or \"png\".")
            exitProcess(0)
        }
        // out_File=input
        out_File=input
    }
    fun saveImage(image: BufferedImage, imageFile: File) {
        ImageIO.write(image, out_File.substring(out_File.length-4,out_File.length-1), imageFile)
    }
}

fun main() {
    val game = watermark()
}
