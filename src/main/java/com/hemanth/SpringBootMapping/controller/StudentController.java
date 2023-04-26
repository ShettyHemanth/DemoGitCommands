package com.hemanth.SpringBootMapping.controller;



import com.hemanth.SpringBootMapping.EmailConfig.MailConfig;
//import com.hemanth.SpringBootMapping.MapperClass.PageMapping;
//import com.hemanth.SpringBootMapping.dto.PageDto;
import com.hemanth.SpringBootMapping.dto.PageSetting;
import com.hemanth.SpringBootMapping.dto.StudentDto;
import com.hemanth.SpringBootMapping.model.Student;
import com.hemanth.SpringBootMapping.model.University;
import com.hemanth.SpringBootMapping.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
public class StudentController
{

    @Autowired
    StudentService ss;

    @Autowired
    RabbitTemplate rabbitTemplate;



    @RequestMapping(method = RequestMethod.POST,value = "/users")
    public StudentDto addingStudent(@Valid @RequestBody StudentDto student)
    {

        rabbitTemplate.convertAndSend(MailConfig.topic_exchange,MailConfig.routing_Key,student);




        System.out.println("Data Entered successfully");
        return ss.addStudents(student);

    }



    @RequestMapping(method = RequestMethod.GET,value = "/details")
    public List<StudentDto> gettingStudents()
    {
        return ss.getStudent();
    }


    @RequestMapping(method = RequestMethod.PUT,value = "/update")
    public String upStudents(@RequestBody StudentDto s)
    {
        return ss.updateStudent(s);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public String deleteStudents()
    {
        return ss.deleteStudent();
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{username}")
    public void deleteStudentsById(@PathVariable("username") int id)
    {
        ss.deleteStudentById(id);
    }


//    @RequestMapping(method = RequestMethod.GET,value = "/page/details")
//    public PageDto getStudentByPage(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
//                                    @RequestParam(value="pageSize",defaultValue = "1",required = false)int pageSize)
//    {
//
//         return ss.getPageWiseStudents(pageNo,pageSize);
//
//    }
//
//    @RequestMapping(method = RequestMethod.GET,value = "custom/page/details/{offset}/{pageSize}")
//    public PageDto getStudentByPageCustom(@PathVariable int offset,@PathVariable int pageSize)
//    {
//
//        return ss.getPageWiseStudents(offset,pageSize);
//
//    }



//    @GetMapping("/page")
//    public PageDto<Student> getPlantPage(PageDto pageDto)
//    {
//
//        log.info(
//                "Request for plant page received with data : " + pageDto);
//
//        return PageMapping.pageToPageDto(plantService.getPlantPage(pageSettings));
//    }

    @GetMapping("/page")
    public Page<Student> getStudentPage(PageSetting pageSettings)
    {

        log.info(
                "Request for Student page received with data : " + pageSettings);

        return ss.getStudentPage(pageSettings);
    }



}
