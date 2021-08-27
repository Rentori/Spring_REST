package com.rentori.spring_rest.rest.adminController;

import com.rentori.spring_rest.dto.FileDto;
import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminFileRestControllerV1 {
    private final FileService fileService;

    @Autowired
    public AdminFileRestControllerV1(FileService fileService) {
        this.fileService = fileService;
    }
    
    @PostMapping("/api/v1/users/files")
    public ResponseEntity saveFile(@RequestBody FileDto fileDto) {
        File file = fileDto.toFile();
        fileService.save(file);

        return new ResponseEntity(fileDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users/files")
    public ResponseEntity<List<FileDto>> getFiles() {
        List<FileDto> fileDtoList = new ArrayList<>();
        fileService.getAll().forEach(file -> {
            FileDto fileDto = new FileDto();
            fileDto.setLocation(file.getLocation());
            fileDto.setId(file.getId());
            fileDto.setCreated(file.getCreated());
            fileDto.setUpdated(file.getUpdated());
            fileDtoList.add(fileDto);
        });
        return ResponseEntity.ok(fileDtoList);
    }

    @GetMapping("/api/v1/users/files/{id}")
    public ResponseEntity getFileById(@PathVariable Long id) {
        File file = fileService.getById(id);
        return ResponseEntity.ok(FileDto.fromFile(file));
    }

    @PutMapping("/api/v1/users/files")
    public ResponseEntity updateFile(@RequestBody FileDto fileDto) {
        File file = fileDto.toFile();
        fileService.update(file);

        return new ResponseEntity(fileDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/v1/users/files/{id}")
    public ResponseEntity deleteFileById(@PathVariable Long id) {
        fileService.delete(id);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
