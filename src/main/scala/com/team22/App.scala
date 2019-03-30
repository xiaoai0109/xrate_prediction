package com.team22

import avs.Api
import com.team22.xrate.Api2
import com.team22.hbase.HBaseTest
import java.time.LocalDate
import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

object App {
  def main(args: Array[String]): Unit = {
    // Let's get our key
//    val key = File("/Users/bensheron/.key/alpha-vantage").contentAsString.trim
    val key = "YOGEYZ72NCX11UPI"

//    val api = new Api(key)
//    // Optionally pass in a symbol
//    val symbol = if(args.nonEmpty) args(0) else "MSFT"
//    val result = api.timeSeriesDaily(symbol)
//
//    result.timeSeries.get.prices.map(p => p.price.open).foreach(println)
//
//    result.timeSeries match {
//      case None => println("It didn't work! Check input parameters, or rawJson")
//      case Some(ts) => ts.prices.map(p => p.price.open).foreach(println)
//    }

//    val api2 = new Api2(key)
//    val result2 = api2.getData("USD", "JPY")
//    result2.timeSeries.get.exchangeRate.map(p => p.exchangeRate.exchangeRate).foreach(println)
//    result2.timeSeries match {
//      case None => println("It didn't work! Check input parameters, or rawJson")
//      case Some(ts) => ts.exchangeRate.map(p => p.exchangeRate.lastRefreshed).foreach(println)
//    }
    // Do some stuff
//    println(result.timeSeries.get.metadata)
//    println(result2.rawJson)
    
   
//    HBaseTest.createTable("sk", "test1",Array("i"))
//    HBaseTest.insertTable("1", "i", "age", "23")
//    HBaseTest.insertTable("2", "i", "age", "24")
//    HBaseTest.scanDataFromHTable("i", "age")
    //HBaseTest.deleteRecord("1","i","name")
//    HBaseTest.close()
    
    val nameSpace = "xrate"
    val tableName = "prediction"
    val colFamily1 = "pred"
    val columnFamilies = Array(colFamily1)
    HBaseTest.createTable(nameSpace, tableName, columnFamilies)
    println(java.time.LocalDate.now.toString())
//    val dateTime = java.time.LocalDate.now.toString()
    val dateFormatter = new SimpleDateFormat("hhmmddMMyyyy")
    val dateTimeString = dateFormatter.format(new Date())
    HBaseTest.insertTable(dateTimeString, colFamily1, "currentXRate", "100")
    HBaseTest.insertTable(dateTimeString, colFamily1, "predictedXRate", "120")
    HBaseTest.scanDataFromHTable(colFamily1, "currentXRate")
    HBaseTest.scanDataFromHTable(colFamily1)
    //HBaseTest.deleteRecord("1","i","name")
    HBaseTest.close()
    
    
  }
}
