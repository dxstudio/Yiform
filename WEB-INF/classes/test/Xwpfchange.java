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

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class Xwpfchange {
	
	
	String school = null;
    String academy  = null;
    String Major  = null;
    String Class1  = null;
    String NewStudent = null;
    String name = null;
     String sex = null;
     String by=null;
     String bm=null;
     String bd=null;
     String nation=null;
     String IDCard=null;
     String PS =null;
     String RPR =null;
     String FN  =null;
public String getschool() {
		return school;
	}


	public void setschool(String school) {
		System.out.println("jinqvle");
		this.school = school;
	}


	public String getacademy() {
		return academy;
	}


	public void setacademy(String academy) {
		this.academy = academy;
	}


	public String getMajor() {
		return Major;
	}


	public void setMajor(String major) {
		this.Major = major;
	}


	public String getClass1() {
		return Class1;
	}


	public void setClass(String class1) {
		this.Class1 = class1;
	}


	public String getNewStudent() {
		return NewStudent;
	}


	public void setNewStudent(String newStudent) {
		this.NewStudent = newStudent;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBy() {
		return by;
	}


	public void setBy(String by) {
		this.by = by;
	}


	public String getBm() {
		return bm;
	}


	public void setBm(String bm) {
		this.bm = bm;
	}


	public String getBd() {
		return bd;
	}


	public void setBd(String bd) {
		this.bd = bd;
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getIDCard() {
		return IDCard;
	}


	public void setIDCard(String iDCard) {
		this.IDCard = iDCard;
	}


	public String getPS() {
		return PS;
	}


	public void setPS(String pS) {
		this.PS = pS;
	}


	public String getRPR() {
		return RPR;
	}


	public void setRPR(String rPR) {
		this.RPR = rPR;
	}


	public String getFN() {
		return FN;
	}


	public void setFN(String fN) {
		this.FN = fN;
	}


/**
    * 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
    * @throws Exception
    */
   public String testTemplateWrite() throws Exception {
     
	  
	   Map<String, Object> params = new HashMap<String, Object>();
      System.out.println("School:"+school);  
      System.out.println("里面sex:"+sex);
      System.out.println("1里面name:"+name);
      System.out.println("1里面academy:"+academy);
      System.out.println("1里面NewStudent:"+NewStudent);
      params.put("school", school);
      params.put("academy", academy);
      params.put("Major", Major);
      params.put("Class1", Class1);
      params.put("NewStudent", NewStudent);
      params.put("name", name);
      params.put("sex", sex);
      params.put("by", by);
      params.put("bm", bm);
      params.put("bd", bd);
      params.put("nation", nation);
      params.put("IDCard", IDCard);
      params.put("PS", PS);
      params.put("RPR", RPR);
      params.put("FN", FN);
      
      
      //System.out.print("身份证号是"+params.get("id"));
      ////////////////////////////////////////////////////////////////
      String filePath = "D:\\eclips_f\\yiban\\WebContent\\word\\1.docx";
      InputStream is = new FileInputStream(filePath);
      XWPFDocument doc = new XWPFDocument(is);
      //替换段落里面的变量
      this.replaceInPara(doc, params);
      //替换表格里面的变量
      this.replaceInTable(doc, params);
      System.out.println("111");
      OutputStream os = new FileOutputStream("D:\\eclips_f\\yiban\\WebContent\\word\\"+name+".docx");
      doc.write(os);
      this.close(os);
      this.close(is);
      return name;//.concat("_提头");
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
      if (this.matcher(para.getParagraphText()).find()) {
         runs = para.getRuns();
         for (int i=0; i<runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String runText = run.toString();
            matcher = this.matcher(runText);
            if (matcher.find()) {
                while ((matcher = this.matcher(runText)).find()) {
                   runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                }
                //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
                para.removeRun(i);
                para.insertNewRun(i).setText(runText);
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
   
  
  ///////////////////////////////////////////
// System.out.println("输入你的姓名");
// name = sc.nextLine();
// System.out.println("选择你的性别 a.男   b.女");
// sex = sc.nextLine();
// if(sex.equals("a"))
// {
//	   sex="新生□  在校生√";
// }else{
//	   sex="新生√  在校生□";
// }
// System.out.println(sex);
// System.out.println("输入你的身份证号");
// id = sc.nextLine();
// birth=id.substring(6, 14);
// System.out.println("选择你的民族 a.汉族  b.回族");
// aaa =sc.nextLine();
// if(aaa.equals("a"))
// {
//	   aaa="汉";
// }else{
//	   aaa="回";
// }
// System.out.println(aaa);
}