package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Request;
import com.spe.ClassroomManagementSystem.Models.RequestStatus;
import com.spe.ClassroomManagementSystem.Repository.RequestRepository;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RequestServiceImplTest {

    @Autowired
    RequestService requestService;

    @MockBean
    RequestRepository requestRepository;

    @Test
    void getByClassroomAndDateAndRequestStatus() {
        //classroom, date,GRANTED
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Date date = Date.valueOf("2020-05-25");//It's a MONDAY
        RequestStatus requestStatus = RequestStatus.GRANTED;
        //String userType, @NotNull String userName, @NotNull String password
        Login requestor = new Login("professor", "shriyakabra97", "password");

        //Login requestor, @NotNull String purpose,
        //                   @NotNull RequestStatus requestStatus, @NotNull Date classRequestDate,
        //                   @NotNull Time startTime, @NotNull Time endTime,
        //                   String comment, Classroom classroom,
        //                   boolean projector, long plugs,
        //                   boolean cleaningRequired
        Date classRequestDate = Date.valueOf("2020-05-25");
        Time startTime = Time.valueOf("23:45:00");
        Time endTime = Time.valueOf("23:55:00");
        Time startTime1 = Time.valueOf("02:30:00");
        Time endTime1 = Time.valueOf("03:00:00");
        when(requestRepository.getAllByClassroomAndClassRequestDateAndRequestStatus(classroom, date, requestStatus)).thenReturn(Stream.of(
                new Request(requestor, "exams", RequestStatus.GRANTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false ),
                new Request(requestor, "exams", RequestStatus.GRANTED, classRequestDate, startTime1, endTime1, "", classroom, true, 0, false )
        ).collect(Collectors.toList()));
        System.out.println(requestService.getByClassroomAndDateAndRequestStatus(classroom, date, requestStatus));
        assertEquals(RequestStatus.GRANTED, requestService.getByClassroomAndDateAndRequestStatus(classroom, date, requestStatus).get(0).getRequestStatus());

    }

//    @Test
//    void getByRequestStatus() {
//
//        Date classRequestDate = Date.valueOf("2020-05-25");
//        Time startTime = Time.valueOf("23:45:00");
//        Time endTime = Time.valueOf("23:55:00");
//        Time startTime1 = Time.valueOf("02:30:00");
//        Time endTime1 = Time.valueOf("03:00:00");
//        Classroom classroom = new Classroom("A102", 50, true, 0);
//
//        Login requestor = new Login("professor", "shriyakabra97", "password");
//        when(requestRepository.findAllByRequestStatus(RequestStatus.REQUESTED)).thenReturn(Stream.of(
//                new Request(requestor, "exams", RequestStatus.REQUESTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false ),
//                new Request(requestor, "exams", RequestStatus.REQUESTED, classRequestDate, startTime1, endTime1, "", classroom, true, 0, false )
//                ).collect(Collectors.toList())
//        );
//        assertEquals(2,requestService.getByRequestStatus(RequestStatus.REQUESTED).size());
//    }

    @Test
    void saveRejectedRequest() {
        long rId =12;
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Login requestor = new Login("professor", "shriyakabra97", "password");
        Date classRequestDate = Date.valueOf("2020-05-25");
        Time startTime = Time.valueOf("23:45:00");
        Time endTime = Time.valueOf("23:55:00");
        when(requestRepository.getAllByRequestId(rId)).thenReturn(
                new Request(requestor, "exams", RequestStatus.REQUESTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false )
        );
        Request request = new Request(requestor, "exams", RequestStatus.REJECTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false );

        when(requestRepository.save(request)).thenReturn(
                new Request(requestor, "exams", RequestStatus.REJECTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false )
        );
        assertTrue(requestService.saveAcceptedRequest((long) 12));
    }

    @Test
    void saveAcceptedRequest() {
        long rId =12;
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Login requestor = new Login("professor", "shriyakabra97", "password");
        Date classRequestDate = Date.valueOf("2020-05-25");
        Time startTime = Time.valueOf("23:45:00");
        Time endTime = Time.valueOf("23:55:00");
        when(requestRepository.getAllByRequestId(rId)).thenReturn(
                new Request(requestor, "exams", RequestStatus.REQUESTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false )
        );
        Request request = new Request(requestor, "exams", RequestStatus.GRANTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false );

        when(requestRepository.save(request)).thenReturn(
                new Request(requestor, "exams", RequestStatus.GRANTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false )
        );
        assertTrue(requestService.saveAcceptedRequest((long) 12));
    }

//    @Test
//    void saveRequest() {
//
//    }

    @Test
    void getByRequestId() {
        long rId = 12;
        Classroom classroom = new Classroom("A102", 50, true, 0);
        Login requestor = new Login("professor", "shriyakabra97", "password");
        Date classRequestDate = Date.valueOf("2020-05-25");
        Time startTime = Time.valueOf("23:45:00");
        Time endTime = Time.valueOf("23:55:00");

        when(requestRepository.getAllByRequestId(rId)).thenReturn(
                new Request(requestor, "exams", RequestStatus.GRANTED, classRequestDate, startTime, endTime, "", classroom, true, 0, false )
        );

        assertEquals(RequestStatus.GRANTED, requestService.getByRequestId(rId).getRequestStatus());


    }
}