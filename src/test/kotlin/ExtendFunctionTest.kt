import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExtendFunctionTest {

    @Test
    fun `extend function test`() {
        val first = "ABCD".first()
        Assertions.assertEquals(first, 'A')
        val addFirst = "ABCD".addFirst('1')
        Assertions.assertEquals(addFirst, "1ABCD")
    }

    fun String.first() : Char {
        return this[0]
    }

    fun String.addFirst(char : Char) : String {
        return char  + this.substring(0)
    }

    class MyExample {
        fun printMessage() = println("class output")

    }

    //확장함수가 이미 존재하는 함수와 동일한 시그니처를 가지고있다면 무조건 탑레벨 함수가 실행이된다
    fun MyExample.printMessage() = println("extend class output")

    fun MyExample?.printNullOrNotNull() {
        if(this == null) println("null")
        else println("not null")
    }

    @Test
    fun `extend class function`() {

        var myExample : MyExample? = null
        myExample.printNullOrNotNull()

        myExample = MyExample()
        myExample.printNullOrNotNull()
    }
}