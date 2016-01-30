package com.leo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class XwpfTest {
	public static void main(String[] args) {

		for (int i = 1; i < 10; i++) {
			try {
				// SimpleWrite("第 "+i+" 份考卷");
				SimpleWrite(i + "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 基本的写操作
	 * 
	 * @throws Exception
	 */
	public static void SimpleWrite(String file_name) throws Exception {
		// 新建一个文档
		XWPFDocument doc = new XWPFDocument();
		// 创建一个段落

		XWPFParagraph para = doc.createParagraph();
		para.setAlignment(ParagraphAlignment.CENTER);

		// 一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run = para.createRun();
		run.setBold(true); // 加粗
		run.setText("飞行安全差错标准");

		run.setFontSize(20);
		run.addBreak();

		XWPFParagraph para1 = doc.createParagraph();
		para1.setAlignment(ParagraphAlignment.LEFT);
		// para1.setBorderBottom(Borders.DOUBLE);
		// para1.setBorderTop(Borders.DOUBLE);
		// para1.setBorderRight(Borders.DOUBLE);
		// para1.setBorderLeft(Borders.DOUBLE);
		// para1.setBorderBetween(Borders.SINGLE);
		para1.setVerticalAlignment(TextAlignment.BASELINE);
		para1.setSpacingAfterLines(20);

		XWPFRun run1 = para1.createRun();
		run1.setFontSize(14);
		run1 = para1.createRun();
		run1.setColor("000000");
		// run1.setText("李耀华");

		ExcelOperate data = new ExcelOperate();
		ArrayList<String> list = data.getDataList();

		int count = 0;
		boolean isStop = false;
		ArrayList<Integer> data_Seq = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			int temp = (int) (Math.random() * 100);
			if (temp < 74 && !data_Seq.contains(temp) && !isStop) {
				// System.out.println(temp+" | "+count);
				data_Seq.add(temp);
				if (data_Seq.size() == 30) {
					isStop = true;
				}
				++count;
			}
		}
		System.out.println("data_Seq Size: " + data_Seq.size());

		int question_num = 1;
		for (int i = 1; i < list.size(); i++) {

			String[] a = list.get(i).split("~");

			if (data_Seq.contains(Integer.parseInt(a[0]))) {
				System.out.println(question_num + ", " + a[1].trim());
				// run1.setText(question_num+", "+a[1].trim()+"\r\n");
				run1.setText(question_num + ", " + a[1].trim());
				run1.addBreak();
				question_num++;
			}

		}

		OutputStream os = new FileOutputStream(RandomTest_UI.textField_export_path.getText()+"\\" + file_name
				+ ".docx");
		// 把doc输出到输出流
		doc.write(os);
		close(os);
	}

	/***
	 * 写一个表格
	 * 
	 * @throws Exception
	 */
	public void testWriteTable() throws Exception {
		XWPFDocument doc = new XWPFDocument();
		// 创建一个5行5列的表格
		XWPFTable table = doc.createTable(5, 5);
		// 这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。
		// table.addNewCol(); //给表格增加一列，变成6列
		table.createRow(); // 给表格新增一行，变成6行
		List<XWPFTableRow> rows = table.getRows();
		// 表格属性
		CTTblPr tablePr = table.getCTTbl().addNewTblPr();
		// 表格宽度
		CTTblWidth width = tablePr.addNewTblW();
		width.setW(BigInteger.valueOf(8000));
		XWPFTableRow row;
		List<XWPFTableCell> cells;
		XWPFTableCell cell;
		int rowSize = rows.size();
		int cellSize;
		for (int i = 0; i < rowSize; i++) {
			row = rows.get(i);
			// 新增单元格
			row.addNewTableCell();
			// 设置行的高度
			row.setHeight(500);
			// 行属性
			// CTTrPr rowPr = row.getCtRow().addNewTrPr();
			// 这种方式是可以获取到新增的cell的。
			// List<CTTc> list = row.getCtRow().getTcList();
			cells = row.getTableCells();
			cellSize = cells.size();
			for (int j = 0; j < cellSize; j++) {
				cell = cells.get(j);
				if ((i + j) % 2 == 0) {
					// 设置单元格的颜色
					cell.setColor("ff0000"); // 红色
				} else {
					cell.setColor("0000ff"); // 蓝色
				}
				// 单元格属性
				CTTcPr cellPr = cell.getCTTc().addNewTcPr();
				cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
				if (j == 3) {
					// 设置宽度
					cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
				}
				cell.setText(i + ", " + j);
			}
		}
		// 文件不存在时会自动创建
		OutputStream os = new FileOutputStream("D:\\table.docx");
		// 写入文件
		doc.write(os);
		this.close(os);
	}

	/**
	 * 关闭输出流
	 * 
	 * @param os
	 */
	private static void close(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}