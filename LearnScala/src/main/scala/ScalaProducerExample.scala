/**
  * Created by xz on 12/17/16.
  */

import kafka.producer.ProducerConfig
import java.util.Properties
import scala.util.Random
import kafka.producer.Producer
import kafka.producer.KeyedMessage
import java.util.Date

//sbt "run-main ScalaProducerExample 10 topic1 localhost:9092"
object ScalaProducerExample extends App {
  val events = 10000000
  val topic = "topic1"
  val brokers = "localhost:9092"
  val rnd = new Random()
  val props = new Properties()
  props.put("metadata.broker.list", brokers)
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  //props.put("partitioner.class", "com.colobu.kafka.SimplePartitioner")
  props.put("producer.type", "async")
  //props.put("request.required.acks", "1")

  val config = new ProducerConfig(props)
  val producer = new Producer[String, String](config)
  val start = System.currentTimeMillis()
  for (nEvents <- Range(0, events)) {
    val runtime = new Date().getTime()
    val ip = "192.168.2." + rnd.nextInt(255)
    val msg = runtime + "," + nEvents + ",www.example.com," + ip
    val data = new KeyedMessage[String, String](topic, ip, msg)
    producer.send(data)
  }



  val end = System.currentTimeMillis()

  System.out.println("send messages take " + (end -start)/1000)

//  System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t));
  producer.close()
}
