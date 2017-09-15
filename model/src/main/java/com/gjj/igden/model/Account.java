package com.gjj.igden.model;

import java.util.List;
import java.util.Objects;

public class Account {
  private Integer id;
  private String accountName;
  private String eMail;
  private String additionalInfo;
  private String password;
  private List<IWatchListDesc> descriptions;
  private String creationDate;

  public Account() {
  }

  public Account(String accountName, String eMail, String additionalInfo, String password,
                 List<IWatchListDesc> descriptions, String creationDate) {
    this.accountName = accountName;
    this.eMail = eMail;
    this.additionalInfo = additionalInfo;
    this.password = password;
    this.descriptions = descriptions;
    this.creationDate = creationDate;
  }

  public Account(int id, String accountName, String eMail,
                 String additionalInfo, String password,
                 List<IWatchListDesc> dataSets, String creationDate) {
    this.id = id;
    this.accountName = accountName;
    this.eMail = eMail;
    this.additionalInfo = additionalInfo;
    this.password = password;
    this.descriptions = dataSets;
    this.creationDate = creationDate;
  }

  public Account(String accountName, String eMail,
                 String additionalInfo) {
    this.accountName = accountName;
    this.eMail = eMail;
    this.additionalInfo = additionalInfo;
  }

  public Account(Integer id, String accountName, String eMail, String additionalInfo,
                 String creationDate) {
    this.id = id;
    this.accountName = accountName;
    this.eMail = eMail;
    this.additionalInfo = additionalInfo;
    this.creationDate = creationDate;
  }

/*  public Account(String accountName, String eMail, String additionalInfo,
                 List<WatchListDesc> dataSets) {
    this(null, accountName, eMail, additionalInfo, dataSets);
  }*/

  public Account(int accountId, String accountName, String eMail, String additionalInfo) {
    this(accountId, accountName, eMail, additionalInfo, (String) null);
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<IWatchListDesc> getAttachedWatchedLists() {
    return descriptions;
  }

  public void setDescriptions(List<IWatchListDesc> descriptions) {
    this.descriptions = descriptions;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public String getEMail() {
    return eMail;
  }

  public void setEMail(String eMail) {
    this.eMail = eMail;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public List<IWatchListDesc> getDataSets() {
    return descriptions;
  }

  public void setDataSets(List<IWatchListDesc> dataSets) {
    this.descriptions = dataSets;
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return com.google.common.base.Objects.hashCode(eMail, accountName);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Account && ((Account) obj).eMail.equals(this.eMail) &&
      Objects.equals(((Account) obj).accountName, this.accountName);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(" [id=");
    builder.append(id);
    builder.append(", accountName= ");
    builder.append(accountName);
    builder.append(", eMail= ");
    builder.append(eMail);
    builder.append(", additionalInfo= ");
    builder.append(additionalInfo);
    builder.append(", data_containers Sets names= ");
    builder.append(descriptions);
    builder.append("]\n");
    return builder.toString();
  }
}
