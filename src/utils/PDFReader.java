package utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {

	public static String getText(String filePath) {
		try (PDDocument pdfDocument = PDDocument.load(filePath)) {
			return new PDFTextStripper().getText(pdfDocument);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getText(InputStream stream) {
		try (PDDocument pdfDocument = PDDocument.load(stream)) {
			return new PDFTextStripper().getText(pdfDocument);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
