package com.apang.icecream.systemmanager.domain.vo;

public class ReturnView {
  private String name;
  private int viewCount;
  private int userCount;
  private int ipCount;
  
  public ReturnView(String name,int viewCount,int userCount,int ipCount){
    this.viewCount = viewCount;
    this.userCount = userCount;
    this.ipCount=ipCount;
    this.setName(name);
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getUserCount() {
    return userCount;
  }

  public void setUserCount(int userCount) {
    this.userCount = userCount;
  }

  public int getIpCount() {
    return ipCount;
  }

  public void setIpCount(int ipCount) {
    this.ipCount = ipCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
