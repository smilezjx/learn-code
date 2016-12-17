import org.apache.commons.lang.mutable.Mutable

import scala.collection.mutable.ArrayBuffer

/**
  * Created by xz on 12/16/16.
  */
object SimpleScala {

  def main(args: Array[String]) {

    for (i <- 0 to 10)
      {
        println(i)
      }
    sayHi()
    println(getSum(1,2,3))
    println(factorial(4))

    println(divideNums(5, 4))
    println(divideNums(5, 0))

    runList
    runMap

  }

  def sayHi(): Unit = {
    println("Hi how are you")
  }

  def getSum(args : Int*) : Int = {
    var sum : Int = 0
    for(num <- args)
      sum += num
    sum
  }

  def factorial(num : Int) : Int = {
    if (num <= 1)
      1
    else
      num * factorial(num-1)
  }

  def divideNums(num1 : Int, num2 : Int) = try
    {
      (num1 / num2)
    } catch {
      case ex: java.lang.ArithmeticException => "Can't divide by zero"
    } finally {
      // Clean up after exception here
    }

  def runList = {
    val friends = Array("Bob", "Tom")

    // Change the value in an array
    friends(0) = "Sue"

    println("Best Friend " + friends(0))

    // Create an ArrayBuffer
    val friends2 = ArrayBuffer[String]()

    // Add an item to the 1st index
    friends2.insert(0, "Phil")

    println(friends2)

    // Add item to the next available slot
    friends2 += "Mark"
    println(friends2)
    // Add multiple values to the next available slot
    friends2 ++= Array("Susy", "Paul")
    println(friends2)
    // Add items starting at 2nd slot
    friends2.insert(1, "Mike", "Sally", "Sam", "Mary", "Sue")
    println(friends2)
    // Remove the 2nd element
    friends2.remove(1)
    // Remove 2 elements starting at the 2nd index
    friends2.remove(1, 2)
  }

  def runMap(): Unit ={
    // ---------- MAPS ----------
    // Maps are collections of key value pairs

    // Create a Map that can't be changed (Immutable)

    val employees = Map("Manager" -> "Bob Smith", "Secretary" -> "Sue Brown")
    // Get value using the key after checking that it exists
    if(employees.contains("Manager"))
      printf("Manager : %s\n", employees("Manager"))
    // Create a Mutable map

    for((k,v) <- employees)
      printf("%s : %s\n", k, v)
  }

}
