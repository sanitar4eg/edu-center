package edu.netcracker.view

import edu.netcracker.model.Student
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
        response.setHeader("Content-Type", "application/octet-stream")
        response.setHeader("Content-Disposition", "attachment; filename=Students.xls")
        List<Student> students = model.get("students")


    }
}
