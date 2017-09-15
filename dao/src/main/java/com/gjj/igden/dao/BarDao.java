package com.gjj.igden.dao;

import com.gjj.igden.dao.daoUtil.DaoException;
import com.gjj.igden.model.Bar;

import java.util.List;
import java.util.Set;

public interface BarDao {
  Bar getSingleBar(long md_id, String instId);

  List<Bar> getBarList( String instId);

  boolean createBar(Bar bar) throws DaoException;

  boolean updateBar(Bar bar);

  boolean deleteBar(long md_id, String instId);

  boolean deleteBar(Bar bar);

  List<String> searchTickersByChars(String tickerNamePart);
}
