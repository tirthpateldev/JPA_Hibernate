package com.gjj.igden.service.accountService;

import com.gjj.igden.dao.AccountDao;
import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class AccountService {
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private WatchListDescDao watchListDescDao;

  public List<Account> getAccountList() {
    return accountDao.getAllAccounts();
  }

  public boolean createAccount(Account account) {
    boolean resultFlag = accountDao.create(account);
    if (resultFlag) {
      return true;
    } else {
      System.err.println(" something bad happen - account wasn't added ");
      return false;
    }
  }

  public boolean updateAccount(Account account) {
    return accountDao.update(account);
  }

  public Account retrieveAccount(int accId) {
    Account user = accountDao.getAccountById(accId);
    List<IWatchListDesc> dataSetList = watchListDescDao.getDataSetsAttachedToAcc(accId);
    user.setDataSets(dataSetList);
    return user;
  }

  public boolean delete(int id) {
    return accountDao.delete(id);
  }

  public boolean setImage(int accId, InputStream is) {
    return accountDao.setImage(accId, is);
  }

  public byte[] getImage(int accId){
    return accountDao.getImage(accId);
  }
}
