package com.github.stackscrubs.stuq.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;
import com.github.stackscrubs.stuq.backend.model.SubjectAlreadyExistsException;
import com.github.stackscrubs.stuq.backend.model.SubjectNotFoundException;
import com.github.stackscrubs.stuq.backend.model.TermNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = StuqBackendApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SubjectServiceTest {
    @Autowired
    SubjectService subjectService;
    
    @Test
    public void getSubject_getNonExistentSubjectInNonExistentTerm_throwsException() {
        assertThrows(
            TermNotFoundException.class,
            () -> this.subjectService.getSubject(new TermId(2, "bæ"), "IDATTULLBALL")
        );
    }

    @Test
    public void getSubject_getSubjectInNonExistentTerm_throwsException() {
        this.subjectService.create(new TermId(2022, "H"), "IDATTULLBALL", "Hestefôr");

        assertThrows(
            TermNotFoundException.class,
            () -> this.subjectService.getSubject(new TermId(2, "bæ"), "IDATTULLBALL")
        );
    }

    @Test
    public void getSubject_getNonExistentSubjectInExistingTerm_throwsException() {
        TermId termId = new TermId(2022, "V");
        this.subjectService.create(termId, "IDATTULLBALL", "Hestefôr");

        assertThrows(
            SubjectNotFoundException.class,
            () -> this.subjectService.getSubject(termId, "test")
        );
    }

    @Test
    public void getSubject_getExistingSubjectInExistingTerm_returnsSubject() {
        TermId termId = new TermId(2022, "V");
        String subjectCode = "IFYTT1001";
        String subjectName = "Fysikk Trondheim";

        this.subjectService.create(termId, subjectCode, subjectName);

        assertEquals(subjectName, this.subjectService.getSubject(termId, subjectCode).getName());
    }

    @Test
    public void create_createExistingSubject_throwsException() {
        TermId termId = new TermId(2021, "V");
        String subjectCode = "IDATT2104";
        String subjectName = "Nettverksprogrammering";

        this.subjectService.create(termId, subjectCode, subjectName);

        assertThrows(SubjectAlreadyExistsException.class, () -> this.subjectService.create(termId, subjectCode, subjectName));
    }

    @Test
    public void create_createNonExistentSubject_createsSubject() {
        TermId termId = new TermId(2023, "V");
        String subjectCode = "IDATT2104";
        String subjectName = "Fullstack";
        this.subjectService.create(termId, subjectCode, subjectName);

        assertEquals(subjectName, this.subjectService.getSubject(termId, subjectCode).getName());
    }

    @Test
    public void update_updateExistingSubject_updatesSubject() {
        TermId termId = new TermId(2024, "V");
        String subjectCode = "IDATT2104";
        String misspelledSubjectName = "Fullstacksasasas";
        this.subjectService.create(termId, subjectCode, misspelledSubjectName);

        String fixedSubjectName = "Fullstack";
        this.subjectService.update(termId, subjectCode, fixedSubjectName);

        Subject subject = this.subjectService.getSubject(termId, subjectCode);
        String actualSubjectName = subject.getName();

        assertEquals(fixedSubjectName, actualSubjectName);
        assertNotEquals(misspelledSubjectName, actualSubjectName);
    }

    @Test
    public void delete_deleteNonExistentSubject_throwsException() {
        assertThrows(
            TermNotFoundException.class,
            () -> this.subjectService.delete(new TermId(65, "mø"), "s")    
        );
    }

    @Test
    public void delete_deleteExistingSubject_deletesException() {
        TermId termId = new TermId(2025, "V");
        String subjectCode = "IDATT2104";
        String subjectName = "Fullstack";
        this.subjectService.create(termId, subjectCode, subjectName);

        this.subjectService.delete(termId, subjectCode);

        assertThrows(SubjectNotFoundException.class, () -> this.subjectService.getSubject(termId, subjectCode));
    }
}
