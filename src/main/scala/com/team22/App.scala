package com.team22

import avs.Api
import com.team22.xrate.Api2

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

    val api2 = new Api2(key)
    val result2 = api2.getData("USD", "JPY")
    result2.timeSeries.get.exchangeRate.map(p => p.exchangeRate.exchangeRate).foreach(println)
    result2.timeSeries match {
      case None => println("It didn't work! Check input parameters, or rawJson")
      case Some(ts) => ts.exchangeRate.map(p => p.exchangeRate.lastRefreshed).foreach(println)
    }
    // Do some stuff
//    println(result.timeSeries.get.metadata)
    println(result2.rawJson)
  }
}
