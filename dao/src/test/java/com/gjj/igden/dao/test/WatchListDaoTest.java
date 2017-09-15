package com.gjj.igden.dao.test;

import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.model.IWatchListDesc;
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


/*
@Configuration
@ComponentScan(basePackageClasses = {WatchListDescDao.class,
  WatchListDescStub.class})
class WatchListDaoTestConfig {
}
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-cp.xml"})
public class WatchListDaoTest {
  @Autowired
  private WatchListDescDao watchListDescDao;

  @Before
  public void setUp() {
    EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .addScript("db-init-sql-script/init-db-fintech_wsH2_moreData.sql")
      .build();
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    watchListDescDao.setNamedParamJbd(template);
  }

  @Test
  public void testAddTicker01() {
    boolean resultFlag = watchListDescDao.addTicker(15, "C@NASDAQ");
    Assert.assertTrue(resultFlag);
    Assert.assertEquals("C@NASDAQ", watchListDescDao.getAllStockSymbols(15).get(2));
  }

  @Test
  public void testGetAllStockSymbols() {
    List<String> tickerList = watchListDescDao.getAllStockSymbols(2);
    final int expectedDataSetsAmount = 18;
    System.out.println(tickerList);
    Assert.assertEquals(expectedDataSetsAmount, tickerList.size());
  }

  @Test
  public void test01Read() throws Exception {
    List<IWatchListDesc> watchListDescs = watchListDescDao.getDataSetsAttachedToAcc(1);
    final int stockSymbolNumAttachedToWatchedList17th = 18;
    Assert.assertEquals(stockSymbolNumAttachedToWatchedList17th,
      watchListDescs.get(1).getStockSymbolsList().size());
  }

  @Test
  public void testGetDataSetsAttachedToAcc() {
    List<IWatchListDesc> dataSetList = watchListDescDao.getDataSetsAttachedToAcc(1);
    final int expectedDataSetsAmount = 9;
    Assert.assertEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testReturnBarList() {
    IWatchListDesc dataSet = watchListDescDao.getWatchListDesc(1, 1);
    System.out.println(dataSet.getWatchListName());
    Assert.assertNotNull(dataSet);
    Assert.assertEquals("test-aapl-5minBar-preMarketdata", dataSet.getWatchListName());
  }

  @Test
  public void testDelete02() throws Exception {
    List<IWatchListDesc> dataSetList = watchListDescDao.getDataSetsAttachedToAcc(1);
    final int expectedDataSetsAmount = 9;
    System.out.println(" again ");
    dataSetList.forEach(p -> System.out.println(p.getWatchListId()));
    Assert.assertEquals(expectedDataSetsAmount, dataSetList.size());
    boolean deleteResultFlag = watchListDescDao.deleteWatchListDesc(dataSetList.get(0));
    //		Assert.assertTrue(deleteResultFlag);
    System.out.println("after deletion ");
    dataSetList = watchListDescDao.getDataSetsAttachedToAcc(1);
    final int expectedDataSetsAmountAfterDeletion = 8;
    dataSetList.forEach(p -> System.out.println(p.getWatchListId()));
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
  }

  @Test
  public void testCreateDataSet() throws Exception {
    IWatchListDesc dataSet = watchListDescDao.getWatchListDesc(1, 1);
    List<IWatchListDesc> dataSetList = watchListDescDao.getDataSetsAttachedToAcc(1);
    dataSetList.forEach(p -> System.out.print(p.getWatchListId() + " ; "));
    int expectedDataSetsAmountAfterDeletion = 9;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
    Assert.assertNotNull(dataSet);
    //dataSet.setWatchListId(111);
    dataSet.setWatchListName("just testing around");
    watchListDescDao.createWatchListDesc(dataSet);
  }

  @Test
  public void testUpdateDesc() throws Exception {
    IWatchListDesc dataSet = watchListDescDao.getWatchListDesc(1, 1);
    dataSet.setWatchListName("test update");
    watchListDescDao.updateWatchListDesc(dataSet);
    final String dataSetNameDirect = watchListDescDao.getWatchListDesc(1, 1).getWatchListName();
    Assert.assertEquals("test update", dataSetNameDirect);
  }
}