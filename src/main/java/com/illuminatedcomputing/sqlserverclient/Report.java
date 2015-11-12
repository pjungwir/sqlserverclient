package com.illuminatedcomputing.sqlserverclient;

import java.io.*;
import java.sql.*;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import java.util.Scanner;

public class Report {

  public static void main(final String[] argv) throws Exception {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    // Should be a string like this:
    // jdbc:sqlserver://192.168.1.2\FOO;databaseName=Bar;user=TheLogin;password=SshItsASecret
    final String url = argv[0];
    final String sqlFile = argv[1];
    final PrintStream out;
    if (argv.length > 2) {
      out = new PrintStream(argv[2], "UTF-8");
    } else {
      out = System.out;
    }

    final Connection conn = DriverManager.getConnection(url);

    final String sql = new Scanner(new File(sqlFile), "UTF-8").useDelimiter("\\Z").next();

    final Statement st = conn.createStatement();
    final ResultSet rs = st.executeQuery(sql);
    final ResultSetMetaData md = rs.getMetaData();
    final int cols = md.getColumnCount();
    final String labels[] = new String[cols];
    for (int i = 0; i < cols; i++) {
      labels[i] = md.getColumnLabel(i + 1);
      if (labels[i] == null || "".equals(labels[i])) labels[i] = md.getColumnName(i + 1);
    }

    final CSVPrinter csv = new CSVPrinter(out, CSVFormat.EXCEL);

    // First print a header
    for (int i = 0; i < cols; i++) {
      csv.print(labels[i]);
    }
    csv.println();

    // Now print all the rows
    while (rs.next()) {
      for (int i = 1; i <= cols; i++) {
        csv.print(rs.getString(i));
      }
      csv.println();
    }
  }

}
