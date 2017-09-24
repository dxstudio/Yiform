package test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Xtest {
	
/**
    * 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
    * @throws Exception
    */
   public String[] testTemplateWrite(Map<String, Object> params,String filename) throws Exception {
//////////////////////////////////////////////////////////////
      ////////////////////////////////////////////////////////////////
	   String filePath;
	   System.out.println("这个是结尾吗？？？");
	  
//	if(filename.equals("2"))
//   {
//	  filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\word\\download\\1.docx";
//   }else{
//	  //filePath = "D:\\Apache Software Foundation\\Tomcat 8.0\\webapps\\word\\download\\2.docx";
//	   
//	   filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\word\\download\\2.docx";
//   }
	
	filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\word\\download\\"+filename+"\\"+filename+".docx";
     
      InputStream is = new FileInputStream(filePath);
      XWPFDocument doc = new XWPFDocument(is);
      //替换段落里面的变量
      this.replaceInPara(doc, params);
      System.out.println("开始查null");
      this.replacenull(doc);
      System.out.println("null查完了");
      //替换表格里面的变量
      //this.replaceInTable(doc, params);
      System.out.println("1112");
      OutputStream os = new FileOutputStream("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\word\\download\\"+filename+"\\"+params.get("name")+".docx");
      doc.write(os);
      this.close(os);
      this.close(is);
      filePath=(String) params.get("name");
      String a[]={filename,filePath};
	  return a;
      
   }
  
   /**
    * 替换段落里面的变量
    * @param doc 要替换的文档
    * @param params 参数
    */
   private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
      Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
      XWPFParagraph para;
      while (iterator.hasNext()) {
         para = iterator.next();
         this.replaceInPara(para, params);
      }
      
      this.replaceInTable(doc, params);
      
   }
  
   /**
    * 替换段落里面的变量
    * @param para 要替换的段落
    * @param params 参数
    */
   private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
      List<XWPFRun> runs;
      Matcher matcher;
      System.out.println("//////////////////////////////kaishi");
      System.out.println(para.getParagraphText());
      if (this.matcher(para.getParagraphText()).find())
      {
    	  System.out.println("成功拼配到了表达式");
      }
      System.out.println("//////////////////////////////结束");
      if (this.matcher(para.getParagraphText()).find()) {/////////////////////////////////
         runs = para.getRuns();
         for (int i=0; i<runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String runText = run.toString();
            matcher = this.matcher(runText);
            System.out.println("现在的runText是："+runText);
            if (matcher.find()) {
            	
                while ((matcher = this.matcher(runText)).find()) {
                	System.out.println("找到的runtext----"+String.valueOf(params.get(matcher.group(1))));
                   runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                }
                //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
                para.removeRun(i);
                XWPFRun now=para.insertNewRun(i);
                
                now.setText(runText);
                now.setFontSize(12);
                
            }
         }
      }
   }
  
   /**
    * 替换表格里面的变量
    * @param doc 要替换的文档
    * @param params 参数
    */
   private void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
      Iterator<XWPFTable> iterator = doc.getTablesIterator();
      XWPFTable table;
      List<XWPFTableRow> rows;
      List<XWPFTableCell> cells;
      List<XWPFParagraph> paras;
      while (iterator.hasNext()) {
         table = iterator.next();
         rows = table.getRows();
         for (XWPFTableRow row : rows) {
            cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                paras = cell.getParagraphs();
                for (XWPFParagraph para : paras) {
                	//System.out.print(para.getParagraphText()+"\n");
                	
                   this.replaceInPara(para, params);
                }
            }
         }
      }
   }
   
   private void replacenull(XWPFDocument doc) {
	      Iterator<XWPFTable> iterator = doc.getTablesIterator();
	      XWPFTable table;
	      List<XWPFRun> runs;
	      Matcher matchers;
	      List<XWPFTableRow> rows;
	      List<XWPFTableCell> cells;
	      List<XWPFParagraph> paras;
	      while (iterator.hasNext()) {
	         table = iterator.next();
	         rows = table.getRows();
	         for (XWPFTableRow row : rows) {
	            cells = row.getTableCells();
	            for (XWPFTableCell cell : cells) {
	                paras = cell.getParagraphs();
	                for (XWPFParagraph para : paras) {
	                	//System.out.print(para.getParagraphText()+"\n");
	                   if (this.matchers(para.getParagraphText()).find()) {/////////////////////////////////
	                       runs = para.getRuns();
	                       for (int i=0; i<runs.size(); i++) {
	                          XWPFRun run = runs.get(i);
	                          String runText = run.toString();
	                          matchers = this.matchers(runText);
	                          System.out.println("现在的runText是："+runText);
	                          if (matchers.find()) {
	                          	
	                              while ((matchers = this.matchers(runText)).find()) {
	                              	System.out.println("替换null");
	                              }
	                              //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
	                              //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
	                              para.removeRun(i);
	                              para.insertNewRun(i).setText("");
	                              
	                              
	                             
	                          }
	                       }
	                    }
	                }
	            }
	         }
	      }
	   }
  
   /**
    * 正则匹配字符串
    * @param str
    * @return
    */
   private Matcher matcher(String str) {
      Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(str);
      return matcher;
   }
   private Matcher matchers(String str) {
	      Pattern pattern = Pattern.compile("null", Pattern.CASE_INSENSITIVE);
	      Matcher matcher = pattern.matcher(str);
	      return matcher;
	   }
  
   /**
    * 关闭输入流
    * @param is
    */
   private void close(InputStream is) {
      if (is != null) {
         try {
            is.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
  
   /**
    * 关闭输出流
    * @param os
    */
   private void close(OutputStream os) {
      if (os != null) {
         try {
            os.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   
  
}