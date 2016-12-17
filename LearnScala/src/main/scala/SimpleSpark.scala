/**
  * Created by xz on 12/16/16.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SimpleSpark {

  case class Record(status: Int, method: String, content: String)

  def stringToRecord(value: String): Record = {
    var status = 0
//    val statusString = ".HTTP/1.0 (\\d{3}).".r
//    val statusString(s1)  =  value
    if (value.contains("HTTP/1.0 200"))
      status = 200
    if (value.contains("HTTP/1.0 500"))
      status = 500
    var method = "GET"
    var content = value
    Record(status, method, content)
  }

  def main(args: Array[String]) {
    val logFile = "/Users/xz/nsservice2.txt"
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val logData = sc.textFile(logFile, 1).cache()
    var a = System.currentTimeMillis
    val httpRequestLog = logData.map(stringToRecord)

    val df = sqlContext.createDataFrame(httpRequestLog)
//    df.write.json("/Users/xz/nsservice2_http.json")
//    df.write.parquet("/Users/xz/nsservice2_http.parquet")
    var b = System.currentTimeMillis
    println(b-a)
    println(httpRequestLog)
  }

}
