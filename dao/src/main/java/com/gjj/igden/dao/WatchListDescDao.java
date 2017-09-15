package com.gjj.igden.dao;

import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public interface WatchListDescDao {
  List<String> getAllStockSymbols(int id);

  List<IWatchListDesc> getDataSetsAttachedToAcc(int id);

  void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd);

  IWatchListDesc getWatchListDesc(int dsId, int accId);

  boolean addTicker(int watchlistId, String tickerName);

  boolean deleteWatchListDesc(int dsId, int accId);

  boolean deleteWatchListDesc(IWatchListDesc watchListDesc);

  boolean createWatchListDesc(IWatchListDesc watchListDesc);

  boolean updateWatchListDesc(IWatchListDesc watchListDesc);
}
