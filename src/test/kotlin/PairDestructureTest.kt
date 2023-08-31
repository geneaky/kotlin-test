import org.junit.jupiter.api.Test

class PairDestructureTest {

    //f((1,3)) = 1 + 3 = 4 , 1과3을 하나의 튜플로 묶어 전달 가능
    fun plus(a:Int, b:Int) = a+b

    @Test
    fun `plus`() {
        println(plus(1,3))
    }

//    data class Tuple(val a : Int, val b : Int)
    fun plus1(pair : Pair<Int, Int>) = pair.first + pair.second

    @Test
    fun `tuple fun`() {
        println(plus1(Pair(1,3)))

        val pair = Pair("A", 1)
//        pair.first = "B" //불변

        val copy = pair.copy(first = "B") //불변이라서 copy로 새로운 객체생성

        val component2 = copy.component2()
        println(component2)

        val toList = copy.toList() //immutable list
        println(toList)

        val triple = Triple("A", "B", "C")
        triple.first
        triple.second
        triple.third

        val(a,b,c) = triple

        println("$a, $b, $c")

        val(v1, v2,v3) = triple.toList()

        println("$v1, $v2, $v3")

    }


}