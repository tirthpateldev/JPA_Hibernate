# noinspection SqlResolveForFile

SELECT * FROM market_data as md
  inner join wl_tickers as wl on wl.instId = instId_fk
where wl.watchlist_id_fk=1 and wl.instId='AAAP@NASDAQ';