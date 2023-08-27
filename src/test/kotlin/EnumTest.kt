import org.junit.jupiter.api.Test

class EnumTest {

    enum class PaymentStatus(val label: String) : Payable{
        UNPAID("미지급") {
            override fun isPayable(): Boolean = true
        }, FAILED("지급 실패") {
            override fun isPayable(): Boolean = false
        }, PAID("지급 완료") {
            override fun isPayable(): Boolean = false
        },REFUNDED("환불") {
            override fun isPayable(): Boolean = false
        };

    }

    interface Payable {
        fun isPayable(): Boolean
    }
    @Test
    fun `enum test`() {
        println(PaymentStatus.UNPAID.label)

        if(PaymentStatus.UNPAID.isPayable()) println("결제가능")

        val paymentStatus = PaymentStatus.valueOf("PAID")
        println(paymentStatus.label)

        if(paymentStatus == PaymentStatus.PAID) println("결제 완료 상태")

        val values = PaymentStatus.values()
        for (value in values) {
            println("[${value}] (${value.label})")
        }

        for (value in values) {
            println("[${value.name}] (${value.label}) : ${value.ordinal}")
        }
    }
}