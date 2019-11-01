import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.math.BigInteger
import java.net.URI
import java.nio.file.Paths
import javax.imageio.ImageIO
import kotlin.test.todo

fun main(args: Array<String>) {
    // Get image
    val file = File("src/resource/Stego.png")
    val picture = ImageIO.read(file)

    val height = picture.height
    val width = picture.width

    val redNumbers = mutableListOf<String>()
    outerLoop@ for (n in 0 until height) {
        for (i in 0 until width) {
            var color = getRedColorAndLastBit(i, n, picture)
            if(color == 48) {
                break@outerLoop;
            }
            println("Binary: ${Integer.toBinaryString(color)}" )
            println("Last two: ${color shr 1 and 1}${color shr 0 and 1}" )
            redNumbers.add("${color shr 1 and 1}${color shr 0 and 1}")
        }
    }


    var num = 0
    var byte = ""
    val listOfBytes = arrayListOf<String>()
    for (n in redNumbers) {
        if(num == 4) {
            listOfBytes.add(byte)
            num = 0
            byte = ""
        }
        byte+= n
        num++
    }

    val sb = StringBuilder()
    for (bit in listOfBytes) {
        sb.append(Integer.parseInt(bit, 2).toChar())
    }
    println(sb.toString())
}

fun getRedColorAndLastBit(width: Int, height: Int, picture: BufferedImage): Int {
    val rgb = picture.getRGB(width,height)
    return rgb.ushr(16) and 0xFF
}