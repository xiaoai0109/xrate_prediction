package com.team22.xrate

import spray.json._
import spray.json.DefaultJsonProtocol._
import com.team22.xrate.json.Protocol.ExchangeRateFormat


class TimeSeriesRealTime(val exchangeRate: Vector[ExchangeRateRealTime])

object TimeSeriesRealTime {
  def parse(json: String): TimeSeriesRealTime = {
    val theJson = json.parseJson.asJsObject

    val exchangeRate = theJson.fields.map {
      case (d: String, p: JsValue) => ExchangeRateRealTime(p.convertTo[ExchangeRate])
      case _ => throw new ResultMappingException("Could not map the HTTP result to a time series.")
    }.toVector

    new TimeSeriesRealTime(exchangeRate)
  }
}
