package edu.netcracker.view

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.web.servlet.view.document.AbstractExcelView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class StudentExportExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

    }
}
