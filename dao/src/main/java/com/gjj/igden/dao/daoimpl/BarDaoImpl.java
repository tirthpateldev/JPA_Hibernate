package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.BarRowMapper;
import com.gjj.igden.dao.MarketDataRowMapper;
import com.gjj.igden.dao.daoUtil.DaoException;
import com.gjj.igden.dao.BarDao;
import com.gjj.igden.model.Bar;
import com.gjj.igden.model.MarketData;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("SameParameterValue")
@Repository("barDao")
public class BarDaoImpl implements BarDao {
  private NamedParameterJdbcTemplate namedParamJbd;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    namedParamJbd = new NamedParameterJdbcTemplate(dataSource);
  }

  public void setNamedParamJbd(
    NamedParameterJdbcTemplate namedParamJbd) {
    this.namedParamJbd = namedParamJbd;
  }

  public Bar getSingleBar(long md_id, String instId) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("md_id", md_id);
    parameters.put("instId", instId);
    String sqlQuery = "SELECT * FROM market_data WHERE md_id = :md_id AND instId_fk = :instId ";
    return namedParamJbd.queryForObject(sqlQuery, parameters, new BarRowMapper());
  }

  public List<Bar> getBarList(String instId) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("instId", instId);
    String sqlQuery = "SELECT * FROM market_data WHERE instId_fk = :instId ";
    return namedParamJbd.query(sqlQuery, parameters, new BarRowMapper());
  }

  @Transactional
  public boolean createBar(Bar bar) throws DaoException {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("mdId", bar.getMdId());
    parameters.put("instId", bar.getInstId().toString());
    parameters.put("ticker", bar.getTicket());
    try {
      parameters.put("dateTime", bar.getDateTimeMySQLFormat());
    } catch (ParseException e) {
      throw new DaoException.ExceptionBuilder().setException(e).build();
    }
    parameters.put("open", bar.getOpen());
    parameters.put("high", bar.getHigh());
    parameters.put("low", bar.getLow());
    parameters.put("close", bar.getClose());
    parameters.put("volume", bar.getVolume());
    parameters.put("logInfo", bar.getLogInfo());
    String sqlQuery = " INSERT INTO Market_Data (md_id, instId_fk, ticker, " +
      " date, open, high, low, close, vol, additional_info) " +
      "VALUES (:mdId, :instId, :ticker, :dateTime," +
      ":open,:high,:low,:close ,:volume,:logInfo );";
    return namedParamJbd.update(sqlQuery, parameters) == 1;
  }

  @Transactional
  public boolean updateBar(Bar bar) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("md_id", bar.getMdId());
    parameters.put("dataSetId", bar.getDataSetId());
    parameters.put("instId", bar.getInstId().toString());
    parameters.put("logInfo", bar.getLogInfo());
    String sqlQuery = "UPDATE market_data SET additional_info = :logInfo WHERE md_id = :md_id " +
      "AND instId_fk = :instId  ";
    return namedParamJbd.update(sqlQuery, parameters) == 1;
  }

  @Transactional
  public boolean deleteBar(long mdId, String instId) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("md_id", mdId);
    parameters.put("instId", instId);
    String sqlQuery = "	DELETE FROM market_data WHERE md_id = :md_id AND  instId_fk = :instId ";
    return namedParamJbd.update(sqlQuery, parameters) == 1;
  }

  @Transactional
  public boolean deleteBar(Bar bar) {
    return deleteBar(bar.getMdId(), bar.getInstId().toString());
  }

  public List<String> searchTickersByChars(String tickerNamePart) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("searchParam", tickerNamePart + "%");
    String sqlQuery = "	SELECT DISTINCT instId_fk FROM market_data WHERE instId_fk LIKE  " +
      " :searchParam ";
    return namedParamJbd.query(sqlQuery, parameters, new MarketDataRowMapper());
  }
}
