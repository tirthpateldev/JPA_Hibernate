package com.gjj.igden.dao;

import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchListDescRowMapper implements RowMapper<IWatchListDesc> {

	public WatchListDesc mapRow(ResultSet resultSet, int i) throws SQLException {
		WatchListDesc watchListDesc = new WatchListDesc();
		watchListDesc.setWatchListId(resultSet.getInt("data_set_id"));
		watchListDesc.setAccountId(resultSet.getInt("account_fk_id"));
		watchListDesc.setWatchListName(resultSet.getString("data_set_name"));
		watchListDesc.setWatchListDetails(resultSet.getString("data_set_description"));
		watchListDesc.setMarketDataFrequency(resultSet.getInt("market_data_frequency"));
		watchListDesc.setDataProviders(resultSet.getString("data_providers"));
		return watchListDesc;
	}
}
