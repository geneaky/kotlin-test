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


    @Test
    fun `흐름제어`() {
        val job = "developer"

        //물리적 동치성 비교가 이렇게 되네?
        if (job == "developer") {
            println("직업 인증 완료")
        }else{
            println("누구세요?")
        }

        //if else 표현식으로 값을 할당 가능
        var age : Int = 10

//        val str = if(age > 10) {
//            "성인"
//        }else{
//            "어린이"
//        }
        val str = if(age > 10) "성인" else "어린이"


        assertEquals(str, "어린이")
    }

    @Test
    fun `흐름제어 2`() {
        val day = 4

        val result : Any = when(day) {
            1 -> "a"
            2 -> "b"
            3 -> "c"
            else -> 1 //반환타입이 여러개가 되는경우 Any로 지정되나봄
        }

        assertEquals(result, 1)

        when(getColor()) {
            "1" -> println("1")
            "2" -> println("2")
            //else없이 가능
        }
    }

    //컴파일 타임에 함수 반환 타입 추론 가능
    private fun getColor() = "hi"

    @Test
    fun `동적으로 반환값이 바뀌는경우 when 식 결과는?`() {
        when(getNumber(4)) {
            1 -> println("1")
            "a" -> println("a")
        }
    }

    private fun getNumber(day: Any) =
        when(day) {
            1 -> "a"
            2 -> "b"
            3 -> "c"
            else -> 1
        }



}