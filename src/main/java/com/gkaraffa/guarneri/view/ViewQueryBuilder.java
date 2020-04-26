package com.gkaraffa.guarneri.view;

import java.util.HashMap;

public class ViewQueryBuilder {
  private HashMap<String, Object> hashMap = new HashMap<String, Object>();

  public ViewQueryBuilder() {
  }

  public void insertCriteria(String key, Object criteria) {
    this.hashMap.put(key, criteria);
  }
  
  public ViewQuery compileViewQuery() {
    return new ViewQuery(this.hashMap);
  }
  
  public void clear() {
    // null out hashMap member and attempt garbage collection
    this.hashMap = null;
    System.gc();

    // create new HashMap object and assign reference
    this.hashMap = new HashMap<String, Object>();
  }
}
