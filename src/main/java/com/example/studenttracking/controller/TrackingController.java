package com.example.studenttracking.controller;

import com.example.studenttracking.model.*;
import com.example.studenttracking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrackingController {

    @Autowired
    private StudentTrackingRepository studentTrackingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping("")
    public String trackingGet(HttpServletRequest request){
        return "home";
    }

    @PostMapping("/student")
    public String postStudent(HttpServletRequest request){
        Student student = new Student();
        student.setCode(request.getParameter("code"));
        student.setName(request.getParameter("name"));
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping("/student")
    public String getStudent(HttpServletRequest request){
        request.setAttribute("students", studentRepository.findAll());
        return "ds-sinh-vien";
    }

    @PostMapping("/subject")
    public String postSubject(HttpServletRequest request){
        Subject subject = new Subject();
        subject.setCode(request.getParameter("code"));
        subject.setName(request.getParameter("name"));
        subjectRepository.save(subject);
        return "redirect:/subject";
    }

    @GetMapping("/subject")
    public String getSubject(HttpServletRequest request){
        request.setAttribute("subjects", subjectRepository.findAll());
        return "ds-mon-hoc";
    }

    @PostMapping("/teacher")
    public String postteacher(HttpServletRequest request){
        Teacher teacher = new Teacher();
        teacher.setCode(request.getParameter("code"));
        teacher.setName(request.getParameter("name"));
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/teacher")
    public String getteacher(HttpServletRequest request){
        request.setAttribute("teachers", teacherRepository.findAll());
        return "ds-giao-vien";
    }

    @PostMapping("/classes")
    public String postclasess(HttpServletRequest request){
        Classes classes = new Classes();
        classes.setCode(request.getParameter("code"));
        classes.setName(request.getParameter("name"));

        Teacher teacher = teacherRepository.findByCode(request.getParameter("teacherId"));
        if(teacher != null){
            classes.setTeacher(teacher);
            classesRepository.save(classes);
        }
        return "redirect:/classes";
    }

    @GetMapping("/classes")
    public String getclasess(HttpServletRequest request){
        request.setAttribute("classes", classesRepository.findAll());
        return "ds-lop-hoc";
    }

    @GetMapping("/classes/{id}/students")
    public String getclasess(HttpServletRequest request, @PathVariable("id") Long classesId){

        request.setAttribute("students", studentRepository.findAllByClassesId(classesId));
        Classes classes = classesRepository.findById(classesId).orElse(null);
        request.setAttribute("classes", classes);
        return "ds-sv-lop-hoc";
    }

    @PostMapping("/classes/{id}/students")
    public String postclasess(HttpServletRequest request, @PathVariable("id") Long classesId){

        Student student = studentRepository.findByCode(request.getParameter("studentCode"));
        if(student != null){
            Classes classes = classesRepository.findById(classesId).orElse(null);
            if(classes != null){
                classes.getStudents().add(student);
                classesRepository.save(classes);
            }
        }
        return "redirect:/classes/" + classesId + "/students";
    }

    @GetMapping("/classes/{id}/tracking")
    public String getTracking(HttpServletRequest request, @PathVariable("id") Long classesId){

        Classes classes = classesRepository.findById(classesId).orElse(null);

        for(Student student: classes.getStudents()){
            StudentTracking studentTracking = new StudentTracking();
            if(studentTrackingRepository
                    .findAllByClassesIdAndStudentIdAndTrackingDate(classesId, student.getId(), LocalDate.now()) != null){
                continue;
            }
            studentTracking.setClasses(classes);
            studentTracking.setStudent(student);
            studentTracking.setIsAbsent(0);
            studentTrackingRepository.save(studentTracking);
        }

        request.setAttribute("classes", classes);
        request.setAttribute("tracks",
                studentTrackingRepository.findAllByClassesIdAndTrackingDate(classesId, LocalDate.now()));
        return "tracking";
    }

    @PostMapping("/classes/{classId}/tracking/{id}")
    public String postTracking(HttpServletRequest request, @PathVariable("id") Long trackId,
                               @PathVariable("classId") Long classId){

        StudentTracking studentTracking = studentTrackingRepository.findById(trackId).orElse(null);
        studentTracking.setIsAbsent(Integer.parseInt(request.getParameter("absent")));

        studentTrackingRepository.save(studentTracking);

        return "redirect:/classes/" + classId + "/tracking";
    }

    @GetMapping("/tracking/history/{classesid}/{studentid}")
    public String getHistoryTrack(HttpServletRequest request, @PathVariable("classesid") Long classId,
                               @PathVariable("studentid") Long studentId){

        List<StudentTracking> studentTrackings = studentTrackingRepository.findAllByStudentIdAndClassesId(studentId, classId);

        request.setAttribute("student", studentRepository.findById(studentId).orElse(null));
        request.setAttribute("classes", classesRepository.findById(classId).orElse(null));
        request.setAttribute("history", studentTrackings);
        return "history-track";
    }

}
