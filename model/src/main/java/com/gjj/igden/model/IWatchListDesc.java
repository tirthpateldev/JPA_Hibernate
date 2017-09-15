package com.gjj.igden.model;

import java.util.List;

public interface IWatchListDesc {
  List<String> getStockSymbolsList();

  void setStockSymbolsList(List<String> stockSymbolsList);

  void setStockSymbolsListFromOperationList(List<OperationParameters> stockSymbolsList);

  int getWatchListId();

  void setWatchListId(int watchListId);

  Integer getAccountId();

  void setAccountId(int accountId);

  String getWatchListName();

  void setWatchListName(String watchListName);

  String getWatchListDetails();

  void setWatchListDetails(String watchListDetails);

  int getMarketDataFrequency();

  void setMarketDataFrequency(int marketDataFrequency);

  String getDataProviders();

  void setDataProviders(String dataProviders);

  String toString();

  List<OperationParameters> getOperationParameterses();

  void setOperationParameterses(List<OperationParameters> operationParameterses);
}
