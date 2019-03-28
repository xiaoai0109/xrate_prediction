package com.team22.avs

import java.time.LocalDate

/**
  * Represents the daily time series of market prices.
 *
  * @param metadata Metadata parsed from the JSON response.
  * @param prices Time series of daily prices, parsed from the JSON response.
  */
class TimeSeriesDaily(val metadata: Map[String, String], val prices: Vector[PriceDate])

object TimeSeriesDaily {
  def parse(json: String): TimeSeriesDaily = {
    import com.team22.avs.json.Protocol.PriceFormat
    import spray.json._
    import spray.json.DefaultJsonProtocol._
    // We have a raw JSON string from alpha vantage.
    // Have spray parse the JSON and get a generic JsObject
    val theJson = json.parseJson.asJsObject
    // The metadata is just a map of strings, we will can convert it with no fancy formatter
    val metadata = theJson.fields("Meta Data").asJsObject.convertTo[Map[String, String]]
    // Extract the time series
    val ts = theJson.fields("Time Series (Daily)").asJsObject
    // The time series is a JSON object with:
    // Key: String date (e.g. 2018-01-29)
    // Value: json object with open, close, etc. The numbers are all JSON strings.
    // We need a fancy formatter for the value
    val timeSeries = ts.fields.map {
      case (d: String, p: JsValue) => PriceDate(LocalDate.parse(d), p.convertTo[Price])
      case _ => throw new ResultMappingException("Could not map the HTTP result to a time series.")
    }.toVector.sortWith((p1, p2) => p1.date.isBefore(p2.date))

    new TimeSeriesDaily(metadata, timeSeries)
  }
}

