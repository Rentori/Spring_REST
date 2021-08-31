package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.repository.FileRepository;
import com.rentori.spring_rest.service.FileService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FileServiceImplTest {
    private File file = new File();
    private List<File> fileList = new ArrayList<>();
    
    @InjectMocks
    private FileService fileService;
    
    @Mock
    private FileRepository fileRepository;

    @Before
    public void setUp() {
        fileService.setFileRepository(fileRepository);
        file.setId(1L);
        file.setLocation("test");
        fileList.add(file);
    }

    @Test
    void getById() {
        when(fileRepository.getById(anyLong())).thenReturn(file);
        assertEquals(fileService.getById(1L), file);
    }
    
    @Test
    void save() {
        when(fileRepository.save((File) any())).thenReturn(file);
        assertEquals(fileService.save(file), file);
    }

    @Test
    void delete() {
        doNothing().when(fileRepository).deleteById(anyLong());
        fileService.delete(1L);
        verify(fileRepository).deleteById(1L);
    }

    @Test
    void getAll() {
        when(fileRepository.findAll()).thenReturn(fileList);
        assertNotNull(fileList);

        fileService.getAll();
        verify(fileRepository).findAll();
    }
}