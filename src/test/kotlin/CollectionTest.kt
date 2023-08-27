import org.junit.jupiter.api.Test
import java.util.*

class CollectionTest {


    @Test
    fun `collection test`() {
        //immutable
        val currencyList = listOf("달러", "유로", "원")
        val s = currencyList[1]
        println(s)

        //mutable
        val mutableCurrencyList = mutableListOf<String>().apply {
            add("달러")
            add("유로")
            add("원")
        }

        //immutable set
        val numberSet = setOf(1,2,3,4)

        val mutableSet = mutableSetOf<Int>().apply {
            add(1)
            add(2)
            add(3)
            add(4)
        }

        //immutable map
        val numberMap = mapOf("one" to 1, "two" to 2)

        //mutable map
        val mutableMap = mutableMapOf<String, Int>()
        mutableMap["one"] = 1
        mutableMap["two"] = 2

        //컬렉션 빌더 -> mutablelist롤 리스트 생성후 immutable List로 반환하게됨
        val numberList = buildList{
            add("달러")
            add("유로")
            add("원")
        }

//        numberList.add("test") //compile error

        //linkedlist
        val linkedList = LinkedList <Int>().apply {
            addFirst(1)
            add(2)
            addLast(3)
        }

        //arraylist
        val arrayList = ArrayList<Int>().apply {
            add(1)
            add(2)
            add(3)
        }

        val iterator = currencyList.iterator()
        while(iterator.hasNext()) {
             println(iterator.next())
        }

        for(currency in currencyList) {
            println(currency)
        }

        currencyList.forEach {
            println(it)
        }

        //for loop -> map
        val lowerList = listOf("a", "b", "c")
//        val upperList = mutableListOf<String>()
//
//        for(lower in lowerList) {
//            upperList.add(lower.uppercase())
//        }

        val upperList = lowerList.map { it.uppercase() }

//        val filteredList = mutableListOf<String>()
//        for (upperCase in upperList) {
//            if (upperCase == "A" || upperCase == "C") {
//                filteredList.add(upperCase)
//            }
//        }

        val filteredList = upperList.filter { it == "A" || it == "C" }

        println(filteredList)
    }
}