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
				// SimpleWrite("�� "+i+" �ݿ���");
				SimpleWrite(i + "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ������д����
	 * 
	 * @throws Exception
	 */
	public static void SimpleWrite(String file_name) throws Exception {
		// �½�һ���ĵ�
		XWPFDocument doc = new XWPFDocument();
		// ����һ������

		XWPFParagraph para = doc.createParagraph();
		para.setAlignment(ParagraphAlignment.CENTER);

		// һ��XWPFRun���������ͬ���Ե�һ������
		XWPFRun run = para.createRun();
		run.setBold(true); // �Ӵ�
		run.setText("���а�ȫ����׼");

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
		// run1.setText("��ҫ��");

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
		// ��doc����������
		doc.write(os);
		close(os);
	}

	/***
	 * дһ�����
	 * 
	 * @throws Exception
	 */
	public void testWriteTable() throws Exception {
		XWPFDocument doc = new XWPFDocument();
		// ����һ��5��5�еı��
		XWPFTable table = doc.createTable(5, 5);
		// �������ӵ���ԭ����ʼ����������5����ͨ��getTableCells()������ȡʱ��ȡ��������ͨ��row�����ľͿ��ԡ�
		// table.addNewCol(); //���������һ�У����6��
		table.createRow(); // ���������һ�У����6��
		List<XWPFTableRow> rows = table.getRows();
		// �������
		CTTblPr tablePr = table.getCTTbl().addNewTblPr();
		// �����
		CTTblWidth width = tablePr.addNewTblW();
		width.setW(BigInteger.valueOf(8000));
		XWPFTableRow row;
		List<XWPFTableCell> cells;
		XWPFTableCell cell;
		int rowSize = rows.size();
		int cellSize;
		for (int i = 0; i < rowSize; i++) {
			row = rows.get(i);
			// ������Ԫ��
			row.addNewTableCell();
			// �����еĸ߶�
			row.setHeight(500);
			// ������
			// CTTrPr rowPr = row.getCtRow().addNewTrPr();
			// ���ַ�ʽ�ǿ��Ի�ȡ��������cell�ġ�
			// List<CTTc> list = row.getCtRow().getTcList();
			cells = row.getTableCells();
			cellSize = cells.size();
			for (int j = 0; j < cellSize; j++) {
				cell = cells.get(j);
				if ((i + j) % 2 == 0) {
					// ���õ�Ԫ�����ɫ
					cell.setColor("ff0000"); // ��ɫ
				} else {
					cell.setColor("0000ff"); // ��ɫ
				}
				// ��Ԫ������
				CTTcPr cellPr = cell.getCTTc().addNewTcPr();
				cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
				if (j == 3) {
					// ���ÿ��
					cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
				}
				cell.setText(i + ", " + j);
			}
		}
		// �ļ�������ʱ���Զ�����
		OutputStream os = new FileOutputStream("D:\\table.docx");
		// д���ļ�
		doc.write(os);
		this.close(os);
	}

	/**
	 * �ر������
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