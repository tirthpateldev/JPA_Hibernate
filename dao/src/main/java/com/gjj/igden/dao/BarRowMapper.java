package com.gjj.igden.dao;

import com.gjj.igden.model.Bar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("Duplicates")
public class BarRowMapper implements RowMapper<Bar> {
  public Bar mapRow(ResultSet resultSet, int i) throws SQLException {
    Bar bar = new Bar();
    try {
      bar.setMdId(resultSet.getLong("md_id"));
      bar.setInstId(resultSet.getString("instId_fk"));
      bar.setDateTime(resultSet.getString("date"));
      bar.setOpen(resultSet.getDouble("open"));
      bar.setHigh(resultSet.getDouble("high"));
      bar.setLow(resultSet.getDouble("low"));
      bar.setClose(resultSet.getDouble("close"));
      bar.setVolume(resultSet.getInt("vol"));
      bar.setLogInfo(resultSet.getString("additional_info"));
    } catch (ParseException e) {
      throw new SQLException(e);
    }
    return bar;
  }
}
