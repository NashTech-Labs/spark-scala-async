

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import scala.concurrent.ExecutionContext.Implicits.global

object SparkAsync extends App {
  val conf = new SparkConf().setAppName("spark_async").setMaster("local[*]").set("spark.scheduler.mode", "FAIR")
  val sc = new SparkContext(conf)
  val rdd = sc.parallelize(List(32, 34, 2, 3, 4, 54, 3), 4).cache()
  rdd.collectAsync().map{ x => x.map{x=> println("Items in the list:"+x)} }
  val rddCount = sc.parallelize(List(434, 3, 2, 43, 45, 3, 2), 4)
  rddCount.countAsync().map { x =>println("Number of items in the list: "+x) }
  println("synchronous count"+rdd.count())
  sc.stop()
}