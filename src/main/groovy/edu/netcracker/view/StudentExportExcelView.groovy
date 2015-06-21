package edu.netcracker.view
import edu.netcracker.model.Student
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.springframework.web.servlet.view.document.AbstractExcelView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat

class StudentExportExcelView extends AbstractExcelView {

    public static final int ID = 0
    public static final int FIRST_NAME = 1
    public static final int MIDDLE_NAME = 2
    public static final int LAST_NAME = 3
    public static final int TYPE = 4
    public static final int EMAIL = 5
    public static final int PHONE = 6
    public static final int UNIVER = 7
    public static final int SPECIALTY = 8
    public static final int COURSE = 9
    public static final int GROUP = 10

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date())
        response.setHeader("Content-Type", "application/octet-stream")
        response.setHeader("Content-Disposition", "attachment; filename=Students_${currentDate}.xls")

        HSSFSheet sheet = workbook.createSheet("Cписок студентов")
        sheet.setDefaultColumnWidth(15)

        Font font = workbook.createFont()
        font.setFontName("Arial")
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD)
        font.setColor(HSSFColor.WHITE.index)
        CellStyle style = workbook.createCellStyle()
        style.setFillForegroundColor(HSSFColor.BLUE.index)
        style.setFillPattern(CellStyle.SOLID_FOREGROUND)
        style.setFont(font)

        HSSFRow header = sheet.createRow(0)

        setCellValueAndStyle(header.createCell(ID), "ID", style)
        setCellValueAndStyle(header.createCell(FIRST_NAME), "Имя", style)
        setCellValueAndStyle(header.createCell(MIDDLE_NAME), "Отчество", style)
        setCellValueAndStyle(header.createCell(LAST_NAME), "Фамилия", style)
        setCellValueAndStyle(header.createCell(TYPE), "Вид обучения", style)
        setCellValueAndStyle(header.createCell(EMAIL), "Email", style)
        setCellValueAndStyle(header.createCell(PHONE), "Телефон", style)
        setCellValueAndStyle(header.createCell(UNIVER), "Университет", style)
        setCellValueAndStyle(header.createCell(SPECIALTY), "Специальность", style)
        setCellValueAndStyle(header.createCell(COURSE), "Курс", style)
        setCellValueAndStyle(header.createCell(GROUP), "Группа", style)

        List<Student> students = model.get("students") as List<Student>
        int counter = 1
        students.each { Student student ->
            HSSFRow row = sheet.createRow(counter++)
            setCellValueAndStyle(row.createCell(ID), student.id, null)
            setCellValueAndStyle(row.createCell(FIRST_NAME), student.firstName, null)
            setCellValueAndStyle(row.createCell(MIDDLE_NAME), student.middleName, null)
            setCellValueAndStyle(row.createCell(LAST_NAME), student.lastName, null)
            setCellValueAndStyle(row.createCell(TYPE), student.type, null)
            setCellValueAndStyle(row.createCell(EMAIL), student.email, null)
            setCellValueAndStyle(row.createCell(PHONE), student.phone, null)
            setCellValueAndStyle(row.createCell(UNIVER), student.university, null)
            setCellValueAndStyle(row.createCell(SPECIALTY), student.specialty, null)
            setCellValueAndStyle(row.createCell(COURSE), student.course, null)
            setCellValueAndStyle(row.createCell(GROUP), student.group, null)
        }
    }

    def setCellValueAndStyle = { HSSFCell cell, def value, CellStyle style ->
        cell.setCellValue(value)
        cell.setCellStyle(style)
    }
}
