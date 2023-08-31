import org.junit.jupiter.api.Test

class ScopeFunctionTest {

    @Test
    fun `scope function test`() {
        val str : String? = "hi"

        val result: Int? = str?.let{
            println(it)

            val abc : String? = "abc"
            val def: String? = "def"
            if (!abc.isNullOrBlank() && !def.isNullOrBlank()) {
                println("abcdef is not null")
            }
             123
        }

        println(result)
//        val this:String? = null
//        val it : String? = null

        val hello = "hello"
        val hi = "hi"

        hello.let{a ->
//            println(a.length)

            hi.let {b ->
                println(a.length)
                println(b.length)
            }
        }
    }

    class DatabaseClient {
        var url : String? = null
        var username : String? = null
        var password : String? = null

        fun connect() : Boolean {
            println("db connecting ..")
            Thread.sleep(1000)
            println("db connected")
            return true
        }
    }

    @Test
    fun `scope run test`() {
//        val config = DatabaseClient()
//        config.url = "localhost:3306"
//        config.username = "root"
//        config.password = "root"
//        val connected = config.connect()

//        val connected = DatabaseClient().let {
//            it.url = "localhost:3306"
//            it.username = "root"
//            it.password = "root"
//            it.connect()
//        }

        val connected = DatabaseClient().run {
            url = "localhost:3306"
            username = "root"
            password = "root"
            connect()
        }

        println(connected)
    }

    @Test
    fun `scope with test`() {
        val str = "안녕하세요"

        val length = with(str) {
            this.length
        }

        println(length)
    }

    @Test
    fun `scope with apply`() {
        DatabaseClient().apply {
            url = "localhost:3306"
            username = "root"
            password = "root"
        }.connect().run {
            println(this)
        }
    }

    class User(val name: String, val password: String) {
        fun validate() {
            if (name.isNotEmpty() && password.isNotEmpty()) {
                println("success")
            }else{
                println("fail")
            }
        }

        fun printName() = println(name)
    }

    @Test
    fun `scope with also`() {
        User(name="tony", password = "1234").also {
            it.validate()
            it.printName()
        }
    }
}