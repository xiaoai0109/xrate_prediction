package com.team22.avs.json

import com.team22.avs.Price
import spray.json._

object Protocol extends DefaultJsonProtocol {

  // PriceFormat parses these objects (e.g.):
  // {
  //   "1. open": "117.3700",
  //   "2. high": "118.6200",
  //   "3. low": "112.0000",
  //   "4. close": "116.5600",
  //   "5. volume": "26614200"
  // }
  implicit object PriceFormat extends RootJsonFormat[Price] {
    override def write(obj: Price): JsValue = ???
    override def read(json: JsValue): Price = json.asJsObject.getFields("1. open", "2. high", "3. low", "4. close", "5. volume") match {
      case Seq(JsString(open),JsString(high),JsString(low),JsString(close),JsString(volume)) =>
        Price(open.toDouble, high.toDouble, low.toDouble, close.toDouble, volume.toDouble)
      case _ => deserializationError("Price expected")
    }
  }

}
