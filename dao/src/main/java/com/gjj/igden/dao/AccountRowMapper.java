package com.gjj.igden.dao;

import com.gjj.igden.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
  public Account mapRow(ResultSet resultSet, int i) throws SQLException {
    Account account = new Account();
    account.setId(resultSet.getInt("account_id"));
    account.setAccountName(resultSet.getString("account_name"));
    account.setEMail(resultSet.getString("email"));
    account.setAdditionalInfo(resultSet.getString("additional_info"));
    account.setCreationDate(resultSet.getString("creation_date"));
    return account;
  }
}
