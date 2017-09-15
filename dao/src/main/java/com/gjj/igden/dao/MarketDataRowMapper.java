package com.gjj.igden.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("Duplicates")
public class MarketDataRowMapper implements RowMapper<String> {
  public String mapRow(ResultSet resultSet, int i) throws SQLException {
    return resultSet.getString("instId_fk");
  }
}
