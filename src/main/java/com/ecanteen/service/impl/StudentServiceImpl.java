package com.ecanteen.service.impl;


import com.ecanteen.domain.Student;
import com.ecanteen.repository.StudentRepository;
import com.ecanteen.service.StudentService;
import com.ecanteen.service.dto.StudentDTO;
import com.ecanteen.service.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {


    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);


    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;



    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save students : {}", studentMapper);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        log.debug("Request to save students : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public Optional<StudentDTO> partialUpdate(StudentDTO studentDTO) {
        log.debug("Request to partially update students : {}", studentDTO);

        return studentRepository
            .findById(studentDTO.getId())
            .map(existingSchool -> {
                studentMapper.partialUpdate(existingSchool, studentDTO);

                return existingSchool;
            })
            .map(studentRepository::save)
            .map(studentMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<StudentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all students");
        return studentRepository.findAll(pageable).map(studentMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<StudentDTO> findOne(Long id) {
        log.debug("Request to get student : {}", id);
        return studentRepository.findById(id).map(studentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete student : {}", id);
        studentRepository.deleteById(id);
    }


}


