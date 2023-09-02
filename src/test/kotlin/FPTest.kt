import org.junit.jupiter.api.Test

class FPTest {

    @Test
    fun `return function test`() {
        printHello()

        //함수를 객체에 닮을 수 있음
        val list = mutableListOf(printHello)

        list[0]()

        call(printHello)

        // 함수를 전달해주는게 아닌 Unit type을 전달해주기때문에 컴파일 에러, 함수를 일급객체로 전달해줘야함
//        list.add(printNo) compile error
//        call(printNo()) compile error

        val result: Int = plus(1, 3)
        println(result)

        val listOf = listOf("a", "b", "c")
        val printStr : (String) -> Unit = {println(it)}
        forEachStr(listOf, printStr)
//        forEachStr(listOf, println(it)) compile error
        //후행 람다 전달 -> 함수의 마지막인자가 함수인경우
        forEachStr(listOf) { println(it) } // 함수를 명시적으로 넘기지 않고 실행시에 생성해서 넘겨주면 이렇게되네
        //함수가 2개면 어떻게 되는거지?
        forEachStr2(listOf, {println(it)}, {print(it)}) // 2개부터는 함수 인자 바깥으로 뺄 수 없네
    }

    val printHello : () -> Unit = {println("hello")}

    fun call(block : () -> Unit) {
        block()
    }

    fun printNo() = println("No")

    val printMessage1 : (String) -> Unit = {message:String -> println(message)}
//    val printMessage2 : (String) -> Unit = {message -> println(message)}
//    val printMessage3 : (String) -> Unit = { println(it)}

    val plus : (Int, Int) -> Int = {a,b -> a+b}

    fun forEachStr(collection: Collection<String>, action: (String) -> Unit) {
        for(item in collection) {
            action(item)
        }
    }


    //근데 이렇게 넘기면 함수를 사용하는 시점에 -> 생성부분을 확인한다음 내부에서 함수를 어떻게 사용하고 있는지 확인해야되는거 아님?
    fun forEachStr2(collection: Collection<String>, action: (String) -> Unit, action2: (String) -> Unit) {
        for(item in collection) {
            action(item)
        }
        action2("end")
    }

    //함수를 리턴하는 함수
    fun outerFunc(): () -> Unit = {println("anonymous function")} //더 간결한 람다 표현식
//    fun outerFunc(): () -> Unit {
//        return{ println("anonymous function")} //람다 표현식
//    }
//    fun outerFunc(): () -> Unit {
//        return fun() {
//            println("anonymous function")
//        }
//    }

    @Test
    fun `anonymous function`() {
        outerFunc()() // 함수 호출 표현 2번으로 출력해야함
    }

    val sum = {x:Int,y:Int-> x+y} //일반 표현식과 구분하기위해 함수블록을 넣어야 람다 표현식이 됨 (함수표현식? 정도로 정의해봄)

    fun arg1(block: (String) -> Unit) {}

    fun arg2(block: (String, String) -> Unit) {}


    @Test
    fun`arg test`() {
        arg1 {
            it.length
            it.first()
        }

        arg2 {
//            it.length 인자가 2개이므로 it으로 구분이 불가 -> 타입 명시 필요
            a:String, b:String ->
            a.length
            b.length
        }
    }

//    val callReference : () -> Unit = {printHello()}
    val callReference = ::printHello //자바 메서레퍼런스랑 비슷하게 사용할 수 있네

    @Test
    fun `lambda reference`() {
        //함수실행 표현을 두번 사용하는게 아닌 람다표현식 안에서 또다른 고차 함수를 실행시키면 한번의 실행 표현식으로 가능
//        callReference()

        //람다레퍼런스는 또 실행 표현식 2번 필요
        callReference()()

        val numberList = listOf("1", "2", "3")

        numberList.map(String::toInt).forEach(::println)
    }
}