package com.own.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.own.utils.AppStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

@FunctionalInterface
public interface PdfTextWriter {
public  void write(String qualifiedFileName) throws FileNotFoundException ,DocumentException;
public abstract class PdfTextWriterImpl implements PdfTextWriter{
	private static final Logger log = LoggerFactory.getLogger(PdfTextWriter.PdfTextWriterImpl.class);
	final static String EXT=".pdf";
	final static String DEFAULT_NAME="default";

	@Override
	public void write(@NotNull final String qualifiedFileName) throws FileNotFoundException, DocumentException {
		log.info("creating Pdf file :{}",qualifiedFileName);
		try {
			Objects.requireNonNull(qualifiedFileName, "File name is null");
			AppStringUtils.isNullEmpty(qualifiedFileName);
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(qualifiedFileName+EXT));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello World", font);
			document.add(chunk);
			document.close();
			log.info("File  written succeded");
		} catch (FileNotFoundException  e) {
			e.printStackTrace();
			throw new FileNotFoundException(qualifiedFileName+" : is not found");
		}catch ( DocumentException e) {
			e.printStackTrace();
			throw new DocumentException(e.getMessage(),e);
		}
		
}
}
}
