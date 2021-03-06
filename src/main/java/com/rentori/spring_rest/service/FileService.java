package com.rentori.spring_rest.service;

import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.repository.FileRepository;

import java.util.List;

public interface FileService {
    File getById(Long id);

    File update(File entity);

    File save(File entity);

    void delete(Long id);

    List<File> getAll();
    
    void setFileRepository(FileRepository fileRepository);
}
