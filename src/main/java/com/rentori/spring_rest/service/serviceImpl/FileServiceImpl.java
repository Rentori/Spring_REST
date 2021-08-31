package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.aws.AwsS3;
import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.repository.EventRepository;
import com.rentori.spring_rest.repository.FileRepository;
import com.rentori.spring_rest.repository.UserRepository;
import com.rentori.spring_rest.service.FileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final AwsS3 awsS3;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository,
                           UserRepository userRepository,
                           EventRepository eventRepository,
                           AwsS3 awsS3) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.awsS3 = awsS3;
    }

    @Override
    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    
    @Override
    public File getById(Long id) {
        File file = fileRepository.getById(id);
        log.info("IN getById - file: {} successfully got", file.getLocation());
        
        return file;
    }

    @Override
    public File update(File entity) {
        File file = fileRepository.getById(entity.getId());
        awsS3.updateFile(file.getLocation(), entity.getLocation());
        file.setLocation(entity.getLocation());
        fileRepository.save(file);
        log.info("IN update - file: {} successfully updated", entity.getLocation());
        
        return entity;
    }

    @SneakyThrows
    @Override
    public File save(File entity) {
        fileRepository.save(entity);
        java.io.File file = new java.io.File("C:\\Users\\vitya\\" + entity.getLocation());
        file.createNewFile();
        awsS3.putFile(entity.getLocation(), file);
        log.info("IN save - file: {} successfully saved", entity.getLocation());
        
        return entity;
    }

    @Override
    public void delete(Long id) {
        File file = fileRepository.getById(id);
        awsS3.deleteFile(file.getLocation());
        fileRepository.delete(file);
        log.info("IN delete - file: {} successfully deleted", file.getLocation());
    }

    @Override
    public List<File> getAll() {
        List<File> result = fileRepository.findAll();

        log.info("IN getAll - {} files found", result.size());

        return result;
    }
}
