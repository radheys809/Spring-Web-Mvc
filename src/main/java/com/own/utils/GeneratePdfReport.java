package com.own.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.own.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Slf4j
public class GeneratePdfReport {
    public static ByteArrayInputStream generateUserReport(@NotNull @NotBlank List<User> users) {
        log.info("Generating User report in pdf");
        Assert.notNull(users, "List must not be null");
        Assert.notEmpty(users, "List must not be Blank");
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfPTable pTable = new PdfPTable(3);
            pTable.setWidthPercentage(60);
            pTable.setWidths(new int[]{1, 3, 3});
            Font hFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
            PdfPCell hCell = null;
            hCell = new PdfPCell(new Phrase("id", hFont));
            hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pTable.addCell(hCell);

            hCell = new PdfPCell(new Phrase("name", hFont));
            hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pTable.addCell(hCell);

            hCell = new PdfPCell(new Phrase("mobile", hFont));
            hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pTable.addCell(hCell);

            for (User userDto : users) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(userDto.getUserId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pTable.addCell(cell);

                cell = new PdfPCell(new Phrase(userDto.getName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pTable.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(userDto.getMobile())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                pTable.addCell(cell);
            }

            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(pTable);
            document.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return new ByteArrayInputStream(outputStream.toByteArray());

    }

}
