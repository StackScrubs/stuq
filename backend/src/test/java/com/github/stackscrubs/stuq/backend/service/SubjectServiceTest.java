package com.github.stackscrubs.stuq.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;
import com.github.stackscrubs.stuq.backend.model.SubjectAlreadyExistsException;
import com.github.stackscrubs.stuq.backend.model.SubjectNotFoundException;
import com.github.stackscrubs.stuq.backend.model.TermNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.Term;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;
import com.github.stackscrubs.stuq.backend.repository.TermRepository;

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

    @Autowired
    TermRepository termRepository;
    
    @Test
    public void getSubject_getNonExistentSubjectInNonExistentTerm_throwsException() {
        assertThrows(
            TermNotFoundException.class,
            () -> this.subjectService.getSubject(new TermId(2, "bæ"), "IDATTULLBALL")
        );
    }

    @Test
    public void getSubject_getSubjectInNonExistentTerm_throwsException() {
        Term term = new Term(new TermId(2022, "H"));
        this.termRepository.save(term);

        this.subjectService.create(term.getId(), "IDATTULLBALL", "Hestefôr");

        assertThrows(
            TermNotFoundException.class,
            () -> this.subjectService.getSubject(new TermId(2, "bæ"), "IDATTULLBALL")
        );
    }

    @Test
    public void getSubject_getNonExistentSubjectInExistingTerm_throwsException() {
        Term term = new Term(new TermId(2022, "V"));
        this.termRepository.save(term);

        this.subjectService.create(term.getId(), "IDATTULLBALL", "Hestefôr");

        assertThrows(
            SubjectNotFoundException.class,
            () -> this.subjectService.getSubject(term.getId(), "test")
        );
    }

    @Test
    public void getSubject_getExistingSubjectInExistingTerm_returnsSubject() {
        Term term = new Term(new TermId(2022, "V"));
        this.termRepository.save(term);

        String subjectCode = "IFYTT1001";
        String subjectName = "Fysikk Trondheim";

        this.subjectService.create(term.getId(), subjectCode, subjectName);

        assertEquals(subjectName, this.subjectService.getSubject(term.getId(), subjectCode).getName());
    }

    @Test
    public void create_createExistingSubject_throwsException() {
        Term term = new Term(new TermId(2021, "V"));
        this.termRepository.save(term);

        String subjectCode = "IDATT2104";
        String subjectName = "Nettverksprogrammering";

        this.subjectService.create(term.getId(), subjectCode, subjectName);

        assertThrows(
            SubjectAlreadyExistsException.class,
            () -> this.subjectService.create(term.getId(), subjectCode, subjectName)
        );
    }

    @Test
    public void create_createNonExistentSubject_createsSubject() {
        Term term = new Term(new TermId(2023, "V"));
        this.termRepository.save(term);

        String subjectCode = "IDATT2104";
        String subjectName = "Fullstack";
        this.subjectService.create(term.getId(), subjectCode, subjectName);

        assertEquals(subjectName, this.subjectService.getSubject(term.getId(), subjectCode).getName());
    }

    @Test
    public void update_updateExistingSubject_updatesSubject() {
        Term term = new Term(new TermId(2024, "V"));
        this.termRepository.save(term);
        
        String subjectCode = "IDATT2104";
        String misspelledSubjectName = "Fullstacksasasas";
        this.subjectService.create(term.getId(), subjectCode, misspelledSubjectName);

        String fixedSubjectName = "Fullstack";
        this.subjectService.update(term.getId(), subjectCode, subjectCode, fixedSubjectName);

        Subject subject = this.subjectService.getSubject(term.getId(), subjectCode);
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
        Term term = new Term(new TermId(2025, "V"));
        this.termRepository.save(term);

        String subjectCode = "IDATT2104";
        String subjectName = "Fullstack";
        this.subjectService.create(term.getId(), subjectCode, subjectName);

        this.subjectService.delete(term.getId(), subjectCode);

        assertThrows(SubjectNotFoundException.class, () -> this.subjectService.getSubject(term.getId(), subjectCode));
    }
}
