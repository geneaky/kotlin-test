import org.junit.jupiter.api.Test

class LazyInitTest {

    class HelloBot {
        // by lazy를 사용하려면 불변성이 기본 조건이기 때문에 val을 사용하고 초기화시점에
        // 초기화 메서드는 한 번만 수행됨
        val greeting : String by lazy(LazyThreadSafetyMode.NONE) { //multithread에서 동기화 여부를 설정할 수 있음
            println("initialize")
            getHello()}
        fun sayHello() = println(this.greeting)
        fun getHello() = "안녕하세요"
    }


    @Test
    fun `lazy init test`() {
        val helloBot = HelloBot()

//        helloBot.sayHello()
//        helloBot.sayHello()
//        helloBot.sayHello()

        //thread safe한가?
        for(i in 1..5) {
            Thread{
                helloBot.sayHello()
            }.start()
        }
    }

    class LateInit {
        lateinit var text : String

        //초기화 되지않은 값을 사용할경우 runtime시에 unitialize 관련 예외 발생
        // DI를 목적으로 만들어둔듯?
        fun printText() {
//            text = "hi"
            if(this::text.isInitialized) { // isInitialized는 class 내부에서 사용가능한 프로퍼티 -> 외부로 제공원할시 커스텀 게터 구현
                println("initialize")
            }else{
                text = "hi"
            }
            println(text)
        }

    }

    @Test
    fun `lateinit var test `() {
        val lateInit = LateInit()
        lateInit.text = "bi"
        lateInit.printText()
    }
}