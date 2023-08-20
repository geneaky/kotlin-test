import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class SyntaxTest {

    @Test
    fun `문법 테스트`() {
        val a : Int = 1
        val c = 2
        val b : Int
        b = 3;
//        val d
//        d = 4

        var e = "afea"
        e = "hi"

//        var f = 123
//        f = "hi"

        //함수 내부 하수 할당이 가능하네
        fun sum(a : Int, b : Int): Int {
            return a+b;
        }

        assertEquals(sum(1,2),3);

        fun sum1(a : Int, b : Int) : Int = a+b;
        fun sum2(a: Int, b:Int) = a+b;

        //body가 있는 메서드 반환타입 필수
//        fun sum3(a: Int, b:Int) {
//            return a+b;
//        }

        //return type없는 경우 Unit 자동 반환
        fun printSum(a : Int, b : Int) : Unit {
            println("$a + $b = ${a+b}")

        }

        printSum(1,2)

        //default parameter
        fun greeting(message: String = "hi") {
            println(message)
        }

        greeting("by")
        greeting()

        fun log(level: String = "INFO", message : String) {
            println("[$level] $message")
        }

        //named arguemnt
        log(message ="info log")
        log(message = "debug log", level = "debug")
        log(level = "warn", message = "warn log")

    }

}