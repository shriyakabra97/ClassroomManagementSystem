package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import com.spe.ClassroomManagementSystem.Repository.RequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;
import java.sql.Time;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.boot.test.mock.mockito.MockBean;
import static com.spe.ClassroomManagementSystem.Models.RequestStatus.GRANTED;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ClassTimingServiceImplTest {

    @Autowired
    private ClassTimingService classTimingService;
    @Autowired
    private ClassroomService classroomService;

    @MockBean
    private ClassroomRepository classroomRepository;
    @MockBean
    private ClassTimingRepository classTimingRepository;
    @MockBean
    private RequestRepository requestRepository;

    @Test
    void saveTimetable() {
        Day day = Day.MONDAY;
        Time startTime = Time.valueOf("10:00:00");
        Time endTime =Time.valueOf("11:00:00");
        Classroom classroom =new Classroom("A102", 50, true, 0);
        ClassTiming classTiming = new ClassTiming(day,startTime, endTime, classroom);

        when(classTimingRepository.save(classTiming)).thenReturn(classTiming);

        assertEquals(classTiming, classTimingService.saveTimetable(classTiming));
    }

    @Test
    void getByClassroomAndDay() {
        //Day dayOfTheWeek,Time startTime,Time endTime,Classroom classroom
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Day day = Day.MONDAY;
        when(classTimingRepository.getAllByClassroomAndDayOfTheWeek(classroom, day)).thenReturn(Stream.of(
                new ClassTiming(Day.MONDAY, Time.valueOf("10:00:00"), Time.valueOf("11:30:00"), classroom),
                new ClassTiming(Day.MONDAY, Time.valueOf("12:00:00"), Time.valueOf("15:30:00"), classroom),
                new ClassTiming(Day.MONDAY, Time.valueOf("22:30:00"), Time.valueOf("23:30:00"), classroom)
        ).collect(Collectors.toList()));
        assertEquals(Day.MONDAY, classTimingService.getByClassroomAndDay(classroom, day).get(0).getDayOfTheWeek());
        System.out.println("Hello from test ---- get By classroom and day..======");
    }

    @Test
    void checkAvailableClassroom() {
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Date date = Date.valueOf("2020-06-01");//It's a MONDAY
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("09:30:00");
        Login requestor = new Login("professor", "shriyakabra97", "password");
        Time startTime1 = Time.valueOf("23:45:00");
        Time endTime1 = Time.valueOf("23:55:00");
        Time startTime2 = Time.valueOf("02:30:00");
        Time endTime2 = Time.valueOf("03:00:00");
        when(classTimingRepository.getAllByClassroomAndDayOfTheWeek(classroom, Day.MONDAY)).thenReturn(Stream.of(
                new ClassTiming(Day.MONDAY, Time.valueOf("10:00:00"), Time.valueOf("11:30:00"), classroom),
                new ClassTiming(Day.MONDAY, Time.valueOf("12:00:00"), Time.valueOf("15:30:00"), classroom),
                new ClassTiming(Day.MONDAY, Time.valueOf("22:30:00"), Time.valueOf("23:30:00"), classroom)
        ).collect(Collectors.toList()));
        when(requestRepository.getAllByClassroomAndClassRequestDateAndRequestStatus(classroom, date, GRANTED)).thenReturn(Stream.of(
                new Request(requestor, "exams", RequestStatus.GRANTED, date, startTime1, endTime1, "", classroom, true, 0, false ),
                new Request(requestor, "exams", RequestStatus.GRANTED, date, startTime2, endTime2, "", classroom, true, 0, false )
        ).collect(Collectors.toList()));

        assertEquals(true, classTimingService.checkAvailableClassroom(classroom, date, startTime, endTime));
    }

    @Test
    void saveInClassTiming() {
        System.out.println("from test--save in class timings----start-----");
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Day day1 = Day.MONDAY;
        Time startTimeFormat = Time.valueOf("09:30:00");
        Time endTimeFormat = Time.valueOf("11:00:00");
        ClassTiming classTiming = new ClassTiming(day1, startTimeFormat, endTimeFormat, classroom);

        when(classTimingRepository.getAllByClassroomAndDayOfTheWeek(classroom, Day.MONDAY)).thenReturn(Stream.of(
                new ClassTiming(Day.MONDAY, Time.valueOf("10:00:00"), Time.valueOf("11:30:00"), classroom),
                new ClassTiming(Day.MONDAY, Time.valueOf("12:00:00"), Time.valueOf("15:30:00"), classroom),
                new ClassTiming(Day.MONDAY, Time.valueOf("22:30:00"), Time.valueOf("23:30:00"), classroom)
        ).collect(Collectors.toList()));

        when(classTimingRepository.save(classTiming)).thenReturn(
                new ClassTiming(Day.MONDAY, startTimeFormat, endTimeFormat, classroom)
        );

        when(classroomRepository.findByClassCode(classroom.getClassCode())).thenReturn(
                new Classroom("A102", 50, true, 0)
        );

        assertEquals(3, classTimingService.getByClassroomAndDay(classroom, Day.MONDAY).size());
        assertEquals("A102", classroomService.findByClassCode(classroom.getClassCode()).getClassCode());
        assertEquals(classTiming.getClassroom().getClassCode(), classTimingService.saveTimetable(classTiming).getClassroom().getClassCode());
        //assertEquals(false, classTimingService.saveInClassTiming(classroom.getClassCode(), startTimeFormat, endTimeFormat ,day1, null));
        System.out.println("from test-- save in class timings----end-----");

    }

}