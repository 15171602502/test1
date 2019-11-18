package com.ysk.kxt.sourceUit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

public class JBarcodeUit implements Printable {

	static final int fontSize = 11; //pixels
	static final int titleFontSize = 13; //pixels
	static final int padding = 6;
	static final int imageWidth = 160;
	static final int imageHeight = 110;

	private String barcode;
	private String addTime;
	private String title;

	public JBarcodeUit(String barcode,String addTime,String title) {
		this.barcode = barcode;
		this.addTime = addTime;
		this.title = title;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) g;
		try {
			initGraphics2D(g2d);
		} catch (InvalidAtributeException e) {
			e.printStackTrace();
		}
		return PAGE_EXISTS;
	}

	private void initGraphics2D(Graphics2D g2d) throws InvalidAtributeException {
		JBarcode jbcode = new JBarcode(Code128Encoder.getInstance(),
				WidthCodedPainter.getInstance(), BaseLineTextPainter
						.getInstance());
		jbcode.setShowCheckDigit(false);
		jbcode.setBarHeight(8);
		jbcode.setShowText(false);
        BufferedImage symbol = jbcode.createBarcode(barcode);
		int lineHeight = (int) (fontSize * 1.4);
		
		Font font = new Font("宋体", Font.PLAIN, fontSize);
		String[] paramArr = new String[] { "编号:" + barcode, "打印时间:",
				addTime };

		g2d.setBackground(Color.white);
		g2d.setColor(Color.black);
		g2d.setFont(font);
		g2d.setStroke(new BasicStroke(0.1f));
		g2d.clearRect(0, 0, imageWidth, imageHeight);

        Font titleFont = new Font("宋体", Font.BOLD, titleFontSize);
        int titleWidth = g2d.getFontMetrics(titleFont).stringWidth(title);
        g2d.setFont(titleFont);
        g2d.drawString(title, (imageWidth - titleWidth)/2, padding * 4);

        //Place the barcode symbol
        int symbolX = (imageWidth - symbol.getWidth())/2;
        int symbolY = padding*2 + (int)(titleFontSize * 1.4);
		g2d.drawImage(symbol, symbolX, symbolY, null);

        //Add text lines (or anything else you might want to add)
        int y = symbol.getHeight() + symbolY;
        g2d.setFont(font);
        for (int i = 0; i < paramArr.length; i++) {
            String line = paramArr[i];
            y += lineHeight;
            g2d.drawString(line, 10, y);
        }
        g2d.dispose();
	}

	private void createBarcode(File outputFile) throws IOException, InvalidAtributeException {
		BufferedImage bitmap = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g2d = (Graphics2D) bitmap.getGraphics();
		initGraphics2D(g2d);

		// Encode bitmap as file
		OutputStream out = new FileOutputStream(outputFile);
		try {
			ImageUtil.encodeAndWrite(bitmap, ImageUtil.PNG, out);
		} finally {
			out.close();
		}
	}

	
	
	
	public String getBarCode(String barcode,String addTime,String title){
		String flag=null;
		if(barcode!=null&&barcode!=""){
			String imgPath="/barCode/" + barcode + ".png";
			try {
				JBarcodeUit app = new JBarcodeUit(barcode, addTime, title);
				app.createBarcode(new File("C:/gene"+imgPath));
				flag=  imgPath;
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}else{
			flag=null;
		}
		return flag;
	}
	public static void main(String[] args) {
		
		try {
			JBarcodeUit app = new JBarcodeUit("9420618060001291425", "", "");
            app.createBarcode(new File("D:/gene/barCode/" + 1231313131 + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
	}
}