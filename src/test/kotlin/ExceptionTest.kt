import org.junit.jupiter.api.Test
import java.io.FileWriter
import java.lang.Exception

class ExceptionTest {

    @Test
    fun `try with resource test`() { //auto closeable
        FileWriter("text.txt").use {
            it.write("Hello Kotlin")
        }

    }

    fun getStr(): Nothing = throw Exception("예외 발생")

    @Test
    fun `exception test`() {
//        val result = try {
//            getStr()
//        }catch (e : Exception) {
//            println(e.message)
//            "기본값"
//        }

//        val result = runCatching { getStr() }.getOrDefault("기본 값")
//        val result = runCatching { getStr() }.exceptionOrNull()
//
//        result?.let {
//            println(it.message)
//            throw it
//        }
//        val result = runCatching { getStr() }.getOrNull()
//        val result = runCatching { getStr() }.getOrElse {
//            println(it.message)
//            "기본값"
//        }

//        val result: String = runCatching {"안녕"}
//            .mapCatching {
//                throw Exception("exception")
//            }.getOrDefault("기본값")

        val result = runCatching { getStr() }
            .recoverCatching { getStr() }
            .getOrNull()
//        val result = runCatching { getStr() }
//            .recover { "복구" }
//            .getOrNull()


        println(result)
    }


}