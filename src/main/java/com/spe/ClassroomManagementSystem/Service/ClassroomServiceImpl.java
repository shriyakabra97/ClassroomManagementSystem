package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService{
    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public Classroom findByClassCode(String classCode){
        return classroomRepository.findByClassCode(classCode);
    }

    @Override
    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }


    @Override
    public List<Classroom> findAllClassrooms(){
        return classroomRepository.findAll();
    }

    @Override
    public List<Classroom> getClassroomByFormFilter(long capacity, long plugs, boolean projectorAvailable){
        return classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqualAndProjector(capacity, plugs, projectorAvailable);
    }
}
