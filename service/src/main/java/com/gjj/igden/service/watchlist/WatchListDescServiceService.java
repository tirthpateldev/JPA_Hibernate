package com.gjj.igden.service.watchlist;

import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListDescServiceService {
  @Autowired
  private WatchListDescDao watchListDescDao;

  public List<IWatchListDesc> getDataSetsAttachedToAcc(int id) {
    return watchListDescDao.getDataSetsAttachedToAcc(id);
  }

  public List<String> getStockSymbolsList(int id) {
    return watchListDescDao.getAllStockSymbols(id);
  }

  public boolean delete(int accId, int watchListId) {
    return watchListDescDao.deleteWatchListDesc(watchListId, accId);
  }

  public boolean delete(IWatchListDesc watchListDesc) {
    return watchListDescDao.deleteWatchListDesc(watchListDesc);
  }

  public boolean create(IWatchListDesc watchListDesc) {
    watchListDesc.setStockSymbolsListFromOperationList(watchListDesc.getOperationParameterses());
    return watchListDescDao.createWatchListDesc(watchListDesc);
  }

  public IWatchListDesc getWatchListDesc(int dsId, int accId) {
    return watchListDescDao.getWatchListDesc(dsId, accId);
  }

  public boolean update(IWatchListDesc watchListDesc) {
    return watchListDescDao.updateWatchListDesc(watchListDesc);
  }
}
