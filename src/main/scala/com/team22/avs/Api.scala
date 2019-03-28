package com.team22.avs

import java.text.SimpleDateFormat

import com.typesafe.scalalogging.Logger
import org.apache.http.client.fluent.Request

import scala.util.Try

class Api(key: String, outputSize: String = "full", logger: Logger = Logger("alpha-vantage-scala")) {
  private val baseUri = "https://www.alphavantage.co/query"

  private def httpRequest(function: String, symbol: String): Try[String] = Try(
    Request
    .Get(s"$baseUri?function=$function&symbol=$symbol&apikey=$key&outputsize=$outputSize")
    .execute()
    .returnContent()
    .asString())

  private def request(function: String, symbol: String): Option[String] = httpRequest(function, symbol).fold(
    error => {
      logger.error(s"Error attempting to call the API. [${error.getLocalizedMessage}]")
      None
    },
    result => Some(result))

  /**
    * Calls the "Time Series Daily" API for the given symbol. Errors are logged via the logger field.
    * @param symbol Provided as symbol query string parameter.
    * @return An ApiResult wrapping the parsed time series sorted by oldest first (if successful), and the raw JSON response (if API call was successful).
    */
  def timeSeriesDaily(symbol: String): ApiResult[TimeSeriesDaily] = {
    val rawJsonMaybe = request("TIME_SERIES_DAILY", symbol)
    val ts: Option[TimeSeriesDaily] = rawJsonMaybe.flatMap(rawJson => Try(Some(TimeSeriesDaily.parse(rawJson))).fold(
      error => {
        logger.error(s"Error while parsing the JSON response. [${error.getLocalizedMessage}]")
        None
      },
      result => result))
    new ApiResult[TimeSeriesDaily](ts, rawJsonMaybe)
  }
}
