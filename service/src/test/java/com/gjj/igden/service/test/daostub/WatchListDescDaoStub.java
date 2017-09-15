package com.gjj.igden.service.test.daostub;

import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WatchListDescDaoStub implements WatchListDescDao {
  private static Map<Integer, List<IWatchListDesc>> watchListDescsDb;

  static {
    List<IWatchListDesc> watchListDescsAttachedToAccWithIdOne = Stream.of(new WatchListDesc(),
      new WatchListDesc(), new WatchListDesc(), new WatchListDesc()).collect(Collectors.toList());
    WatchListDesc theWatchListD = new WatchListDesc();
    theWatchListD.setWatchListName("test-aapl-5minBar-preMarketdata");
    List<IWatchListDesc> watchListDescsAttachedToAccWithIdTwo = Lists.newArrayList(theWatchListD);
    watchListDescsDb = Maps.newHashMap(ImmutableMap
      .of(1, watchListDescsAttachedToAccWithIdOne,
        2, watchListDescsAttachedToAccWithIdTwo));
  }

  @Override
  public List<String> getAllStockSymbols(int id) {
    return Stream.of("C@NYSE", "GS@NYSE").collect(Collectors.toList());
  }

  @Override
  public List<IWatchListDesc> getDataSetsAttachedToAcc(int id) {
    return watchListDescsDb.get(id);
  }

  @Override
  public void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd) {
  }

  @Override
  public IWatchListDesc getWatchListDesc(int dsId, int accId) {
    return watchListDescsDb.get(accId).get(dsId);
  }

  @Override
  public boolean addTicker(int watchlistId, String tickerName) {
    return true;
  }

  @Override
  public boolean deleteWatchListDesc(int dsId, int accId) {
    return false;
  }



  @Override
  public boolean deleteWatchListDesc(IWatchListDesc watchListDesc) {
    return watchListDescsDb.entrySet()
      .stream()
      .anyMatch(e -> e.getValue()
        .removeIf(p -> p.equals(watchListDesc)));
  }

  @Override
  public boolean createWatchListDesc(IWatchListDesc watchListDesc) {
    return watchListDescsDb.get(watchListDesc.getAccountId()).add(watchListDesc);
  }

  @Override
  public boolean updateWatchListDesc(IWatchListDesc watchListDesc) {
    watchListDescsDb.get(watchListDesc.getAccountId()).stream()
      .filter(a -> a.equals(watchListDesc))
      .findFirst()
      .ifPresent(p -> p.setWatchListName(watchListDesc.getWatchListName()));
    return true;
  }
}
