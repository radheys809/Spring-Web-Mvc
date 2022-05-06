package com.own.utils;

import com.itextpdf.text.DocumentException;
import com.own.service.PdfTextWriter.PdfTextWriterImpl;

import java.io.FileNotFoundException;

public class CodeTester extends PdfTextWriterImpl {
    public static void main(String[] args) {

        CodeTester codeTester = new CodeTester();
        try {
            codeTester.write("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


}
