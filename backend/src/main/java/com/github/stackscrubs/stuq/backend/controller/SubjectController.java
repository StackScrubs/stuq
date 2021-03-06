package com.github.stackscrubs.stuq.backend.controller;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.CreateSubjectRequest;
import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.jpa.Assignment;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.Teacher;
import com.github.stackscrubs.stuq.backend.model.jpa.TeachingAssistant;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;
import com.github.stackscrubs.stuq.backend.service.QueueService;
import com.github.stackscrubs.stuq.backend.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

/**
 * SubjectController handles requests for the SubjectService.
 * 
 * More documentation is generated by Swagger and may be found at @/swagger-ui/index.html
 */
@RestController
@RequestMapping("/subject")
@EnableAutoConfiguration
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private QueueService queueService;

    @Operation(summary = "Get a subject by its ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully retrieved subject", 
          content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Queue.class)) }),
        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found", content = @Content)
    })
    @GetMapping(value = "/{termYear}/{termPeriod}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Subject getById(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getSubject(new TermId(termYear, termPeriod), code);
    }

    @Operation(summary = "Get all registered subjects")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all subjects", 
          content = { @Content(mediaType = "application/json", 
            array = @ArraySchema(schema = @Schema(implementation = Subject.class))) }),
        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content)
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subject> getAll() {
        return this.subjectService.getSubjects();
    }

    @Operation(summary = "Get all teachers who teach a given subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all teachers", 
          content = { @Content(mediaType = "application/json", 
            array = @ArraySchema(schema = @Schema(implementation = Teacher.class))) }),
        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found", content = @Content)
    })
    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getTeachers(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
        ) {
            return this.subjectService.getTeachers(new TermId(termYear, termPeriod), code);
        }

    @Operation(summary = "Get all teaching assistants who assist the teacher in a given subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all teaching assistants", 
        content = { @Content(mediaType = "application/json", 
        array = @ArraySchema(schema = @Schema(implementation = TeachingAssistant.class))) }),
        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found", content = @Content)
    })
    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/teaching-assistants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeachingAssistant> getTeachingAssistants(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getTeachingAssistants(new TermId(termYear, termPeriod), code);
    }

    @Operation(summary = "Get all assignments handed out in a given subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all assignments", 
        content = { @Content(mediaType = "application/json", 
        array = @ArraySchema(schema = @Schema(implementation = Assignment.class))) }),
        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found", content = @Content)
    })
    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/assignments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Assignment> getAssignments(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getAssignments(new TermId(termYear, termPeriod), code);
    }

    @Operation(summary = "Get the queue for a given subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully retrieved queue", 
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Queue.class)) }),
        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found", content = @Content)
    })
    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/queue", produces = MediaType.APPLICATION_JSON_VALUE)
    public Queue getQueue(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.queueService.getBySubject(new TermId(termYear, termPeriod), code);
    }

    @Operation(summary = "Create a new subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully created subject"),
        @ApiResponse(responseCode = "401", description = "Invalid token"),
        @ApiResponse(responseCode = "404", description = "Term not found"),
        @ApiResponse(responseCode = "409", description = "Subject already exists")
    })
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody CreateSubjectRequest request) {
        this.subjectService.create(new TermId(request.getTermYear(), request.getTermPeriod()), request.getCode(), request.getName());
    }

    @Operation(summary = "Update an existing subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully updated subject"),
        @ApiResponse(responseCode = "401", description = "Invalid token"),
        @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    @PutMapping(value = "/{termYear}/{termPeriod}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code,
        @RequestBody CreateSubjectRequest request
    ) {
        this.subjectService.update(
            new TermId(termYear, termPeriod),
            code,
            request.getCode(),
            request.getName()
        );
    }

    @Operation(summary = "Delete an existing subject")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully deleted subject"),
        @ApiResponse(responseCode = "401", description = "Invalid token"),
        @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    @DeleteMapping(value = "/{termYear}/{termPeriod}/{code}")
    public void delete(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        this.subjectService.delete(new TermId(termYear, termPeriod), code);
    }
}
