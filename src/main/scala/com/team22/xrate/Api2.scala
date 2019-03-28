package com.team22.xrate

import com.typesafe.scalalogging.Logger
import org.apache.http.client.fluent.Request

import scala.util.Try

// https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=BTC&to_currency=CNY&apikey=demo
class Api2(key: String, logger: Logger = Logger("alpha-vantage-scala")) {
  private val baseUri = "https://www.alphavantage.co/query"

  private def httpRequest(function: String, fromCurrency: String, toCurrency: String): Try[String] = Try(
    Request
      .Get(s"$baseUri?function=$function&from_currency=$fromCurrency&to_currency=$toCurrency&apikey=$key")
      .execute()
      .returnContent()
      .asString())

  private def request(function: String, fromCurrency: String,
                      toCurrency: String): Option[String] = httpRequest(function, fromCurrency, toCurrency).fold(
    error => {
      logger.error(s"Error attempting to call the API. [${error.getLocalizedMessage}]")
      None
    },
    result => Some(result))

  def getData(fromCurrency: String = "USD", toCurrency: String = "CNY"): ApiResult[TimeSeriesRealTime] = {

    val rawJsonMaybe = request("CURRENCY_EXCHANGE_RATE", fromCurrency, toCurrency)
    val ts: Option[TimeSeriesRealTime] = rawJsonMaybe.flatMap(rawJson => Try(Some(TimeSeriesRealTime.parse(rawJson))).fold(
      error => {
        logger.error(s"Error while parsing the JSON response. [${error.getLocalizedMessage}]")
        None
      },
      result => result))

    new ApiResult[TimeSeriesRealTime](ts, rawJsonMaybe)
  }
}
