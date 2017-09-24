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
    * ��һ��docx�ĵ���Ϊģ�壬Ȼ���滻���е����ݣ���д��Ŀ���ĵ��С�
    * @throws Exception
    */
   public String[] testTemplateWrite(Map<String, Object> params,String filename) throws Exception {
//////////////////////////////////////////////////////////////
      ////////////////////////////////////////////////////////////////
	   String filePath;
	   System.out.println("����ǽ�β�𣿣���");
	  
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
      //�滻��������ı���
      this.replaceInPara(doc, params);
      System.out.println("��ʼ��null");
      this.replacenull(doc);
      System.out.println("null������");
      //�滻�������ı���
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
    * �滻��������ı���
    * @param doc Ҫ�滻���ĵ�
    * @param params ����
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
    * �滻��������ı���
    * @param para Ҫ�滻�Ķ���
    * @param params ����
    */
   private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
      List<XWPFRun> runs;
      Matcher matcher;
      System.out.println("//////////////////////////////kaishi");
      System.out.println(para.getParagraphText());
      if (this.matcher(para.getParagraphText()).find())
      {
    	  System.out.println("�ɹ�ƴ�䵽�˱��ʽ");
      }
      System.out.println("//////////////////////////////����");
      if (this.matcher(para.getParagraphText()).find()) {/////////////////////////////////
         runs = para.getRuns();
         for (int i=0; i<runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String runText = run.toString();
            matcher = this.matcher(runText);
            System.out.println("���ڵ�runText�ǣ�"+runText);
            if (matcher.find()) {
            	
                while ((matcher = this.matcher(runText)).find()) {
                	System.out.println("�ҵ���runtext----"+String.valueOf(params.get(matcher.group(1))));
                   runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                }
                //ֱ�ӵ���XWPFRun��setText()���������ı�ʱ���ڵײ�����´���һ��XWPFRun�����ı������ڵ�ǰ�ı����棬
                //�������ǲ���ֱ����ֵ����Ҫ��ɾ����ǰrun,Ȼ�����Լ��ֶ�����һ���µ�run��
                para.removeRun(i);
                XWPFRun now=para.insertNewRun(i);
                
                now.setText(runText);
                now.setFontSize(12);
                
            }
         }
      }
   }
  
   /**
    * �滻�������ı���
    * @param doc Ҫ�滻���ĵ�
    * @param params ����
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
	                          System.out.println("���ڵ�runText�ǣ�"+runText);
	                          if (matchers.find()) {
	                          	
	                              while ((matchers = this.matchers(runText)).find()) {
	                              	System.out.println("�滻null");
	                              }
	                              //ֱ�ӵ���XWPFRun��setText()���������ı�ʱ���ڵײ�����´���һ��XWPFRun�����ı������ڵ�ǰ�ı����棬
	                              //�������ǲ���ֱ����ֵ����Ҫ��ɾ����ǰrun,Ȼ�����Լ��ֶ�����һ���µ�run��
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
    * ����ƥ���ַ���
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
    * �ر�������
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
    * �ر������
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