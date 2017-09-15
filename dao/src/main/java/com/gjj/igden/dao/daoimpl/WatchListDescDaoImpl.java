package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.dao.WatchListDescRowMapper;
import com.gjj.igden.dao.WatchListTickersRowMapper;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WatchListDescDaoImpl implements WatchListDescDao {
  private NamedParameterJdbcTemplate namedParamJbd;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    namedParamJbd = new NamedParameterJdbcTemplate(dataSource);
  }

  public void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd) {
    this.namedParamJbd = namedParamJbd;
  }

  public List<String> getAllStockSymbols(int watchListDescId) {
    SqlParameterSource params = new MapSqlParameterSource("id", watchListDescId);
    String sqlQuery = "SELECT instId FROM wl_tickers WHERE watchlist_id_fk = :id";
    return namedParamJbd.query(sqlQuery, params, new WatchListTickersRowMapper());
  }

  public List<IWatchListDesc> getDataSetsAttachedToAcc(int accId) {
    SqlParameterSource params = new MapSqlParameterSource("accountId", accId);
    final String getDataFromDataSetTable = "SELECT * FROM data_set WHERE account_fk_id = :accountId";
    List<IWatchListDesc> watchListDescs = namedParamJbd.query(getDataFromDataSetTable,
      params, new WatchListDescRowMapper());
    watchListDescs.forEach(p -> p.setStockSymbolsList(getAllStockSymbols(p.getWatchListId())));
    return watchListDescs;
  }

  public IWatchListDesc getWatchListDesc(int dsId, int accId) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("accId", accId);
    parameters.put("dsId", dsId);
    final String sqlQuery =
      "SELECT * FROM data_set WHERE account_fk_id = :accId AND data_set_id = :dsId";
    return namedParamJbd.queryForObject(sqlQuery, parameters, new WatchListDescRowMapper());
  }

  @Override
  public boolean addTicker(int watchlistId, String tickerName) {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("watchlistId", watchlistId);
    paramMap.put("tickerName", tickerName);
    final String INSERT_QUERY =
      "INSERT INTO wl_tickers (`watchlist_id_fk`, `instId`) VALUES (:watchlistId, :tickerName);";
    return namedParamJbd.update(INSERT_QUERY, paramMap) == 1;
  }

  @Transactional
  public boolean deleteWatchListDesc(int dsId, int accId) {
    IWatchListDesc dataSet = this.getWatchListDesc(dsId, accId);
    SqlParameterSource beanParams = new BeanPropertySqlParameterSource(dataSet);
    String sqlQuery = "	DELETE FROM data_set WHERE data_set_id = :watchListId" +
      " AND account_fk_id = :accountId;";
    return namedParamJbd.update(sqlQuery, beanParams) == 1;
  }

  @Transactional
  public boolean deleteWatchListDesc(IWatchListDesc watchListDesc) {
    SqlParameterSource beanParams = new BeanPropertySqlParameterSource(watchListDesc);
    String sqlQuery = "	DELETE FROM data_set WHERE data_set_id = :watchListId" +
      " AND account_fk_id = :accountId;";
    return namedParamJbd.update(sqlQuery, beanParams) == 1;
  }

  @Transactional
  public boolean createWatchListDesc(IWatchListDesc watchListDesc) {
    if (createWatchListDescFields(watchListDesc)) {
      for (String ticker : watchListDesc.getStockSymbolsList()) {
        if (!setWatchListTickers(ticker)) {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public boolean createWatchListDescFields(IWatchListDesc watchListDesc) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("accId", watchListDesc.getAccountId(), Types.INTEGER);
    parameters.addValue("data_set_name", watchListDesc.getWatchListName());
    parameters.addValue("market_data_frequency", watchListDesc.getMarketDataFrequency());
    parameters.addValue("data_set_description", watchListDesc.getWatchListDetails());
    parameters.addValue("data_providers", watchListDesc.getDataProviders());
    String sqlQuery = " INSERT INTO data_set ( account_fk_id, data_set_name, " +
      "data_set_description, market_data_frequency, data_providers ) " +
      "VALUES ( :accId, :data_set_name, :data_set_description," +
      " :market_data_frequency, :data_providers);";
    return namedParamJbd.update(sqlQuery, parameters) == 1;
  }

  @Transactional
  public boolean setWatchListTickers(String ticker) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("instId", ticker);
    String sqlQuery = " INSERT INTO wl_tickers (instId, watchlist_id_fk)" +
      "VALUES ( :instId, LAST_INSERT_ID()) ;";
    return namedParamJbd.update(sqlQuery, parameters) == 1;
  }

  @Transactional
  public boolean updateWatchListDesc(IWatchListDesc watchListDesc) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("data_set_id", watchListDesc.getWatchListId());
    parameters.put("account_fk_id", watchListDesc.getAccountId());
    parameters.put("data_set_name", watchListDesc.getWatchListName());
    String sqlQuery = "UPDATE data_set SET data_set_name = :data_set_name " +
      "WHERE data_set_id = :data_set_id AND account_fk_id = :account_fk_id";
    return namedParamJbd.update(sqlQuery, parameters) == 1;
  }
}
