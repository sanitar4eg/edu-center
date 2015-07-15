package edu.netcracker.service.impl

import edu.netcracker.model.Student
import edu.netcracker.repository.StudentJpaRepository
import edu.netcracker.service.StudentService
import edu.netcracker.view.StudentExportExcelView
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.hibernate.envers.AuditReader
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.RevisionType
import org.hibernate.envers.query.AuditEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import javax.persistence.EntityManager

@Service
class StudentServiceImpl implements StudentService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    StudentJpaRepository repository;

    public List<Student> getStudentsHistoryAfterDate(Date date) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List list = auditReader.createQuery()
                .forRevisionsOfEntity(Student.class, true, true)
                .add(AuditEntity.revisionNumber().le(auditReader.getRevisionNumberForDate(date)))
                .add(AuditEntity.revisionNumber().maximize().computeAggregationInInstanceContext())
                .add(AuditEntity.revisionType().ne(RevisionType.DEL))
                .getResultList();
        return null;
    }

    @Override
    public List<Student> findAll() {
        repository.findAll()
    }

    @Override
    public void saveAndFlush(Student student) {
        repository.saveAndFlush(student)
    }

    @Override
    public Student getOne(Long id) {
        repository.getOne(id)
    }

    @Override
    public void delete(Long id) {
        repository.delete(id)
    }

    @Override
    List<Student> findDev() {
        repository.findByTypeIgnoreCase("dev")
    }

    @Override
    void handleImport(MultipartFile file) {
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes())
        Workbook workbook;
        if (file.getOriginalFilename().endsWith("xls")) {
            workbook = new HSSFWorkbook(bis);
        } else {
            throw new IllegalArgumentException("Received file does not have a standard excel extension.");
        }
        if (file.empty) {
            return
        }

        for (Row row : workbook.getSheetAt(0).rowIterator()) {
            if (row.getRowNum() != 0) {
                Student student = new Student()
                student.id = row.getCell(StudentExportExcelView.ID).getNumericCellValue().toLong()
                student.firstName = row.getCell(StudentExportExcelView.FIRST_NAME).getStringCellValue()
                student.lastName = row.getCell(StudentExportExcelView.LAST_NAME).getStringCellValue()
                student.middleName = row.getCell(StudentExportExcelView.MIDDLE_NAME).getStringCellValue()
                student.type = row.getCell(StudentExportExcelView.TYPE).getStringCellValue()
                student.email = row.getCell(StudentExportExcelView.EMAIL).getStringCellValue()
                student.phone = row.getCell(StudentExportExcelView.PHONE).getStringCellValue()
                student.university = row.getCell(StudentExportExcelView.UNIVER).getStringCellValue()
                student.specialty = row.getCell(StudentExportExcelView.SPECIALTY).getStringCellValue()
                student.course = row.getCell(StudentExportExcelView.COURSE).getStringCellValue()
                student.group = row.getCell(StudentExportExcelView.GROUP).getStringCellValue()
                repository.save(student)
            }
        }
    }

    @Override
    List<Student> findQA() {
        repository.findByTypeIgnoreCase("qa")
    }
}
