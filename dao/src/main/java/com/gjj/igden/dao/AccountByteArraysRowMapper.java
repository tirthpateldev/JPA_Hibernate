package com.gjj.igden.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountByteArraysRowMapper implements RowMapper<byte[]> {
  public byte[] mapRow(ResultSet resultSet, int i) throws SQLException {
    return resultSet.getBytes("image");
  }
}
