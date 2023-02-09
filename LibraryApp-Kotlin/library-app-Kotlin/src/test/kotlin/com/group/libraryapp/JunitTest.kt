package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class JunitTest {
    /*  ( BeforeAll, AfterAll 에는 @JvmStatic 을 붙여야 한다)
    *  @BeforeAll : 모든테스트를 수행하기전에 최초 1회 수행되는 메소드를 지정한다
    *  @AfterAll: 모든 테스트를 수행한 후 최후 1회 수행되는 메소드를 지정한다
    *
    * */

    companion object{
        @BeforeAll
        @JvmStatic
        fun beforeAll(){
            println("모든 테스트 시작 전")
        }
        @AfterAll
        @JvmStatic
        fun afterAll(){
            println("모든 테스트 종료 후")
        }
    }

    @BeforeEach
    fun beforeEach(){
        println("각 테스트 시작 전")
    }
    @AfterEach
    fun afterEach(){
        println("각테스트 시작 후")
    }
    
    @Test
    fun test1(){
        println("테스트 1")
    }

    @Test
    fun test2(){
        println("테스트 2")
    }

}