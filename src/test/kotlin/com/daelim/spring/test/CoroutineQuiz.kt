package com.daelim.spring.test

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.datafaker.Faker
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.random.Random

@SpringBootTest
class CoroutineQuiz {
    @Test
    fun testDataFaker(){
        val faker = Faker(Locale.KOREA)
        println(faker.name().name())
    }

    /*
        100명의 가상 사용자 이름을 로그로 출력하세요
    */
    @Test
    fun quiz1Test(): Unit = runBlocking {
        val startTime = System.currentTimeMillis()
        val jobs = List(100) {
            launch {
                println(Faker(Locale.KOREA).name().name())
            }
        }

        jobs.forEach { it.join() }

        val endTime = System.currentTimeMillis()
        println("Total time with Coroutines: ${endTime - startTime}ms")
    }

    /*
        50명의 가상 사용자의 이름, 이메일, 주소를 로그 출력하세요
     */
    @Test
    fun quiz2Test(): Unit = runBlocking {
        val startTime = System.currentTimeMillis()
        val jobs = List(50) {
            launch {
                delay(1000)
                println(Faker(Locale.KOREA).name().name()+"\n "+Faker().internet().emailAddress()+"\n "+Faker().address().fullAddress()+"\n-----------------------------------------")
            }
        }

        jobs.forEach { it.join() }

        val endTime = System.currentTimeMillis()
        println("Total time with Coroutines: ${endTime - startTime}ms")
    }

    /*
        30명의 가상 사용자의 이름과 나이 생성를 데이터클래스로 생성하고, 어린 나이 순으로 정렬 후 출력하세요
     */
    data class User(val name: String, val age: Int)

    @Test
    fun generateSortAndPrintUserAges(): Unit = runBlocking {
        var list = mutableListOf<User>()
        val startTime = System.currentTimeMillis()
        val jobs = List(30) {
            launch {
                list.add(User(Faker(Locale.KOREA).name().name(),Random.nextInt(1,100)))
            }
        }

        jobs.forEach { it.join() }

        val endTime = System.currentTimeMillis()
        println("Total time with Coroutines: ${endTime - startTime}ms")

        list.sortBy { user: User -> user.age }

        for(user in list){
            println(user.name+" / "+user.age)
        }
    }

}