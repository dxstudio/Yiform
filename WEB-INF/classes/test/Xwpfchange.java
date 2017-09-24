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
    * ��һ��docx�ĵ���Ϊģ�壬Ȼ���滻���е����ݣ���д��Ŀ���ĵ��С�
    * @throws Exception
    */
   public String testTemplateWrite() throws Exception {
     
	  
	   Map<String, Object> params = new HashMap<String, Object>();
      System.out.println("School:"+school);  
      System.out.println("����sex:"+sex);
      System.out.println("1����name:"+name);
      System.out.println("1����academy:"+academy);
      System.out.println("1����NewStudent:"+NewStudent);
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
      
      
      //System.out.print("���֤����"+params.get("id"));
      ////////////////////////////////////////////////////////////////
      String filePath = "D:\\eclips_f\\yiban\\WebContent\\word\\1.docx";
      InputStream is = new FileInputStream(filePath);
      XWPFDocument doc = new XWPFDocument(is);
      //�滻��������ı���
      this.replaceInPara(doc, params);
      //�滻�������ı���
      this.replaceInTable(doc, params);
      System.out.println("111");
      OutputStream os = new FileOutputStream("D:\\eclips_f\\yiban\\WebContent\\word\\"+name+".docx");
      doc.write(os);
      this.close(os);
      this.close(is);
      return name;//.concat("_��ͷ");
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
                //ֱ�ӵ���XWPFRun��setText()���������ı�ʱ���ڵײ�����´���һ��XWPFRun�����ı������ڵ�ǰ�ı����棬
                //�������ǲ���ֱ����ֵ����Ҫ��ɾ����ǰrun,Ȼ�����Լ��ֶ�����һ���µ�run��
                para.removeRun(i);
                para.insertNewRun(i).setText(runText);
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
   
  
  ///////////////////////////////////////////
// System.out.println("�����������");
// name = sc.nextLine();
// System.out.println("ѡ������Ա� a.��   b.Ů");
// sex = sc.nextLine();
// if(sex.equals("a"))
// {
//	   sex="������  ��У����";
// }else{
//	   sex="������  ��У����";
// }
// System.out.println(sex);
// System.out.println("����������֤��");
// id = sc.nextLine();
// birth=id.substring(6, 14);
// System.out.println("ѡ��������� a.����  b.����");
// aaa =sc.nextLine();
// if(aaa.equals("a"))
// {
//	   aaa="��";
// }else{
//	   aaa="��";
// }
// System.out.println(aaa);
}