package com.team22.xrate

import java.time.{LocalDate, LocalDateTime}

case class ExchangeRate(fromCurrencyCode: String, fromCurrencyName: String, toCurrencyCode: String,
                        toCurrencyName: String, exchangeRate: Double, lastRefreshed: String, timeZone: String)
