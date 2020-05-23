package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ClassroomServiceImplTest {

    @Autowired
    private ClassroomService classroomService;
    @MockBean
    private ClassroomRepository classroomRepository;

    @Test
    public void findAllClassroomsTest(){
        when(classroomRepository.findAll()).thenReturn(Stream.of(
                new Classroom("A102", 50, false, 10),
                new Classroom("A103", 50, true, 0),
                new Classroom("A104", 100, true, 100),
                new Classroom("A105", 200, false, 20)
        ).collect(Collectors.toList()));

        Assert.assertEquals(4, classroomService.findAllClassrooms().size());
    }

    @Test
    public void getClassroomByFormFilterTest(){
        long capacity = 50;
        long plugs = 0 ;
        boolean projectorAvailable = true;
        when(classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqualAndProjector(capacity, plugs, projectorAvailable)).thenReturn(Stream.of(
                new Classroom("A103", 50, true, 0),
                new Classroom("A104", 100, true, 100)
        ).collect((Collectors.toList())));
        Assert.assertEquals(2, classroomService.getClassroomByFormFilter(capacity, plugs, projectorAvailable).size());
    }
}
