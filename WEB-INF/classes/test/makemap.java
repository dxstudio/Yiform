package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class makemap {

	
	 public Map transToMAP(Map parameterMap){
	      // ����ֵMap
	      Map returnMap = new HashMap();
	      Iterator entries = parameterMap.entrySet().iterator();
	      Map.Entry entry;
	      String name = "";
	      String value = "";
	      while (entries.hasNext()) {
	          entry = (Map.Entry) entries.next();
	          name = (String) entry.getKey();
	          Object valueObj = entry.getValue();
	          if(null == valueObj){
	              value = "";
	          }else if(valueObj instanceof String[]){
	              String[] values = (String[])valueObj;
	              for(int i=0;i<values.length;i++){
	                  value = values[i] + ",";
	              }
	              value = value.substring(0, value.length()-1);
	          }else{
	              value = valueObj.toString();
	          }
	          returnMap.put(name, value);
	          System.out.print("��    "+name);
	          System.out.println("   ֵ  "+value);
	      }
	      return  returnMap;
	  }
}
