import org.junit.jupiter.api.Test

class GenericTest {

    class MyGenerics<out T>(val t : T) { //out을 지정하여 CharSequence타입 아규먼트로 생성한 객체가 하위 타입인 String 타입 아규먼트를 지정한 객체를 받을 수 있도록 지정(공변성)

    }

    class Bag<T> {
        fun saveAll(to : MutableList<in T>, from: MutableList<T>) { //in을 지정해서 사용하는(consumer) to쪽에서 하위 타입인 String을 사용할 수 있도록 지정
            to.addAll(from)
        }
    }

    @Test
    fun `generics test`() {
//        val generics = MyGenerics<String>("test")
        //타입 아큐먼트 생략 가능
        val generics = MyGenerics("test")

        //변수 타입에 제네릭 사용
        val list1: MutableList<String> = mutableListOf()

        //타입 아규먼트 생성자에서 추가
        val list2 = mutableListOf<String>()

        val list3 : List<*> = listOf<String>()
        val list4 : List<*> = listOf(1)

        //변성 공변성 무공변서 pecs
        //producer -> extends , consumer -> super
        // 공변성은 자바 제네릭에서의 extends가 코틀린에서 out
        // 반공변성은 자바 제네릭에서의 super가 코틀린에서 in

        var charGenerics : MyGenerics<CharSequence> = generics

        val bag = Bag<String>()
        bag.saveAll(mutableListOf<CharSequence>("1", "2"), mutableListOf<String>("3", "4"))
    }
}