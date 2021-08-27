package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.repository.FileRepository;
import com.rentori.spring_rest.service.FileService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class FileServiceImplTest {
    private File file = new File();
    
    @InjectMocks
    private FileService fileService;
    
    @MockBean
    private FileRepository fileRepository;
    
    @Before
    public void setUp() {
        file.setId(1L);
        file.setLocation("test");
    }
    
    @Test
    void getById() {
        when(fileRepository.getById(anyLong())).thenReturn(file);
        assertEquals(fileService.getById(1L), file);
    }
}