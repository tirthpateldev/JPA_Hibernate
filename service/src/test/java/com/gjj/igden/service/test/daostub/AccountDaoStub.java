package com.gjj.igden.service.test.daostub;

import com.gjj.igden.dao.AccountDao;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.WatchListDesc;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AccountDaoStub implements AccountDao {
  private static Map<Integer, Account> accountDbSimulator;

  static {
    accountDbSimulator = Maps.newHashMap(ImmutableMap
      .of(1, new Account(1, "accountName_test1", "eMail_test1",
          "additionalInfo_test1",
          "password_test1", Stream.of(new WatchListDesc(), new WatchListDesc(),
        new WatchListDesc(), new WatchListDesc()).collect(Collectors.toList()),
          "creationDate_test1"),
        2, new Account(2, "accountName_test2", "eMail_test2",
          "additionalInfo_test2",
          "password_test2", Stream.of(new WatchListDesc()).collect(Collectors.toList()),
          "creationDate_test2")));
  }

  public void setDataSource(DataSource dataSource) {
  }

  public void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd) {
  }

  public List<Account> getAllAccounts() {
    //**// return accountDbSimulator.entrySet().stream().collect(Collectors.toList());
    //**// return accountDbSimulator.values().stream().collect(Collectors.toList());
    //   return new ArrayList<>(accountDbSimulator.values());
    return accountDbSimulator.values()
      .stream()
      .filter(Objects::nonNull)
      .collect(Collectors.toList());
  }

  public boolean delete(Account account) {
    return false;
  }

  public boolean delete(int id) {
    if (accountDbSimulator.get(id) != null) {
      accountDbSimulator.put(id, null);
      return true;
    } else {
      return false;
    }
  }

  public Account getAccountById(int id) {
    return accountDbSimulator.get(1);
  }

  public boolean update(Account acc) {
    return accountDbSimulator.put(acc.getId(), acc) == acc;
  }

  public boolean create(Account account) {
    // return accountDbSimulator.entrySet().stream().max((e1,e2)->e1.getKey())
    // return accountDbSimulator.Co().stream().max((e1,e2)->e1.getKey())
    int biggestKeyValue = Collections
      .max(accountDbSimulator.entrySet(), Map.Entry.comparingByKey()).getKey();
    return accountDbSimulator.put(++biggestKeyValue, account) == account;
  }

  @Override
  public boolean setImage(int accId, InputStream is) {
    return true;
  }

  @Override
  public byte[] getImage(int accId) {
    return new byte[0];
  }
}
