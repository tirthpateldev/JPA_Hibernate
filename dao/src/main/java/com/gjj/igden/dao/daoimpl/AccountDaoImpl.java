package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.AccountByteArraysRowMapper;
import com.gjj.igden.dao.AccountRowMapper;
import com.gjj.igden.dao.AccountDao;
import com.gjj.igden.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
  private NamedParameterJdbcTemplate namedParamJbd;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    namedParamJbd = new NamedParameterJdbcTemplate(dataSource);
  }

  public void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd) {
    this.namedParamJbd = namedParamJbd;
  }

  public List<Account> getAllAccounts() {
    String sqlQuery = "SELECT * FROM account;";
    return namedParamJbd.query(sqlQuery, new AccountRowMapper());
  }

  @Transactional
  public boolean delete(Account account) {
    SqlParameterSource beanParams = new BeanPropertySqlParameterSource(account);
    String sqlQuery = "DELETE FROM account WHERE account_id = :id ";
    return namedParamJbd.update(sqlQuery, beanParams) == 1;
  }

  @Transactional
  public boolean delete(int id) {
    Account account = this.getAccountById(id);
    SqlParameterSource beanParams = new BeanPropertySqlParameterSource(account);
    String sqlQuery = "DELETE FROM account WHERE account_id = :id ";
    return namedParamJbd.update(sqlQuery, beanParams) == 1;
  }

  public Account getAccountById(int id) {
    SqlParameterSource params = new MapSqlParameterSource("ID", id);
    String sqlQuery = "SELECT account_id,account_name,email, additional_info, creation_date " +
      "FROM account WHERE account_id = :ID ";
    return namedParamJbd.queryForObject(sqlQuery, params, new AccountRowMapper());
  }

  @Transactional
  public boolean update(Account acc) {
    SqlParameterSource beanParams = new BeanPropertySqlParameterSource(acc);
    String sqlQuery =
      "UPDATE account SET additional_info = :additionalInfo, " +
        "account_name =:accountName," +
        "email =:eMail " +
        "WHERE account_id = :id ";
    return namedParamJbd.update(sqlQuery, beanParams) == 1;
  }

  @Transactional
  public boolean create(Account account) {
    DateFormat df = new SimpleDateFormat("dd/MM/yy");
    Date dateobj = new Date();
    String creationDate = df.format(dateobj);
    account.setCreationDate(creationDate);
    SqlParameterSource beanParams = new BeanPropertySqlParameterSource(account);
    String sqlQuery = "INSERT INTO account ( account_name, email," +
      " additional_info, password,  creation_date  ) " +
      "VALUES ( :accountName, :eMail, :additionalInfo, :password,:creationDate);";
    return namedParamJbd.update(sqlQuery, beanParams) == 1;
  }

  @Transactional
  public boolean setImage(int accId, InputStream is) {
    MapSqlParameterSource params = new MapSqlParameterSource("", is);
    params.addValue("accId", accId, Types.INTEGER);
    params.addValue("image", is, Types.BLOB);
    String sqlQuery = "UPDATE account SET image = :image WHERE account_id = :accId";
    return namedParamJbd.update(sqlQuery, params) == 1;
  }

  @Override
  public byte[] getImage(int accId) {
    //getAccountById(accId);
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("accId", accId, Types.INTEGER);
    String  sqlQuery = "SELECT image from account WHERE account_id = :accId";
    byte[] bytes = namedParamJbd.query(sqlQuery, parameters, new AccountByteArraysRowMapper()).get(0);
    return bytes;
  }
}
