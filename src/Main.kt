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

    val redNumbers = mutableListOf<Int>()
    for (n in 0 until height) {
        for (i in 0 until width) {
            redNumbers.add(getRedColorAndLastBit(i, n, picture))
        }
    }


    var num = 0
    var byte = ""
    val listOfBytes = arrayListOf<String>()
    for (n in redNumbers) {
        println(n)
        if(num == 8) {
            listOfBytes.add(byte)
            num = 0
            byte = ""
        }
        byte+= n
        num++
    }

    println(listOfBytes)

/*
    val arrays = mutableListOf<Byte>()
    var num = 1
    var bit = ""
    for (red in redNumbers) {
        bit += red.substring(red.length - 1)
        num++
        if(num == 10) {
            arrays.add(bit.toByte())
            num = 1
            bit = ""
        }
        println(bit)
    }
    println(arrays)
*/
}

fun getRedColorAndLastBit(width: Int, height: Int, picture: BufferedImage): Int {
    val rgb = picture.getRGB(width,height)
    val red = rgb.ushr(16) and 0xFF
    return red shr 0 and 1
}