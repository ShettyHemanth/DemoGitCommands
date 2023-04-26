//package com.hemanth.SpringBootMapping.dto;
//
//import com.hemanth.SpringBootMapping.model.Student;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Sort;
//
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Slf4j
//public class PageDto<T>
//{
//    List<Student> content;
//    private int pageNo;
//
//    int pageSize;
//
//    int total_Element;
//
//    int total_Page;
//
//    boolean isLast;
//
////    private String key;
////
////    private String direction = "dsc";
//
//
////    public Sort buildSort()
////    {
////        switch (direction)
////        {
////            case "dsc":
////                return Sort.by(key).descending();
////            case "asc":
////                return Sort.by(key).ascending();
////            default:
////                log.warn("Invalid direction provided in PageSettings, using descending direction as default value");
////                return Sort.by(key).descending();
////        }
////
////    }
//
//
//}
