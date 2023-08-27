import org.junit.jupiter.api.Test

class DataClassTest {

    data class Person(val name: String, val age : Int) {
    }

    @Test
    fun `data class test`() {
        val person1 = Person(name = "tony", age = 12)
//        val person2 = Person(name = "tony", age = 12)

//        assertEquals(person1, person2)

        val set = hashSetOf(person1)

        println(set.contains(person1))
//        person1.name="strange" //hashcode변경
        val person2 = person1.copy(name = "strange")
//        println("이름=${person2.component1()}, 나이=${person2.component2()}")
        //구조분해할당 -> 순서가 맞아야하네
        val (name, age) = person2
        println("이름=${name}, 나이=${age}")

//        println(person1.toString())
//        println(person1)


    }
}