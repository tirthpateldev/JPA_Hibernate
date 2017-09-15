package com.gjj.igden.service.test;

import com.gjj.igden.service.watchlist.WatchListDescServiceService;
import com.gjj.igden.service.test.daostub.WatchListDescDaoStub;
import com.gjj.igden.model.IWatchListDesc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = {WatchListDescServiceService.class,
  WatchListDescDaoStub.class})
class SpringTextContext {
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTextContext.class)
public class WatchListDescServiceTest {
  @Autowired
  private WatchListDescServiceService watchListDescService;

  @Test
  public void simpleReadTest() throws Exception {
    watchListDescService.getStockSymbolsList(1).forEach(System.out::println);
  }

  @Test
  public void testCreateH2DataBaseTest() {
    List<IWatchListDesc> dataSetList = watchListDescService.getDataSetsAttachedToAcc(1);
    final int expectedDataSetsAmount = 4;
    Assert.assertEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testReturnBarList() {
    IWatchListDesc dataSet = watchListDescService.getDataSetsAttachedToAcc(2).get(0);
    System.out.println(dataSet.getWatchListName());
    Assert.assertNotNull(dataSet);
    Assert.assertEquals("test-aapl-5minBar-preMarketdata", dataSet.getWatchListName());
  }

  @Test
  public void testDelete02() throws Exception {
    List<IWatchListDesc> dataSetList = watchListDescService.getDataSetsAttachedToAcc(1);
    final int expectedDataSetsAmount = dataSetList.size();
    dataSetList.forEach(p -> System.out.println(p.getWatchListId()));
    boolean deleteResultFlag = watchListDescService.delete(dataSetList.get(0));
    Assert.assertTrue(deleteResultFlag);
    System.out.println("after deletion ");
    dataSetList = watchListDescService.getDataSetsAttachedToAcc(1);
    dataSetList.forEach(p -> System.out.println(p.getWatchListId()));
    Assert.assertNotEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testCreateDataSet() throws Exception {
    int accId = 1;
    IWatchListDesc newWatchList = watchListDescService.getWatchListDesc(1, accId);
    List<IWatchListDesc> dataSetList = watchListDescService.getDataSetsAttachedToAcc(1);
    dataSetList.forEach(p -> System.out.print(p.getWatchListId() + " ; "));
    int expectedDataSetsAmountAfterDeletion = 4;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
    Assert.assertNotNull(newWatchList);
    newWatchList.setWatchListId(111);
    newWatchList.setAccountId(accId);
    newWatchList.setWatchListName("just testing around");
    Assert.assertTrue(watchListDescService.create(newWatchList));
    dataSetList = watchListDescService.getDataSetsAttachedToAcc(1);
    dataSetList.forEach(p -> System.out.print(p.getWatchListId() + " ; "));
    expectedDataSetsAmountAfterDeletion = 5;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
  }

  @Test
  public void testUpdate() throws Exception {
    final int accId = 1;
    IWatchListDesc dataSet = watchListDescService.getWatchListDesc(1, accId);
    dataSet.setWatchListName("test update");
    dataSet.setAccountId(accId);
    watchListDescService.update(dataSet);
    final String dataSetNameDirect = watchListDescService.getWatchListDesc(1, 1).getWatchListName();
    Assert.assertEquals("test update", dataSetNameDirect);
  }

  @Test
  public void test01Read() throws Exception {
    List<IWatchListDesc> watchListDescs = watchListDescService.getDataSetsAttachedToAcc(1);
    final int size = 4;
    Assert.assertEquals(size,
      watchListDescs.size());
  }

  /*










  */
}
