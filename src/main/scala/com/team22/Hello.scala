package example
import com.mongodb.spark._

import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.FloatType
import org.apache.spark.sql.expressions.Window
import org.apache.spark.ml.feature.{CountVectorizer, RegexTokenizer, StopWordsRemover}
import org.apache.spark.sql.functions._
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.Pipeline

object Hello extends Greeting with App {
  println(greeting)
  
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder()
    .master("local")
    .appName("MongoSparkConnectorIntro")
    .config("spark.mongodb.input.uri", "mongodb://localhost/BloombergNews.news")
    .config("spark.mongodb.output.uri", "mongodb://localhost/BloombergNews.news")
    .getOrCreate()
  import spark.implicits._
  
  val df = MongoSpark.load(spark) 
  df.printSchema() 

//  import df.sparkSession.implicits._
//  df.registerTempTable("data")
//  val dataExplode = spark.sql("select to_date(date) as date, explode(news) as news from data")
//  dataExplode.show(20, false)
//  dataExplode.printSchema()
// 
//  val pricedf = spark.read.format("csv").option("header", "true").load("C:\\Users\\eugene\\Desktop\\Big Data Engineering\\Project\\fx_daily_EUR_USD.csv")
//  pricedf.printSchema()
// 
//  pricedf.registerTempTable("pricedata")
//  val selectpricedf = spark.sql("select to_date(timestamp) as date, close as close from pricedata")
//  selectpricedf.withColumn("close", selectpricedf("close") cast FloatType)
// 
//  
//  val w = Window.partitionBy().orderBy("date")
//  val pricetable = selectpricedf.withColumn("signal", when(lag("close", -1, 0).over(w) > selectpricedf("close"), 1).otherwise(0))
//  pricetable.show()
//  pricetable.printSchema()
//
//  pricetable.registerTempTable("pricetable")
//  dataExplode.registerTempTable("newstable")
// 
//  val merged = spark.sql("""SELECT pricetable.date as date, newstable.news as news, pricetable.signal as label FROM  pricetable JOIN  newstable ON pricetable.date == newstable.date""")
//  merged.show()
//  
//  val sep = merged.limit(1)
//  
//  val stopwords: Array[String] = spark.sparkContext.textFile("C:\\Users\\eugene\\Desktop\\Big Data Engineering\\stopwords.txt").flatMap(_.stripMargin.split("\\s+")).collect ++ Array("rt")
// 
// stopwords.foreach(println)
//  
//  val tokenizer = new RegexTokenizer()
//  .setGaps(false)
//  .setPattern("\\p{L}+")
//  .setInputCol("news")
//  .setOutputCol("words")
//  
//  val filterer = new StopWordsRemover()
//  .setStopWords(stopwords)
//  .setCaseSensitive(false)
//  .setInputCol("words")
//  .setOutputCol("filtered")
//  
//  val countVectorizer = new CountVectorizer()
//  .setInputCol("filtered")
//  .setOutputCol("features1")
//  
//  val hashingTF = new HashingTF()
//  .setInputCol("filtered").setOutputCol("rawFeatures").setNumFeatures(20)
//  
//  val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
//  
//  val lr = new LogisticRegression()
//  .setMaxIter(10)
//  .setRegParam(0.2)
//  .setElasticNetParam(0.0)
//  
//  val pipeline = new Pipeline().setStages(Array(tokenizer, filterer))
//  
//  merged.printSchema()
//  merged.show(merged.count.toInt, false)
//  
//  val lrModel = pipeline.fit(merged)
//  val results = lrModel.transform(merged)
//  results.show(20, false)
//  results.printSchema()
  
//  val one = spark.createDataFrame(Seq(
//  (0, Array("a", "b", "c", "ghf")),
//  (1, Array("a", "b", "b", "c", "a", "d")))).toDF("id", "words")
//
//  one.printSchema()
//  
//  val count = new CountVectorizer()
//  .setInputCol("words")
//  .setOutputCol("features")
//  
//  val pipe = new Pipeline().setStages(Array(count))
//  
//  val ltestModel = pipe.fit(one)
//  val resultsg = ltestModel.transform(one)
//  resultsg.show(20, false)
  

  println(greeting)
  
}

trait Greeting {
  lazy val greeting: String = "hello"
}
