package com.hemanth.SpringBootMapping.service;


import com.hemanth.SpringBootMapping.MapperClass.StudentMapper;
//import com.hemanth.SpringBootMapping.dto.PageDto;
//import com.hemanth.SpringBootMapping.dto.PaginatedResponse;
import com.hemanth.SpringBootMapping.dto.PageSetting;
import com.hemanth.SpringBootMapping.dto.StudentDto;
import com.hemanth.SpringBootMapping.exception.StudentIdNotFoundException;
import com.hemanth.SpringBootMapping.model.Student;
import com.hemanth.SpringBootMapping.model.University;
import com.hemanth.SpringBootMapping.repository.StudentRepository;
import com.hemanth.SpringBootMapping.repository.UniversityRepository;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Builder
public class StudentService
{


    @Autowired
    StudentRepository sr;



    public StudentDto addStudents(StudentDto student)
    {

        Student s= StudentMapper.dtoToStudentMapping(student);
        Student snew=sr.save(s);

        StudentDto newResult=StudentMapper.studentToDto(snew);
        return newResult;

    }

    public List<StudentDto> getStudent()
    {
        List<Student> result=sr.findAll();

        List<StudentDto> newResult=new ArrayList<>();

        for(Student i:result)
        {
            StudentDto n=StudentMapper.studentToDto(i);
            newResult.add(n);
        }

        return newResult;

    }


    public String updateStudent(StudentDto st)
    {
        Student result=StudentMapper.dtoToStudentMapping(st);
        sr.save(result);
        return "Updated Student Details Successfully";

    }

    public String deleteStudent()
    {
        sr.deleteAll();
        return "All Students Deleted Successfully";
    }


    public void deleteStudentById(int id)
    {
        if(id<0)
            throw new StudentIdNotFoundException("Id Entered is Invalid");
        sr.deleteById(id);
        System.out.println("Deleted the students with the id"+id);

    }



//        public PageDto getPageWiseStudents(int pageNo, int pageSize)
//        {
//
//            PageRequest pageable=PageRequest.of(pageNo,pageSize);
//
//            Page<Student> students= sr.findAll((org.springframework.data.domain.Pageable) pageable);
//
//            List<Student> student_result=students.getContent();
//
//
//            //mapping the whole List
//
//            List<StudentDto> content= student_result.stream().map(s->StudentMapper.studentToDto(s)).collect(Collectors.toList());
//
//            PageDto pageContent=new PageDto();
//            pageContent.setContent(content);
//            pageContent.setPageNo(students.getNumber());
//            pageContent.setPageSize(students.getSize());
//            pageContent.setLast(students.isLast());
//            pageContent.setTotal_Page(students.getTotalPages());
//            pageContent.setTotal_Element((int) students.getTotalElements());
//
//
//            return pageContent;
//
//
//        }



//    public Page<Student> getStudentPage(@NonNull PageDto pageDto)
//    {
//        Sort studentSort=PageDto.b
//
//
//    }



    public Page<Student> getStudentPage(@NonNull PageSetting pageSetting)
    {

        Sort studSort = pageSetting.buildSort();
        Pageable studPage = PageRequest.of(pageSetting.getPage(), pageSetting.getElementPerPage(), studSort);

        return sr.findAll(studPage);
    }





}
