package com.gjj.igden.service.barService;

import com.gjj.igden.model.Bar;
import com.gjj.igden.dao.BarDao;
import com.gjj.igden.dao.daoUtil.DaoException;
import com.gjj.igden.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarService {
  @Autowired
  private BarDao barDao;

  public List<Bar> getBarList(String instId) {
    return barDao.getBarList(instId);
  }

  public Bar getSingleBar(long barId, String instId) {
    return barDao.getSingleBar(barId, instId);
  }

  public boolean update(Bar bar) {
    return barDao.updateBar(bar);
  }

  public boolean createBar(Bar bar) throws ServiceException {
    try {
      return barDao.createBar(bar);
    } catch (DaoException e) {
      throw new ServiceException.ExceptionBuilder().setException(e).build();
    }
  }

  public boolean deleteBar(Bar bar) {
    return barDao.deleteBar(bar);
  }

  public List<String> searchTickersByChars(String tickerNamePart) {
    return barDao.searchTickersByChars(tickerNamePart);
  }
}
