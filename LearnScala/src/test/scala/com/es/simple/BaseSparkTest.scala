package com.es.simple

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.FunSuite
import org.apache.spark.rdd.RDD

/**
  * Created by xz on 12/16/16.
  */


class BaseSparkTest extends FunSuite with SharedSparkContext {

  test("really simple transformation") {
    val input = List("hi", "hi holden", "bye")
    val expected = List(List("hi"), List("hi", "holden"), List("bye"))
    assert(tokenize(sc.parallelize(input)).collect().toList === expected)
  }

  def tokenize(f: RDD[String]) = {
    f.map(_.split(" ").toList)
  }

}