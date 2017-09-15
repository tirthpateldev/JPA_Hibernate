package com.gjj.igden.utils;
import com.google.common.base.Objects;

import java.util.TimeZone;


public class Exchange {

	final static TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("America/New_York") ;

	public static Exchange NASDAQ = new Exchange("NASDAQ",
					"National Association of Securities Dealers Automated Quotations",
					"9:30 a.m.",
					"4:00 p.m.", DEFAULT_TIME_ZONE); // UTC-4 - 70% of the year time
	public static Exchange NYSE = new Exchange("NYSE",
					"New York Stock Exchange",
					"9:30 a.m.",
					"4:00 p.m.", DEFAULT_TIME_ZONE); // // UTC-4 - - 70% of the year time
	private String exchId;
	private String name;
	private String markerHoursFrom;
	private String marketHoursTo;
	private TimeZone timeZone;

	public Exchange(String exchId, String name) {
		this.exchId = exchId;
		this.name = name;
	}

	public Exchange(String exchId, String name, String markerHoursFrom, String marketHoursTo,
                  TimeZone timeZone) {
		this.exchId = exchId;
		this.name = name;
		this.markerHoursFrom = markerHoursFrom;
		this.marketHoursTo = marketHoursTo;
		this.timeZone = DEFAULT_TIME_ZONE;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public int getMarketHoursDuration(int barSize) {
		if (barSize < 1) {
			throw new IllegalArgumentException("bar size too small ");
		}
		if (java.util.Objects.equals(markerHoursFrom, "9:30 a.m.") &&
						java.util.Objects.equals(marketHoursTo, "4:00 p.m.")) {
			return 390 / barSize;
		} else {
			throw new IllegalArgumentException("something wrong ");
		}
	}

	public int getMarketHoursDurationInMin() {
		if (java.util.Objects.equals(markerHoursFrom, "9:30 a.m.") &&
						java.util.Objects.equals(marketHoursTo, "4:00 p.m.")) {
			return 390;
		} else {
			throw new IllegalArgumentException("something wrong ");
		}
	}

	public String getExchId() {
		return exchId;
	}

	public void setExchId(String exchId) {
		this.exchId = exchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Exchange)) {
			return false;
		}
		Exchange exchange = (Exchange) o;
		return Objects.equal(exchId, exchange.exchId) &&
						Objects.equal(name, exchange.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(exchId, name);
	}

	@Override
	public String toString() {
		return "Exchange{" +
						"exchId='" + exchId + '\'' +
						", name='" + name + '\'' +
						"} " + super.toString();
	}
}
