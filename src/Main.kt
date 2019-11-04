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
            var color = getRedColorAndLastBit(i, n, picture)
            redNumbers.add(color shr 0 and 1)
        }
    }

    var num = 0
    var byte = ""
    val listOfBytes = arrayListOf<String>()
    for (n in redNumbers) {
        if(num == 8) {
            listOfBytes.add(byte)
            num = 0
            byte = ""
        }
        byte+= n
        num++
    }

    val list = mutableListOf<Char>()
    for (byte in listOfBytes) {
        if(Integer.parseInt(byte.reversed(), 2) == 0) {
            break
        }
        list.add(Integer.parseInt(byte.reversed(), 2).toChar())
    }

    var message = ""
    for (char in list) {
        message += char.toString()
    }
    println(message)


}

fun getRedColorAndLastBit(width: Int, height: Int, picture: BufferedImage): Int {
    val rgb = picture.getRGB(width,height)
    return rgb and 0x000000FF
}