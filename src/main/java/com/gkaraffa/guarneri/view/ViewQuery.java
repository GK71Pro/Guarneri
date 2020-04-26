package com.gkaraffa.guarneri.view;

import java.util.HashMap;

public class ViewQuery {
  private HashMap<String, Object> hashMap = null;

  public ViewQuery(HashMap<String, Object> hashMap) {
    this.hashMap = hashMap;
  }

  public Object getCriteria(String key) throws IllegalArgumentException{
    Object criteria = this.hashMap.get(key);
    
    if (criteria == null) {
      throw new IllegalArgumentException("No matching criteria found: " + key);
    }
    
    return criteria;
  }
}
