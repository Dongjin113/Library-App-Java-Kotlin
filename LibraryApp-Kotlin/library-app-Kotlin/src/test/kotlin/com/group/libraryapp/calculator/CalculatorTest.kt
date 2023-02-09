package com.group.libraryapp.calculator

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}

class CalculatorTest {

    fun addTest(){
//        given
        val calculator = Calculator(5)

//        when
        calculator.add(3)
// data class임으로 equals가 자동으로 구현이 되있다
//        private임으로 data를 가져오지못함으로 data class를 사용해줬다
//        val expectedCalculator = Calculator(8)
//        if(calculator != expectedCalculator){
//            throw IllegalArgumentException()
//        }

//        then
        if(calculator.number != 8){
            throw IllegalArgumentException();
        }

    }

    fun minusTest(){

//        given
        val calculator = Calculator(5)

//        when
        calculator.minus(3)

        if(calculator.number != 2){
            throw IllegalArgumentException();
        }
    }

    fun multiplyTest(){

//        given
        val calculator = Calculator(5)

//        when
        calculator.multiply(3)

        if(calculator.number != 15){
            throw IllegalArgumentException();
        }
    }

    fun divideTest(){

//        given
        val calculator = Calculator(5)

//        when
        calculator.divide(2)

        if(calculator.number != 2){
            throw IllegalArgumentException();
        }
    }


    fun divideExceptionTest(){
//        given
        val calculator = Calculator(5)

        try{
            calculator.divide(0)
        }catch (e: IllegalArgumentException){
            if(e.message != "0으로 나눌 수 없습니다"){
                throw IllegalStateException("메세지가 다릅니다.")
            }
//            테스트 성공
            return
        }catch (e: Exception){
            throw IllegalStateException("기대하는 예외가 발생하지 않았습니다")
        }
    }
}