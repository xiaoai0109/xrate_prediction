package com.team22.xrate.json

import com.team22.xrate.ExchangeRate
import spray.json._


object Protocol extends DefaultJsonProtocol {

  implicit object ExchangeRateFormat extends RootJsonFormat[ExchangeRate] {
    override def write(obj: ExchangeRate): JsValue = ???

    override def read(json: JsValue): ExchangeRate = json.asJsObject.getFields("1. From_Currency Code",
      "2. From_Currency Name", "3. To_Currency Code", "4. To_Currency Name", "5. Exchange Rate",
      "6. Last Refreshed", "7. Time Zone") match {
      case Seq(JsString(fromCurrencyCode),JsString(fromCurrencyName),JsString(toCurrencyCode),JsString(toCurrencyName),JsString(exchangeRate),
      JsString(lastRefreshed), JsString(timeZone)) =>
        ExchangeRate(fromCurrencyCode.toString, fromCurrencyName.toString, toCurrencyCode.toString,
          toCurrencyName.toString, exchangeRate.toDouble, lastRefreshed.toString, timeZone.toString)
      case _ => deserializationError("Price expected")
    }

  }

}
