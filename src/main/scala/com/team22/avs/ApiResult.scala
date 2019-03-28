package com.team22.avs

/**
  * Wraps a result from the alpha vantage API.
  * @param timeSeries The time series of parsed data, or None in case of error.
  * @param rawJson The raw JSON returned by Alpha Vantage (provided in both
  *                success and error cases - this is in keeping with Alpha
  *                Vantage's wishes - see https://www.alphavantage.co/support/#support.).
  *                None if an error prevented the call from being made (e.g. bad connection).
  * @tparam T Type of the time series containing parsed data.
  */
class ApiResult[T](val timeSeries: Option[T], val rawJson: Option[String])
