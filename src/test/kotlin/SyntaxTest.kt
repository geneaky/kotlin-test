import org.junit.jupiter.api.Assertions.*
import java.lang.Exception
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
            3,4 -> "c"
            else -> 1
        }


    @Test
    fun `for문 종류`() {
        for(i in 0.. 3) {
            println(i)
        }

        for(i in 0 until 3) {
            println(i)
        }

        for(i in 0..6 step 2) {
            println(i)
        }

        for(i in 0 until 6 step 2) {
            println(i)
        }

        for(i in 6 downTo 1) {
            println(i)
        }

        for(i in 6 downTo 1 step 2) {
            println(i)
        }

        val array = arrayOf(1,3,4,5,6);

        for(i in array) {
            print(i)
        }

        println("---")
        array.map { n -> print(n) }

        println("---")


    }

    @Test
    fun `while문법`() {
         var x = 5

        while(x > 0) {
            println(x--)
        }
    }

    @Test
    fun `NPE test`() {
//        val a : String = null
        var b = "adfas"
//        b = null

        var c : String? = null
        val length = c?.length

        print(length)

        println("---")

        val d = c?.length ?: 0

        println(d)

        println("---")

        val nullableStr = getNullStr()
        val nullableStrLength = getLengthIfNotNull(nullableStr)
        println(nullableStrLength)

        println("---")

        //단언 연산자 , 잘 안쓸듯?
        val e = c!!.length
    }

    fun getNullStr(): String? =  null
    fun getLengthIfNotNull(str : String?) = str?.length ?: 0

    @Test
    fun `kt exception test`() {
//            Thread.sleep(1) // checked exception 강제하지 않음
        try {
            throw Exception();
        }catch (e: Exception) {
            println("exception throw")
        }finally {
            println("final execute")
        }

        val a = try {
            throw Exception() //throw도 표현 ₩식₩
            "1234".toInt()
        }catch (e: Exception) {
            println("예외")
        }

        println(a)

        val b:String? = null
        val c: String = b ?: failFast("is null")

        println(c.length)
//        failFast("예외발생")

    }

    fun failFast(message : String): Nothing {
        throw IllegalArgumentException(message)
    }

    @Test
    fun `class test`() {
        val coffee = Coffee(brand2 = 4, name = "t")
        println("${coffee.name}  ")
        coffee.name = "아아"
        coffee.price = 5000


        println("${coffee.name} 가격은 ${coffee.price} ${coffee.brand} ${coffee.brand2}  ${coffee.bran3} ${coffee.bran4}")
        coffee.name = "777"
        println("${coffee.name}  ")
    }

    class Coffee (
        name : String = "12",
        var price : Int = 0, //후행 쉼표
        val brand2: Int = 3,
    ){

        fun get2() = name;


//        val brand : String // getter method는 get()만되네 get(), set()이 예약어인듯

//            getDS() = "dd"

//        value field는 setter안됨
        val brand : String // getter method는 get()만되네
            get() = "dd"

        var bran3 : String = "hihi"
            get() = "3"
            private set

        val bran4 : String = "bran4"

        //프로퍼티는 클래스 생성자에 할당하는 방식과 내부 필드에서 할당하는 방식으로 사용할 수 있음
        //각각 프로퍼티에 대해 custom get(), set()을 구현할 수 있고 setter는 private 접근 지정자 설정 가능
        // TODO: 뭐는 생성자에 넣고 뭐는 프로퍼티에 넣는지 관례가 뭐지

        var name : String = name
            set(str : String) {
                field = str
            }

    }


    @Test
    fun `constructor property feat`() {
        val instance: Coffee2 = Coffee2("name")

        println("${instance.name1}  ${instance.name2}  ${instance.name3}")
        instance.name1 = "1"
        instance.name4 = "name 4"

        println("${instance.name4}")
    }
    class Coffee2(var name1: String, var name2: String = "name2", val name3: String = "name3"){

        var name4 : String = ""
            get() = field
            set(str : String){
                field = str
            }


//        var name1 : String = name1
    }

    class EmptyClass

    @Test
    fun `class abstract class override test`() {

        open class Developer(var language : String) {

            open fun code(){
                println("i code with $language")
            }
        }

        class BackendDeveloper(language: String) : Developer(language)  {

            override fun code() {
                println("i usaually code with $language")
            }
        }


        val developer = BackendDeveloper("Kotlin");

        developer.code()

        abstract class Dog {

            abstract var age : Int
            abstract fun bark(sound: String)
        }

        class Pug(override var age: Int) : Dog() {
            override fun bark(sound: String) {
                println("barkk $sound")
            }

        }

        val pug = Pug(20)

        pug.bark("Wwww")
    }


    @Test
    fun `interface test`() {
        val mycart = MyCart(100)
        val product = Product()
        mycart.rent()
        mycart.add(product)
        mycart.roll()
        mycart.printId()
    }

    interface Wheel {
        fun roll()
    }

    class Product {}

    interface Cart : Wheel {

        var coin : Int

        val weight: String
            get() = "20KG"
        fun add(product : Product)

        fun rent() {
            if (coin > 0) {
                println("rent")
            }
        }

        override fun roll() {
            println("rollll ")
        }

        fun printId() = println("1234")

    }

    interface Order {
        fun add(product: Product) {
            println("order add")
        }

        fun printId() = println("5678")
    }

    class MyCart(override var coin: Int) : Cart, Order {
        override fun add(product: Product) {
            if(coin <= 0) println("input coin")
            else println("addd cart")

            super<Order>.add(product)
        }

        override fun printId() {
            super<Cart>.printId();
            super<Order>.printId();
        }
    }
}