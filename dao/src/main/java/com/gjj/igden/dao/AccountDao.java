package com.gjj.igden.dao;

import com.gjj.igden.model.Account;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;

public interface AccountDao {
  void setDataSource(DataSource dataSource);

  void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd);

  List<Account> getAllAccounts();

  boolean delete(Account account);

  boolean delete(int id);

  Account getAccountById(int id);

  boolean update(Account acc);

  boolean create(Account account);

  boolean setImage(int accId, InputStream is);

  byte[] getImage(int accId);
}
