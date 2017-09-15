/*
package com.gjj.igden.service.test;

import com.gjj.igden.model.Account;
import com.gjj.igden.model.WatchListDesc;
import com.gjj.igden.service.accountService.AccountService;
import com.gjj.igden.service.test.daostub.AccountDaoStub;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ComponentScan(basePackageClasses = {AccountService.class,
  AccountDaoStub.class})
class SpringConfigContextAccountService {
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfigContextAccountService.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {
  @Autowired
  private AccountService accountService;

  @Test
  public void simpleReadTest01(){
    List<Account> accounts = accountService.getAccountList();
    accounts.forEach(System.out::println);
    //  Assert.assertEquals("quant@account.com", accounts.get(0));
  }

  @Test
  public void simpleReadTest02()  {
    List<Account> accounts = accountService.getAccountList();
    accounts.forEach(System.out::println);
    System.out.println("==========");
    System.out.println("==========");
    List<WatchListDesc> watchListDescs = new ArrayList<>(1);
    accounts.stream()
      .filter(p -> p.getAccountName().equals("accountName_test2")).findAny()
      .ifPresent(p -> watchListDescs.addAll(p.getAttachedWatchedLists()));
    System.out.println(watchListDescs.size());
    Assert.assertEquals(1, watchListDescs.size());
    Assert.assertTrue(accounts.stream()
      .anyMatch(p -> p.getAccountName().equals("accountName_test1")));
  }

  @Test
  public void testEditData()  {
    Account account = accountService.retrieveAccount(2);
    String oldName = account.getAccountName();
    account.setAccountName("test update");
    accountService.updateAccount(account);
    String newName = accountService.retrieveAccount(2).getAccountName();
    Assert.assertNotEquals(newName, oldName);
  }



  @Test
  public void testCreate()  {
    Account account = accountService.retrieveAccount(1);
    account.setId(111);
    account.setAccountName(" ***** new Account ****** ");
    int oldSize = accountService.getAccountList().size();
    accountService.createAccount(account);
    int newSize = accountService.getAccountList().size();
    List<Account> accounts = accountService.getAccountList();
    accounts.forEach(System.out::println);
    Assert.assertNotEquals(oldSize, newSize);
  }

  @Test
  public void testSimpleDelete()  {
    int oldSize = accountService.getAccountList().size();
    accountService.delete(1);
    int newSize = accountService.getAccountList().size();
    Assert.assertNotEquals(oldSize, newSize);
  }
}
*/
