package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class JunitCalculatorTest {
    /*==참인지 거짓인지
    * val isNew = true
    * assertThat(isNew).isTrue
    * assertThat(isNew).isFalse
    * 
    * ==주어진 컬렉션의 size가 원하는 값인지 검증
    * val people = listof(Person("A"), Person("B"))
    * assertThat(people).hasSize(2)
    *
    * ==주어진 컬렉션 안의 item들에서 name이라는 프로퍼티를 추출한 후, 그 값이 A와 B인지 검증(이때 순서는 중요하지 않다)
    * val people = listof(Person("A"), Person("B"))
    * assertThat(people).extracting("name").containsExactlyInAnyOrder("A","B")
    *
    * extracting: 필드의 name의 프로퍼티를 뽑아준다
    * containsExactlyInAnyOrder: 순서와상관없이 정확한값을 비교해줘라
    * containsExactly : 순서와 상관있이 값을 비교해줘라
    *
    * == function1 함수를 실행했을 때 IllegalArgumentException이 나오는지 검증
    * message를 가져와 예외 메시지를 확인할 수도 있다.
    * val message = assertThrows<IllegalArgumentException>{function1()}.message
    * assertThat(message).isEqualTo("잘못된 값이 들어 왔습니다")
    * */

    @Test
    fun addTest(){
//        given
        val calculator = Calculator(5)
//        when
        calculator.add(3)
//        then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest(){

//        given
        val calculator = Calculator(5)
//        when
        calculator.minus(3)
//        then
        assertThat(calculator.number).isEqualTo(2)

    }

    @Test
    fun multiplyTest(){

    // given
        val calculator = Calculator(5)
    // when
        calculator.multiply(3)
    // then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divideTest(){

    // given
        val calculator = Calculator(5)
    // when
        calculator.divide(2)
    // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divideExceptionTest(){

    // given
        val calculator = Calculator(5)
    // when
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
    // then
        assertThat(message).isEqualTo("0으로 나눌 수 없습니다")

//        scope function을 활용하면
       assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다")
        }
    }



}