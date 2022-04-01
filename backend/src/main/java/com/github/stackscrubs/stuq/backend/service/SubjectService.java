package com.github.stackscrubs.stuq.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.github.stackscrubs.stuq.backend.model.SubjectAlreadyExistsException;
import com.github.stackscrubs.stuq.backend.model.SubjectNotFoundException;
import com.github.stackscrubs.stuq.backend.model.TermNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Assignment;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;
import com.github.stackscrubs.stuq.backend.model.jpa.Teacher;
import com.github.stackscrubs.stuq.backend.model.jpa.TeachingAssistant;
import com.github.stackscrubs.stuq.backend.model.jpa.Term;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;
import com.github.stackscrubs.stuq.backend.repository.SubjectRepository;
import com.github.stackscrubs.stuq.backend.repository.TermRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TermRepository termRepository;

    Logger logger = LoggerFactory.getLogger(SubjectService.class);

    public Subject getSubject(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return this.getSubjectOrThrow(new SubjectId(subjectCode, term));
    }

    public List<Teacher> getTeachers(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return new ArrayList<>(
            this.getSubjectOrThrow(new SubjectId(subjectCode, term)).getTeachers()
        );
    }

    public List<TeachingAssistant> getTeachingAssistants(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return new ArrayList<>(
            this.getSubjectOrThrow(new SubjectId(subjectCode, term)).getTeachingAssistants()
        );
    }

    public List<Assignment> getAssignments(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return new ArrayList<>(
            this.getSubjectOrThrow(new SubjectId(subjectCode, term)).getAssignments()
        );
    }

    public void create(TermId termId, String subjectCode, String subjectName) {
        Term term = this.termRepository.existsById(termId)
                    ? this.termRepository.getById(termId)
                    : this.termRepository.save(new Term(termId));

        SubjectId subjectId = new SubjectId(subjectCode, term);
        if (this.subjectRepository.existsById(subjectId)) {
            logger.info(
                "Failed to create subject with term year="
                + termId.getYear() + ", term period="
                + termId.getPeriod() + "and code="
                + subjectCode + " as it already exists"
            );
            throw new SubjectAlreadyExistsException();
        } else {
            this.subjectRepository.save(new Subject(subjectId, subjectName));
        }
    }

    public void update(TermId termId, String subjectCode, String subjectName) {
        Term term = this.getTermOrThrow(termId);

        SubjectId subjectId = new SubjectId(subjectCode, term);
        if (!this.subjectRepository.existsById(subjectId)) {
            logger.info(
                "Failed to find subject with term year="
                + termId.getYear() + ", term period="
                + termId.getPeriod() + "and code="
                + subjectCode
            );
            throw new SubjectNotFoundException();
        } else {
            this.subjectRepository.save(new Subject(subjectId, subjectName));
        }
    }

    public void delete(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        SubjectId subjectId = new SubjectId(subjectCode, term);
        if (!this.subjectRepository.existsById(subjectId)) {
            logger.info(
                "Failed to find subject with term year="
                + termId.getYear() + ", term period="
                + termId.getPeriod() + "and code="
                + subjectCode
            );
            throw new SubjectNotFoundException();
        } else {
            this.subjectRepository.deleteById(subjectId);
        }
    }

    private Term getTermOrThrow(TermId termId) {
        return this.termRepository.findById(termId)
            .orElseThrow(() -> {
                logger.info(
                    "Failed to find term with term year="
                    + termId.getYear() + " and term period="
                    + termId.getPeriod()
                );
                return new TermNotFoundException();
            }
        );
    }

    private Subject getSubjectOrThrow(SubjectId subjectId) {
        TermId termId = subjectId.getTerm().getId();

        return this.subjectRepository.findById(subjectId)
            .orElseThrow(() -> {
                logger.info(
                    "Failed to find subject with term year="
                    + termId.getYear() + ", term period="
                    + termId.getPeriod() + "and code="
                    + subjectId.getCode()
                );
                return new SubjectNotFoundException();
            }
        );
    }
}
