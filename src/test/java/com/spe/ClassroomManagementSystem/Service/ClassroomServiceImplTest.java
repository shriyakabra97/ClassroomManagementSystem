package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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

        assertEquals(4, classroomService.findAllClassrooms().size());
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

        assertEquals(2, classroomService.getClassroomByFormFilter(capacity, plugs, projectorAvailable).size());
    }

    @Test
    public void findByClassCode(){
        String classCode= "A102";
        when(classroomRepository.findByClassCode(classCode)).thenReturn(new Classroom("A102", 50, true, 0));
        assertEquals(classCode, classroomService.findByClassCode(classCode).getClassCode());

    }
    @Test
    public void saveClassroom(){
        Classroom classroom = new Classroom("A102", 50, true, 0);
        when(classroomRepository.save(classroom)).thenReturn(
                new Classroom("A102", 50, true, 0)
        );
        assertEquals("Saved Classroom Successfully", classroomService.saveClassroom(classroom));
    }

    @Test
    public void getClassroomByFormFilterWithoutProjectorConstraint(){
        long capacity = 50;
        long plugs = 0 ;
        when(classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqual(capacity, plugs)).thenReturn((List<Classroom>) Stream.of(
                new Classroom("A102", 50, false, 10),
                new Classroom("A103", 50, true, 0),
                new Classroom("A104", 100, true, 100),
                new Classroom("A105", 200, false, 20)
        ).collect((Collectors.toList())));
        assertEquals(4, classroomService.getClassroomByFormFilterWithoutProjectorConstraint(capacity, plugs).size());
    }
}
