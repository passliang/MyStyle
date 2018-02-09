/********************** 版权声明 *************************
 * 文件名: TraceSourceReport.java
 * 包名: biz
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月29日 上午11:13:18
 * 文件版本：V1.0 
 *
 *******************************************************/
package biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.html.table.Table;

/** 
 * @类名: biz.TraceSourceReport.java 
 * @职责说明: 溯源分析采购来源 导出
 * @创建者: peijd
 * @创建时间: 2016年7月29日 上午11:13:18
 */
public class TraceSourceReport {
	
	//标题样式
	public static Font titleFont = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 16, Font.BOLD);
	//段落标题样式
	public static Font pargTitleFont = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 14, Font.BOLD);
	//二级标题样式
	public static Font secdTitleFont = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 12, Font.BOLD);
	//表格标题样式
	public static Font tableTitleFont = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 11, Font.BOLD);
	//表格内容样式
	public static Font tableGridFont = FontFactory.getFont("resources/fonts/DENG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 11);
	
	//段落间距-前
	public static int paragSpaceBefore = 20;
	//段落间距-后
	public static int paragSpaceAfter = 10;
	
	public static void main(String[] args) throws DocumentException, IOException {
		createPdf("C:/r/P009_ZJU_NKH/","lz","hz");
	}
	
	//String projectPath 项目路径名称
	public static void createPdf(String projectPath,String tester,String company){
		try {
			
			//参数信息
			String paramPath=projectPath+"statistics/Summary_table.csv";//参数信息表格
			String contentPath= projectPath+"SampleInfo.csv";
	        String images1= projectPath+"statistics/"+"MetaboliteClass_Pieplot.png";
	        String images2=projectPath+"statistics/"+"PCA_QCs.png";
	        String images3=projectPath+"statistics/"+"PCA_1D_Scores_withQC.png";
	        String images4=projectPath+"statistics/"+"PCA_Samples.png";
	        String images5=projectPath+"statistics/"+"Clustering_z-score.png";
	        //开始创建文档
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/traceSource1.pdf"));
			//writer.setPageEvent(new TraceWatermark());
			writer.setStrictImageSequence(true);
			document.open();
			
			//设置标题
			Paragraph title = new Paragraph("AutoGC", titleFont);
			//段落后间距
			title.setSpacingAfter(5);
			//居中对齐
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			Paragraph subTitle = new  Paragraph("数据分析报告", titleFont);
			subTitle.setAlignment(Element.ALIGN_CENTER);
			document.add(subTitle);
			Paragraph subTitle2 = new  Paragraph("（中文版）", titleFont);
			subTitle2.setAlignment(Element.ALIGN_CENTER);
			document.add(subTitle2);
			Paragraph subTitle3 = new  Paragraph("测试者:"+tester, titleFont);
			subTitle3.setAlignment(Element.ALIGN_CENTER);
			document.add(subTitle3);
			Paragraph subTitle4 = new  Paragraph("单位:"+company, titleFont);
			subTitle4.setAlignment(Element.ALIGN_CENTER);
			document.add(subTitle4);
			Paragraph subTitle5 = new  Paragraph(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), titleFont);
			subTitle5.setAlignment(Element.ALIGN_CENTER);
			document.add(subTitle5);
			
			document.newPage();
			//测试样本信息总结 正文开始
			Paragraph subTitle6 = new Paragraph("测试样本信息总结", pargTitleFont);
			subTitle6.setSpacingBefore(paragSpaceBefore);
			subTitle6.setSpacingAfter(paragSpaceAfter);
			subTitle6.setAlignment(Element.ALIGN_CENTER);
			document.add(subTitle6);
			//本项目共包括19个测试样本（Sample）以及3个质量控制样本（QC），具体信息总结如下（只显示前40，详见表格SampleInfo.csv）
			Paragraph subTitle7 = new Paragraph("本项目共包括"+getFixParams("Sample_count", paramPath)+"个测试样本（Sample）以及"+getFixParams("QC_count", paramPath)+"个质量控制样本（QC），具体信息总结如下（只显示前15，详见表格SampleInfo.csv）", secdTitleFont);
			subTitle7.setSpacingBefore(paragSpaceBefore);
			subTitle7.setSpacingAfter(paragSpaceAfter);
			document.add(subTitle7);
			//表格信息
			createCsv(document,5,contentPath);
			document.newPage();
			//鉴定代谢物总结
			Paragraph jd = new Paragraph("鉴定代谢物总结", pargTitleFont);
			jd.setSpacingBefore(paragSpaceBefore);
			jd.setSpacingAfter(paragSpaceAfter);
			jd.setAlignment(Element.ALIGN_CENTER);
			document.add(jd);
			
			//采用基于硅烷化衍生的气相色谱时间飞行质谱联用 GC-TOFMS (LECO Corp., St.Joseph, MI, USA) 作为无靶向代谢组学的分析平台。根据内部开发的内源性代谢物数据库 JIALIB，我们总共鉴定 98 个代谢物，分类如下图。并且，根据代谢通路上下游关系，共计算得到 25 代谢物比值。
			Paragraph jdms = new Paragraph("采用基于硅烷化衍生的气相色谱时间飞行质谱联用 GC-TOFMS (LECO Corp., St.Joseph, MI, USA) 作为无靶向代谢组学的分析平台。根据内部开发的内源性代谢物数据库 JIALIB，我们总共鉴定 "+getFixParams("iden_num", paramPath)+" 个代谢物，分类如下图。并且，根据代谢通路上下游关系，共计算得到 "+getFixParams("ratio_num", paramPath)+" 代谢物比值。", secdTitleFont);
			jdms.setSpacingBefore(paragSpaceBefore);
			jdms.setSpacingAfter(paragSpaceAfter);
			document.add(jdms);
			
			//第一个图
			Image image1 = Image.getInstance(images1);
			//设置图片大小为40%
			image1.scalePercent(30f);
			//image1.set
			document.add(image1);
			System.out.println("iamge:"+images1);
			
			//QC 样 本 稳 定性考察
			
			Paragraph qc = new Paragraph("QC 样 本 稳 定性考察", pargTitleFont);
			qc.setSpacingBefore(paragSpaceBefore);
			qc.setSpacingAfter(paragSpaceAfter);
			qc.setAlignment(Element.ALIGN_CENTER);
			document.add(qc);
			
			//本项目在样本分析过程中，前后一共检测了 3 个 QC 样本，可以用来客观地评价大规模样本批次内的重复性，还可以矫正批间的误差。QC 样本以及与测试样本的PCA 分析如下：
			Paragraph qcfx = new Paragraph("本项目在样本分析过程中，前后一共检测了 "+getFixParams("QC_count", paramPath)+ "个 QC 样本，可以用来客观地评价大规模样本批次内的重复性，还可以矫正批间的误差。QC 样本以及与测试样本的PCA 分析如下：", secdTitleFont);
			qcfx.setSpacingBefore(paragSpaceBefore);
			qcfx.setSpacingAfter(paragSpaceAfter);
			document.add(qcfx);
			
			//第er个图
			Image image2 = Image.getInstance(images2);
			//设置图片大小为40%
			image2.scalePercent(60f);
			//image1.set
			document.add(image2);
			System.out.println("iamge:"+images2);
			
			//从 PCA 的第一个主成分 score plot，进一步观察 QC 样本以及测试样本的代谢分布以及相对稳定性：
			Paragraph pca = new Paragraph("从 PCA 的第一个主成分 score plot，进一步观察 QC 样本以及测试样本的代谢分布以及相对稳定性", secdTitleFont);
			pca.setSpacingBefore(paragSpaceBefore);
			pca.setSpacingAfter(paragSpaceAfter);
			document.add(pca);
			
			//图三
			Image image3 = Image.getInstance(images3);
			//设置图片大小为40%
			image3.scalePercent(60f);
			//image1.set
			document.add(image3);
			System.out.println("iamge:"+images3);
			
			document.newPage();
			//测试样 本无 监督 督 PCA 分析
			Paragraph pcajd = new Paragraph("测试样 本无 监督 督 PCA 分析", pargTitleFont);
			pcajd.setSpacingBefore(paragSpaceBefore);
			pcajd.setSpacingAfter(paragSpaceAfter);
			pcajd.setAlignment(Element.ALIGN_CENTER);
			document.add(pcajd);
			
			//本项目除了 QC 样本外，一共分析了 19 个测试样本，分别被分成 4 组。其 PCA 分析结果如下：
			Paragraph testyb = new Paragraph("本项目除了 QC 样本外，一共分析了 "+getFixParams("Sample_count", paramPath)+" 个测试样本，分别被分成"+getFixParams("Class_count", paramPath)+"组。其 PCA 分析结果如下：", secdTitleFont);
			testyb.setSpacingBefore(paragSpaceBefore);
			testyb.setSpacingAfter(paragSpaceAfter);
			document.add(testyb);
			//图四
			Image image4 = Image.getInstance(images4);
			//设置图片大小为40%
			image4.scalePercent(60f);
			//image1.set
			document.add(image4);
			
			document.newPage();
			//测试样本无监督 z z －e score 热图
			Paragraph zz = new Paragraph("测试样本无监督 z z －e score 热图", pargTitleFont);
			zz.setSpacingBefore(paragSpaceBefore);
			zz.setSpacingAfter(paragSpaceAfter);
			zz.setAlignment(Element.ALIGN_CENTER);
			document.add(zz);
			//这些样本代谢物的相对差异，可通过如下热图显示：
			Paragraph xdcy = new Paragraph("这些样本代谢物的相对差异，可通过如下热图显示：", secdTitleFont);
			xdcy.setSpacingBefore(paragSpaceBefore);
			xdcy.setSpacingAfter(paragSpaceAfter);
			document.add(xdcy);
			//图五
			Image image5 = Image.getInstance(images5);
			//设置图片大小为40%
			image5.scalePercent(60f);
			//image1.set
			document.add(image5);
			
			document.newPage();
			//动态添加内容
			//拼接参数信息表格位置
	    	
	    	//单维统计和多维 OPLS －A DA 分析两组比较
	    	Paragraph dad = new Paragraph("单维统计和多维 OPLS －A DA 分析两组比较", pargTitleFont);
	    	dad.setSpacingBefore(paragSpaceBefore);
	    	dad.setSpacingAfter(paragSpaceAfter);
	    	dad.setAlignment(Element.ALIGN_CENTER);
			document.add(dad);
	    	//分组信息
	    	String []group = null;
	    	if(new File(paramPath).exists()){
	    		System.out.println("参数位置："+paramPath);
	    		
	        	//获取所有的参数信息
	        	for(HashMap<String ,String >  param:getParams(paramPath)){
	        		String  groupCount=param.get("Group_Comp");//分组信息
	        		if(groupCount.length()>4){
	        			//分组信息>(1_2;) 
	        			group =groupCount.split(";");
	        			for(int i=0;i<group.length;i++){
	        				 String g=group[i];
	        				 //参数路径拼接 OPLSDA_1_2.pdf /UniTest_1_2_Vplot.png/Ttest_1_2.csv/TopBoxplot_1_2.pdf/Utest_1_2.csv/ Pathway_1_2.csv
	        				 String Figure6path=projectPath+"statistics/"+"OPLSDA_"+g+".png";
	        				 String Figure7path=projectPath+"statistics/"+"UniTest_"+g+"_Vplot.png";
	        				 String Figure8path=projectPath+"statistics/"+"TopBoxplot_"+g+".png";
	        				 String excel1path=projectPath+"statistics/"+"Ttest_"+g+".csv";
	        				 String excel2path=projectPath+"statistics/"+"Utest_"+g+".csv";
	        				 String excel3path=projectPath+"statistics/"+"Pathway_"+g+".csv";
	        				 System.out.println("第一组图位置："+Figure6path+"第二组图的位置： "
	        				 +Figure7path+"第三组图的位置： "+Figure8path+"第一个表格位置："+
	        				excel1path+"第二个表格位置： "+excel2path+"第三个表格位置： "+excel3path);
	        				String [] a = g.split("_");//按_截取数据
	        				if(Integer.parseInt(a[0])< Integer.parseInt(a[1])){
	        					//循环写书 比较名称
	        					Paragraph pg1 = new Paragraph("Class"+a[0]+"和Class"+a[1]+"比较", secdTitleFont);
	        					pg1.setSpacingBefore(paragSpaceBefore);
	        					pg1.setSpacingAfter(paragSpaceAfter);
	        					pg1.setAlignment(Element.ALIGN_CENTER);
	        					document.add(pg1);
	            			}else{
	            				
	            				Paragraph pg2 = new Paragraph("Class"+a[1]+"和Class"+a[0]+"比较", secdTitleFont);
	        					pg2.setSpacingBefore(paragSpaceBefore);
	        					pg2.setSpacingAfter(paragSpaceAfter);
	        					pg2.setAlignment(Element.ALIGN_CENTER);
	        					document.add(pg2);
	            			}
	        				
	        				String [] counts=param.get("MetPath_Sig_count").split(";");//代谢物数量
	        				String [] sigCount=param.get("Total_Sig_count").split(";");//代谢通道数量
	        				String [] Ttest_Sig_count=param.get("Ttest_Sig_count").split(";");//t -test 物质差异性数量
	        				//U test 物质差异性数量
	    					String [] Utest_Sig_count=param.get("Utest_Sig_count").split(";");
	    					//OPLS-DA 数量
	    					String [] PLSDA_Sig_count=param.get("PLSDA_Sig_count").split(";");
	    					
	        				/*OPLS-DA 模型分析，共有 15 个代谢物具有差异性（VIP 大于 1 且|weight| > 0.1）。
	        				如下图，score plot 显示组别间差异：*/
	        				Paragraph OPLS = new Paragraph("通过正交偏最小二乘法判别分析(OPLS-DA) 模型分析，共发现"+counts[i]+"个代谢物具有差异性（VIP 大于 1 且|weight| > 0.1）。下图分别是:（左上）score plot显示组别差异;(右上)模型参数总结;(左下)permutation检测结果：(右下)多维V-plot 显示差异性物质", secdTitleFont);
	        				OPLS.setSpacingBefore(paragSpaceBefore);
	        				OPLS.setSpacingAfter(paragSpaceAfter);
        					document.add(OPLS);
        					//循环图1
        					Image Figure6 = Image.getInstance(Figure6path);
        					//设置图片大小为40%
        					Figure6.scalePercent(30f);
        					//image1.set
        					document.add(Figure6);
        					
        					document.newPage();
        					//同时采用单维统计参数 student t test 和非参数 Mann Whitney U test 分析。根据差异性分析标准： p<0.05, FC > 1.5 or FC<-1.5，用火山图总结如下：
        					Paragraph dwtj = new Paragraph("同时采用单维统计参数 student t test 和非参数 Mann Whitney U test 分析,经一步检验代谢物在组间差异。检测标准： p<0.05, FC > 1.5 or FC<-1.5。下面的火山图分别总结了t-test 和u-test 的检测得到的差异性物质。", secdTitleFont);
        					dwtj.setSpacingBefore(paragSpaceBefore);
        					dwtj.setSpacingAfter(paragSpaceAfter);
        					document.add(dwtj);
        					//循环图2
        					Image Figure7 = Image.getInstance(Figure7path);
        					//设置图片大小为40%
        					Figure7.scalePercent(100f);
        					//image1.set
        					document.add(Figure7);
        					document.newPage();
        					//如上图所示，单维 student T-test 统计分析共 得到 7 个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Ttest_1_2.csv）：
        					Paragraph dw = new Paragraph("如上图所示，单维 student T-test 统计分析共 得到 "+Ttest_Sig_count[i]+" 个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Ttest_1_2.csv）：", secdTitleFont);
        					dw.setSpacingBefore(paragSpaceBefore);
        					dw.setSpacingAfter(paragSpaceAfter);
        					document.add(dw);
        					//表格图
        					createCsv(document,4,excel1path);
        					document.newPage();
        					//其中挑选前 6 个差异性代谢物， box plot 结果显示如下：
        					Paragraph cyx = new Paragraph("其中挑选前 6 个差异性代谢物， box plot 结果显示如下", secdTitleFont);
        					cyx.setSpacingBefore(paragSpaceBefore);
        					cyx.setSpacingAfter(paragSpaceAfter);
        					document.add(cyx);
        					//循环图2
        					Image Figure8 = Image.getInstance(Figure8path);
        					//设置图片大小为40%
        					Figure8.scalePercent(60f);
        					//image1.set
        					document.add(Figure8);
        					document.newPage();
        					//同样，单维 Mann Whitney U-test 统计分析共 得到 8 个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Utest_1_2.csv）：
        					Paragraph uTest = new Paragraph("同样，单维 Mann Whitney U-test 统计分析共 得到"+Utest_Sig_count[i]+"个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Utest_1_2.csv）：", secdTitleFont);
        					uTest.setSpacingBefore(paragSpaceBefore);
        					uTest.setSpacingAfter(paragSpaceAfter);
        					document.add(uTest);
        					createCsv(document,4,excel2path);
        					/*合并单维统计（p<0.05) 和多维统计(VIP>1) 结果, 发现共有 23 个代谢物具有统
        					计学差异，具体详见表格 Diff_1_2.csv.*/
        					document.newPage();
        					Paragraph hbdw = new Paragraph("合并单维统计（p<0.05) 和多维统计(VIP>1) 结果, 发现共有 "+PLSDA_Sig_count[i]+" 个代谢物具有统计学差异，具体详见表格 Diff_1_2.csv。进一步对这些差异性代谢物进行代谢通道富集分析，发现共有"+sigCount[i]+"条相关代谢通道有显著变化，值得进一步生化机制研究。前5个差异性代谢通道总结如下，详见表格Pathway_1_2.csv：", secdTitleFont);
        					hbdw.setSpacingBefore(paragSpaceBefore);
        					hbdw.setSpacingAfter(paragSpaceAfter);
        					document.add(hbdw);
        					/*进一步对这些差异性代谢物进行代谢通道富集分析，发现共有 6 条相关代谢通道有
        					显著变化，值得进一步生化机制研究。其差异性代谢通道总结如下，详见表格
        					Pathway_1_2.csv：*/
        					createCsv(document,5,excel3path);
	        			}
	        			
	        		}else{
	        			//如果只存在一组数据  1_2 
	        			if(groupCount.contains(";")){
	        				//如果包含;则替换为 空
	        				groupCount=groupCount.replaceAll(";", "");
	        			}
	        			
	        			String []groups=groupCount.split("_");
	        			 String Figure6path=projectPath+"statistics/"+"OPLSDA_1_2.png";
	    				 String Figure7path=projectPath+"statistics/"+"UniTest_1_2_Vplot.png";
	    				 String Figure8path=projectPath+"statistics/"+"TopBoxplot_1_2.png";
	    				 String excel1path=projectPath+"statistics/"+"Ttest_1_2.csv";
	    				 String excel2path=projectPath+"statistics/"+"Utest_1_2.csv";
	    				 String excel3path=projectPath+"statistics/"+"Pathway_1_2.csv";
        					//循环写书 比较名称
    					Paragraph pg1 = new Paragraph("Class1"+"和Class2比较", secdTitleFont);
    					pg1.setSpacingBefore(paragSpaceBefore);
    					pg1.setSpacingAfter(paragSpaceAfter);
    					pg1.setAlignment(Element.ALIGN_CENTER);
    					document.add(pg1);
        				
        				String  counts=param.get("MetPath_Sig_count");//代谢物数量
        				String  sigCount=param.get("Total_Sig_count");//代谢通道数量
        				String  Ttest_Sig_count=param.get("Ttest_Sig_count");//t -test 物质差异性数量
        				//U test 物质差异性数量
    					String  Utest_Sig_count=param.get("Utest_Sig_count");
    					//OPLS-DA 数量
    					String  PLSDA_Sig_count=param.get("PLSDA_Sig_count");
    					
        				/*OPLS-DA 模型分析，共有 15 个代谢物具有差异性（VIP 大于 1 且|weight| > 0.1）。
        				如下图，score plot 显示组别间差异：*/
        				Paragraph OPLS = new Paragraph("通过正交偏最小二乘法判别分析(OPLS-DA) 模型分析，共发现"+counts+"个代谢物具有差异性（VIP 大于 1 且|weight| > 0.1）。下图分别是:（左上）score plot显示组别差异;(右上)模型参数总结;(左下)permutation检测结果：(右下)多维V-plot 显示差异性物质", secdTitleFont);
        				OPLS.setSpacingBefore(paragSpaceBefore);
        				OPLS.setSpacingAfter(paragSpaceAfter);
    					document.add(OPLS);
    					//循环图1
    					Image Figure6 = Image.getInstance(Figure6path);
    					//设置图片大小为40%
    					Figure6.scalePercent(30f);
    					//image1.set
    					document.add(Figure6);
    					
    					document.newPage();
    					//同时采用单维统计参数 student t test 和非参数 Mann Whitney U test 分析。根据差异性分析标准： p<0.05, FC > 1.5 or FC<-1.5，用火山图总结如下：
    					Paragraph dwtj = new Paragraph("同时采用单维统计参数 student t test 和非参数 Mann Whitney U test 分析,经一步检验代谢物在组间差异。检测标准： p<0.05, FC > 1.5 or FC<-1.5。下面的火山图分别总结了t-test 和u-test 的检测得到的差异性物质。", secdTitleFont);
    					dwtj.setSpacingBefore(paragSpaceBefore);
    					dwtj.setSpacingAfter(paragSpaceAfter);
    					document.add(dwtj);
    					//循环图2
    					Image Figure7 = Image.getInstance(Figure7path);
    					//设置图片大小为40%
    					Figure7.scalePercent(100f);
    					//image1.set
    					document.add(Figure7);
    					
    					document.newPage();
    					//如上图所示，单维 student T-test 统计分析共 得到 7 个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Ttest_1_2.csv）：
    					Paragraph dw = new Paragraph("如上图所示，单维 student T-test 统计分析共 得到 "+Ttest_Sig_count+" 个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Ttest_1_2.csv）：", secdTitleFont);
    					dw.setSpacingBefore(paragSpaceBefore);
    					dw.setSpacingAfter(paragSpaceAfter);
    					document.add(dw);
    					//表格图
    					document.newPage();
    					createCsv(document,4,excel1path);
    					//其中挑选前 6 个差异性代谢物， box plot 结果显示如下：
    					document.newPage();
    					Paragraph cyx = new Paragraph("其中挑选前 6 个差异性代谢物， box plot 结果显示如下", secdTitleFont);
    					cyx.setSpacingBefore(paragSpaceBefore);
    					cyx.setSpacingAfter(paragSpaceAfter);
    					document.add(cyx);
    					//循环图2
    					Image Figure8 = Image.getInstance(Figure8path);
    					//设置图片大小为40%
    					Figure8.scalePercent(60f);
    					//image1.set
    					document.add(Figure8);
    					document.newPage();
    					//同样，单维 Mann Whitney U-test 统计分析共 得到 8 个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Utest_1_2.csv）：
    					Paragraph uTest = new Paragraph("同样，单维 Mann Whitney U-test 统计分析共 得到"+Utest_Sig_count+"个代谢物具有显著性差异（p<0.05)。举例如下（详见表格 Utest_1_2.csv）：", secdTitleFont);
    					uTest.setSpacingBefore(paragSpaceBefore);
    					uTest.setSpacingAfter(paragSpaceAfter);
    					document.add(uTest);
    					createCsv(document,4,excel2path);
    					/*合并单维统计（p<0.05) 和多维统计(VIP>1) 结果, 发现共有 23 个代谢物具有统
    					计学差异，具体详见表格 Diff_1_2.csv.*/
    					/*进一步对这些差异性代谢物进行代谢通道富集分析，发现共有 6 条相关代谢通道有
    					显著变化，值得进一步生化机制研究。其差异性代谢通道总结如下，详见表格
    					Pathway_1_2.csv：*/
    					document.newPage();
    					Paragraph jyb = new Paragraph("合并单维统计（p<0.05) 和多维统计(VIP>1) 结果, 发现共有 "+PLSDA_Sig_count+" 个代谢物具有统计学差异，具体详见表格 Diff_1_2.csv。进一步对这些差异性代谢物进行代谢通道富集分析，发现共有"+sigCount+"条相关代谢通道有显著变化，值得进一步生化机制研究。前5个差异性代谢通道总结如下，详见表格Pathway_1_2.csv：", secdTitleFont);
    					jyb.setSpacingBefore(paragSpaceBefore);
    					jyb.setSpacingAfter(paragSpaceAfter);
    					document.add(jyb);
    					createCsv(document,5,excel3path);
	        		}
	        	}}
			
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("export over!");
		
	}
	/**
	 * @方法名称: getGridHeaderCell 
	 * @实现功能: 添加grid标题单元格
	 * @param content
	 * @return
	 * @create by peijd at 2016年7月29日 下午2:02:50
	 */
	public static PdfPCell getGridHeaderCell(String content){
		PdfPCell headerCell = new PdfPCell();
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        headerCell.setPadding(4);
        headerCell.addElement(new Phrase(content, tableTitleFont));
        return headerCell;
	}
	
	/**
	 * @方法名称: getGridContentCell 
	 * @实现功能: 添加grid内容单元格
	 * @param content
	 * @return
	 * @create by peijd at 2016年7月29日 下午2:04:19
	 */
	public static PdfPCell getGridContentCell(String content){
		PdfPCell headerCell = new PdfPCell();
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
		headerCell.setPadding(4);
		headerCell.addElement(new Phrase(content, tableGridFont));
		return headerCell;
	}
	
	 /**
     * CSV(word中表格属性)数据获取list
     * 读取内容跳过0行  只要20行
     * @param filePath
     * @return
     */
    public static List<String[]>  getListFromExcel(String filePath){
    	CsvReader cr=null;
    	List<String []> data=new ArrayList<String []>();
    	try {
	    		cr=new CsvReader(filePath, ',',Charset.forName("GBK"));
	    		//int count=0;
	    		int raw = 0;
	    		while(cr.readRecord()){
	    			 String [] args=cr.getValues();
	    			//if(count++==0)continue;
	    			data.add(args);
	    			if(raw++==15)break;
	    		} 
	    	
    		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;  
    }
    
    /**
     *  获取到所有的参数信息
     * @param filePath 参数表格信息位置
     * @return
     */
    public static List< HashMap<String ,String >> getParams(String filePath){
    	CsvReader cr=null;
    	List<HashMap<String, String>>params=new ArrayList<HashMap<String,String >>();
    	HashMap<String ,String >param=new HashMap<String ,String>();
    	try {
			cr=new CsvReader(filePath, ',',Charset.forName("GBK"));
			while(cr.readRecord()){
				String [] args=cr.getValues();
				param.put(args[0], args[1]);
			}
			params.add(param);	
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return params;
    }
    
    
    /**
     * 根据参数名获取参数信息
     * 
     */
    public static String  getFixParams(String paramName,String filePath){
    	CsvReader cr=null;
    	String result=null;
    	HashMap<String ,String >param=new HashMap<String ,String>();
    	try {
			cr=new CsvReader(filePath, ',',Charset.forName("GBK"));
			while(cr.readRecord()){
				String [] args=cr.getValues();
				param.put(args[0], args[1]);
			}
			 result=param.get(paramName);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    /**
     * 创建表格 及相关的属性配置
     * @param document
     */
    public static void createCsv(Document document,int colmns,String filePath){
    	
    	try {
			PdfPTable table1 = new PdfPTable(colmns);
			//table1.setHeaderRows(1);
			if(colmns==5){
			
				table1.setWidths(new int[] {6, 2, 2, 3, 2 });
			}else if(colmns==4){
				table1.setWidths(new int[] {4, 3, 2, 2 });
			}
			
			table1.setWidthPercentage(98);
			//table1.setSplitLate(false);
			List<String []>data=getListFromExcel(filePath);
			//循环生成表格数据
			for(int i=0;i<data.size();i++){
				if(i==0){
					for(int a =0 ;a<data.get(0).length;a++){
						table1.addCell(getGridHeaderCell(data.get(0)[a]));
					}
					
				}else{
					for(int a =0 ;a<data.get(i).length;a++){
						table1.addCell(getGridContentCell(data.get(i)[a]));
					}
					
				}
				
			}	
			
			document.add(table1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
