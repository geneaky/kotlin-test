import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SingleTonCompanionTest {

    object Singleton {
        val a = 1234

        fun printA() = println(a)
    }

    object DateTimeUtils {
        val now : LocalDateTime
            get() = LocalDateTime.now()

        const val DEFAULT_FORMANT = "YYYY-MM-DD"

        fun same(a: LocalDateTime, b: LocalDateTime) : Boolean {
            return a == b
        }
    }

    class MyClass {

        private constructor()

        companion object MyCompanion{
            val a = 1234

            fun newInstance() = MyClass()
        }
    }

    sealed class Developer{
        abstract val name: String
        abstract fun code(language : String)
    }

    data class BackendDeveloper(override val name: String) : Developer() {
        override fun code(language: String) {
            println("i use ${language} ")
        }

    }
    data class FrontendDeveloper(override val name: String) : Developer() {
        override fun code(language: String) {
            println("i use ${language} ")
        }

    }

    object OrderDeveloper : Developer() {
        override val name: String = "익명"
        override fun code(language: String) {
        }

    }

    data class AndroidDeveloper(override val name: String) : Developer() {
        override fun code(language: String) {
        }

    }

    object DeveloperPool {
        val pool = mutableMapOf<String, Developer>()

        fun add(developer: Developer) = when(developer) {
            is BackendDeveloper -> pool[developer.name] = developer
            is FrontendDeveloper -> pool[developer.name] = developer
            else -> println("Nope")
//            else -> {
//                println("now supported")
//            }
        }

        fun get(name: String) = pool[name]
    }

    @Test
    fun `obejct pool`() {
        val backendDeveloper = BackendDeveloper(name = "tony")
        DeveloperPool.add(backendDeveloper)
        val frontendDeveloper = FrontendDeveloper(name = "kaz")
        DeveloperPool.add(frontendDeveloper)
        val androidDeveloper = AndroidDeveloper(name = "zak")
        DeveloperPool.add(androidDeveloper)

        println(DeveloperPool.get("tony"))
        println(DeveloperPool.get("kaz"))
        println(DeveloperPool.get("zak"))
    }

    @Test
    fun `companion`() {
        println(MyClass.a)
        println(MyClass.MyCompanion.a)
        println(MyClass.newInstance())
        println(MyClass.MyCompanion.newInstance())
    }

    @Test
    fun `singleton companion test`() {
        println(Singleton.a)
        Singleton.printA()

        println(DateTimeUtils.now)
        println(DateTimeUtils.now)
        println(DateTimeUtils.now)
        println(DateTimeUtils.DEFAULT_FORMANT)

        val now = DateTimeUtils.now
        println(DateTimeUtils.same(now, now))
    }
}