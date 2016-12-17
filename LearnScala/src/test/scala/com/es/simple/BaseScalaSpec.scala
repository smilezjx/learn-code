package com.es.simple

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack

/**
  * Created by xz on 12/16/16.
  */
class BaseScalaSpec  extends FlatSpec{

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    assertThrows[NoSuchElementException] {
      emptyStack.pop()
    }
  }

  "A Tuple" should "can use _1 _2 _... to access" in {
    val t = Tuple3(1, "hello", Console)
    assert(t._1 === 1)
    assert(t._2 === "hello")
    assert(t._3 === Console)
    val t2 = ("d1", "d2", "d3")
    assert(t2._3 === "d3")

  }

  "An array" should "be used like this" in {
    val a = Array("10", 20)
    assert(a(0) === "10")
    assert(a(1) === 20)
    a(0) = "test1"
    assert(a(0) === "test1")
    a.update(0, "123")
    assert(a(0) === "123")
  }

  "map function" should "act like" in {
    val l = List(1, 2, 3, 4, 5)
    val lAfterMap = l.map(x => x*2)
    assert(l === List(1, 2, 3, 4, 5))
    assert(lAfterMap === List(2, 4, 6, 8, 10))
  }

  "flatMap" should "act like" in {
    val l = List(1, 2, 3)
    def g(v: Int) = List(v-1, v, v+1)
    val a = l.map(x => g(x))
    assert(a === List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4)))

    val b = l.flatMap(x => g(x))
    assert(b === List(0, 1, 2, 1, 2, 3, 2, 3, 4))
  }

  "zip" should "act like" in {
    val a = List(1, 2, 3).zip(List("a", "b", "c"))
    assert(a === List((1, "a"), (2, "b"), (3, "c")))
  }

  "partition" should "act like" in {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val a = numbers.partition(_ % 3 == 0)
    assert(a === (List(3, 6, 9), List(1, 2, 4, 5, 7, 8, 10)))

    val b = numbers.partition(_ % 2 == 0)
    assert(b === (List(2, 4, 6, 8, 10), List(1, 3, 5, 7, 9)))

  }

  "list append" should "act like" in {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var a = 5 :: numbers
    assert(a === List(5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    assert(numbers === List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
  }

  "list filter" should "act like" in {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val a = numbers.flatMap(x => List(x)).filter(_ > 5)
    assert(a === List(6, 7, 8, 9, 10))
  }

}
