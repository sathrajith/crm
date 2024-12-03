package com.crm.crm_web_app.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

@Service
public class ReportExportService {

    public void exportToPdf(Map<String, Object> report, HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        try (OutputStream out = response.getOutputStream()) {
            // Generate PDF content (simplified)
            String content = String.format(
                    "Report:\nTotal Customers: %s\nTotal Leads: %s\nConversion Rate: %.2f%%\nDate Range: %s",
                    report.get("totalCustomers"), report.get("totalLeads"), report.get("conversionRate"), report.get("dateRange")
            );
            out.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToExcel(Map<String, Object> report, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");

        try (Workbook workbook = new XSSFWorkbook();
             OutputStream out = response.getOutputStream()) {
            Sheet sheet = workbook.createSheet("CRM Report");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Metric");
            header.createCell(1).setCellValue("Value");

            int rowIdx = 1;
            for (Map.Entry<String, Object> entry : report.entrySet()) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue().toString());
            }
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
