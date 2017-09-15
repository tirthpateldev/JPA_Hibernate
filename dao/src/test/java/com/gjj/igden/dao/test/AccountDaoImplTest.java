package com.gjj.igden.dao.test;

import com.gjj.igden.dao.daoimpl.AccountDaoImpl;
import com.gjj.igden.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-cp.xml"})
public class AccountDaoImplTest {
  @Autowired
  private AccountDaoImpl testAccountDaoImpl;

  @Before
  public void setUpInMemoryDataBaseForTests() {
    EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2) // toask: is this line  redundant  ?
      .addScript("db-init-sql-script/init-db-fintech_wsH2_moreData.sql")
      .build();
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    testAccountDaoImpl.setNamedParamJbd(template);
  }

  @Test
  public void testCreateH2DataBaseTest() {
    List<Account> accounts = testAccountDaoImpl.getAllAccounts();
    Assert.assertNotNull(accounts);
  }

  @Test
  public void test01ReadAll() throws Exception {
    List<Account> accounts = testAccountDaoImpl.getAllAccounts();
    Assert.assertEquals(2, accounts.size());
  }

  @Test
  public void testReadById() throws Exception {
    Account account = testAccountDaoImpl.getAccountById(1);
    Assert.assertNotNull(account);
  }

  @Test
  public void testDelete() throws Exception {
    List<Account> accounts = testAccountDaoImpl.getAllAccounts();
    int originalAmount = accounts.size();
    testAccountDaoImpl.delete(accounts.get(0));
    accounts = testAccountDaoImpl.getAllAccounts();
    int afterDel = accounts.size();
    Assert.assertEquals(originalAmount, afterDel + 1);
  }

  @Test
  public void testUpdate() throws Exception {
    Account accounts = testAccountDaoImpl.getAccountById(1);
    String oldInfo = accounts.getAdditionalInfo();
    accounts.setAdditionalInfo("test update");
    testAccountDaoImpl.update(accounts);
    final String additionalInfo = testAccountDaoImpl.getAccountById(1).getAdditionalInfo();
    Assert.assertNotEquals(oldInfo, additionalInfo);
    Assert.assertEquals("test update", additionalInfo);
  }

  @Test
  public void testCreateAccount() throws Exception {
    Account account = new Account();
    account.setAccountName("test");
    account.setEMail("test@test");
    account.setAdditionalInfo("test my test");
    account.setPassword("1");
    account.setAccountName("test");
    boolean flag = testAccountDaoImpl.create(account);
    System.out.println(flag);
    List<Account> accounts = testAccountDaoImpl.getAllAccounts();
    Stream<Account> newAccount = accounts.stream().filter(p -> Objects.equals(p.getAccountName(),
      "test"));
    Stream<Account> allAccounts = accounts.stream().filter(p -> p.getId() > 0);
    List<Account> result = newAccount.collect(Collectors.toList());
    List<Account> testResult = allAccounts.collect(Collectors.toList());
    Assert.assertEquals(1, result.size());
    Assert.assertEquals("test@test", result.get(0).getEMail());
    Assert.assertEquals(3, testResult.size());
  }
}
