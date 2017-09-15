package com.gjj.igden.model;

import com.gjj.igden.utils.InstId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MarketData {
  /**
   * instance Id as a tool for solving some problems : for example conditional problems ( like
   * finding new day ) ; jMetric ; performance metrics and ect
   */
  private static final String MYSQL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  protected InstId instId;
  protected long dateTime;

  protected MarketData() {
  }

  public MarketData(InstId instId, long dateTime) {
    this.instId = instId;
    this.dateTime = dateTime;
  }

  public InstId getInstId() {
    return instId;
  }

  public void setInstId(String instId) {
    this.instId = new InstId(instId);
  }

  public void setInstId(InstId instId) {
    this.instId = instId;
  }

  public String getTicket() {
    return instId.getSymbol();
  }

  public long getDateTime() {
    return dateTime;
  }

  public void setDateTime(long dateTime) {
    this.dateTime = dateTime;
  }

  public void setDateTime(String dateTime) throws ParseException {
    SimpleDateFormat stf = new SimpleDateFormat(MYSQL_TIME_FORMAT);
    // stf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    stf.setTimeZone(instId.getExchange().getTimeZone());
    Date date = stf.parse(dateTime);
    // System.err.println(date);
    this.dateTime = date.getTime() / 1000;
  }

  public String getDateTimeMySQLFormat() throws ParseException {
    Date dateFromEpoch = new Date(dateTime * 1000);
    SimpleDateFormat df = new SimpleDateFormat(MYSQL_TIME_FORMAT);
    df.setTimeZone(instId.getExchange().getTimeZone());
    return df.format(dateFromEpoch);
  }

  public void reset() {
    this.instId = null;
    this.dateTime = -1;
  }
}
