package com.gjj.igden.dao.daoUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DaoSqlUtil {
  public static List<String> queryReader(String sql, Connection conn,
                                         boolean enableConnectionClose) {
    List<String> queryRow = new LinkedList<>();
    try (ResultSet rs = conn.createStatement().executeQuery(sql)) {
      StringBuilder sB = new StringBuilder("");
      List<Row> table = new ArrayList<>();
      Row.formTable(rs, table);
      for (Row row : table) {
        sB.setLength(0);
        sB.append("> ");
        for (Map.Entry<Object, Class> col : row.row) {
          sB.append(" | ").append((col.getValue()).cast(col.getKey()));
        }
        sB.append('\n');
        queryRow.add(sB.toString());
      }
      if (enableConnectionClose) {
        rs.close();
      }
    } catch (SQLSyntaxErrorException se) {
      if (se.getMessage().contains(" in 'where clause'")) {
        return null;
      } else {
        se.printStackTrace();
      }
    } catch (SQLException se) {
      //Handle errors for JDBC
      se.printStackTrace();
    } catch (@SuppressWarnings("TryWithIdenticalCatches") Exception e) {
      //Handle errors for Class.forName
      e.printStackTrace();
    }
    return queryRow;
  }

  public static List<String> queryReader(String sql, Connection conn) {
    return queryReader(sql, conn, false);
  }

  public static List<String> queryReaderPrepStatementId(int id, String sql, Connection connection,
                                                        boolean enableConnectionClose) {
    List<String> queryRows = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, id);
      queryRows = getStringListFromResultSet(preparedStatement, connection, enableConnectionClose);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return queryRows;
  }

  public static List<String> queryReaderPrepStatementId(String id, String sql,
                                                        Connection connection) {
    return queryReaderPrepStatementId(id, sql, connection, false);
  }

  public static List<String> queryReaderPrepStatementId(String strPar, String sql,
                                                        Connection connection,
                                                        boolean enableConnectionClose) {
    List<String> queryRows = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, strPar);
      queryRows = getStringListFromResultSet(preparedStatement, connection, enableConnectionClose);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return queryRows;
  }

  public static List<String> queryReaderPrepStatementOneParamString(String strPar, String sql,
                                                                    Connection connection) {
    return queryReaderPrepStatementOneParamString(strPar, sql, connection, false);
  }

  public static List<String> queryReaderPrepStatementOneParamString(String strPar, String sql,
                                                                    Connection connection,
                                                                    boolean enableConnectionClose) {
    List<String> queryRows = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, strPar);
      queryRows = getStringListFromResultSet(preparedStatement, connection, enableConnectionClose);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return queryRows;
  }

  public static List<String> getStringListFromResultSet(PreparedStatement preparedStatement,
                                                        Connection conn,
                                                        boolean enableConnectionClose) {
    List<String> queryRow = new LinkedList<>();
    ResultSet rs;
    try {
      rs = preparedStatement.executeQuery();
      StringBuilder sB = new StringBuilder("");
      List<Row> table = new ArrayList<>();
      Row.formTable(rs, table);
      for (Row row : table) {
        sB.setLength(0);
        sB.append("> ");
        for (Map.Entry<Object, Class> col : row.row) {
          sB.append(" | ").append((col.getValue()).cast(col.getKey()));
        }
        sB.append('\n');
        queryRow.add(sB.toString());
      }
      if (enableConnectionClose) {
        rs.close();
      }
    } catch (SQLException se) {
      se.printStackTrace();
    }
    return queryRow;
  }

  public static List<String> queryReaderPrepStatementId(int id, Connection connection, String sql) {
    return queryReaderPrepStatementId(id, sql, connection, false);
  }

  public static List<String> queryReaderPrepStatementId(String id, Connection connection,
                                                        String sql) {
    return queryReaderPrepStatementId(id, sql, connection, false);
  }

  public static void updateTableFromPrepStatementOneParamString(String strPar, String sql,
                                                                Connection conn) throws DaoException {
    try (PreparedStatement psNewBar = conn.prepareStatement(sql)) {
      psNewBar.setString(1, strPar);
      psNewBar.executeUpdate();
    } catch (SQLException e1) {
      throw new DaoException.ExceptionBuilder().setException(e1).build();
    }
  }

  public static void executableUpdateQuery(String sql, Connection conn) throws DaoException {
    executableUpdateQuery(sql, conn, false);
  }

  public static void executableUpdateQuery(String sql, Connection conn,
                                           boolean isChangesCommittable) throws DaoException {
    try {
      conn.createStatement().executeUpdate(sql);
      if (isChangesCommittable) {
        conn.commit();
      }
    } catch (SQLException se) {
      throw new DaoException.ExceptionBuilder().setException(se).build();
    } catch (@SuppressWarnings("TryWithIdenticalCatches") Exception e) {
      throw new DaoException.ExceptionBuilder().setException(e).build();
    }
  }
}
