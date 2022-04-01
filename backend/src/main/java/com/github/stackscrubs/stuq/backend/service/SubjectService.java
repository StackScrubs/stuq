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

/**
 * SubjectService handles getting subjects, the subject's teachers and teaching assistants
 * as well as creation, updating and deletion of subjects.
 */
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TermRepository termRepository;

    Logger logger = LoggerFactory.getLogger(SubjectService.class);

    /**
     * Gets a subject using its subject code and the given term. 
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCode The subject's code.
     * @return The subject that has the given subject code that takes place during the given term.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public Subject getSubject(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return this.getSubjectOrThrow(new SubjectId(subjectCode, term));
    }
    
    /**
     * Gets the teachers that teach a certain subject.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCode The subject's code.
     * @return A list of teachers that teach the subject with the given subject code that takes place during the given term.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public List<Teacher> getTeachers(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return new ArrayList<>(
            this.getSubjectOrThrow(new SubjectId(subjectCode, term)).getTeachers()
        );
    }

    /**
     * Gets the teaching assistants that assist the teachers in a certain subject.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCode The subject's code.
     * @return A list of teaching assistants that assist teachers in a certain subject
     *         with the given subject code that takes place during the given term.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public List<TeachingAssistant> getTeachingAssistants(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return new ArrayList<>(
            this.getSubjectOrThrow(new SubjectId(subjectCode, term)).getTeachingAssistants()
        );
    }

    /**
     * Gets assignments that are handed out to students in a certain subject.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCode The subject's code.
     * @return A list assignments that are handed out to students in a certain subject
     *         with the given subject code that takes place during the given term.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public List<Assignment> getAssignments(TermId termId, String subjectCode) {
        Term term = this.getTermOrThrow(termId);

        return new ArrayList<>(
            this.getSubjectOrThrow(new SubjectId(subjectCode, term)).getAssignments()
        );
    }

    /**
     * Creates a new subject with a given subject code and name that takes place during the given term.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCode The subject's code.
     * @param subjectName The subject's full name.
     * @throws SubjectAlreadyExistsException The subject already exists.
     */
    public void create(TermId termId, String subjectCode, String subjectName) {
        Term term = this.getTermOrThrow(termId);

        SubjectId subjectId = new SubjectId(subjectCode, term);
        if (this.subjectRepository.existsById(subjectId)) {
            logger.info(
                "Failed to create subject with term year="
                + termId.getYear() + ", term period="
                + termId.getPeriod() + "and code="
                + subjectCode + " as it already exists"
            );
            throw new SubjectAlreadyExistsException();
        }

        this.subjectRepository.save(new Subject(subjectId, subjectName));
    }

    /**
     * Updates a subject with a given subject code and name that takes place during the given term.
     * @param termId The ID of the term in which the subject takes place.
     * @param oldSubjectCode The subject's old code.
     * @param newSubjectCode The new subject code to set.
     * @param newSubjectName The subject's new full name.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public void update(
        TermId termId,
        String oldSubjectCode,
        String newSubjectCode,
        String newSubjectName
    ) {
        Term term = this.getTermOrThrow(termId);

        SubjectId oldSubjectId = new SubjectId(oldSubjectCode, term);
        if (!this.subjectRepository.existsById(oldSubjectId)) {
            logger.info(
                "Failed to find subject with term year="
                + termId.getYear() + ", term period="
                + termId.getPeriod() + "and code="
                + oldSubjectCode
            );
            throw new SubjectNotFoundException();
        }
        
        SubjectId newSubjectId = new SubjectId(newSubjectCode, term);
        this.subjectRepository.save(new Subject(newSubjectId, newSubjectName));

        if (!oldSubjectId.equals(newSubjectId))
            this.subjectRepository.deleteById(oldSubjectId);
    }

    /**
     * Deletes a subject with a given subject code that takes place during the given term.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCode The subject's code.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
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

    /**
     * Helper method for getting a term or throwing an exception if it does not exist.
     * @param termId The ID of the term to get.
     * @return The term with the given ID.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     */
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

    /**
     * Helper method for getting a subject or throwing an exception if it does not exist.
     * @param subjectId The ID of the subject to get.
     * @return The subject with the given ID.
     * @throws SubjectNotFoundException The subject with the given ID is not registered in the database.
     */
    private Subject getSubjectOrThrow(SubjectId subjectId) {
        return this.subjectRepository.findById(subjectId)
        .orElseThrow(() -> {
                TermId termId = subjectId.getTerm().getId();
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
