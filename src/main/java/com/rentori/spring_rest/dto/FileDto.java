package com.rentori.spring_rest.dto;

import com.rentori.spring_rest.model.File;
import lombok.Data;

import java.util.Date;

@Data
public class FileDto {
    private Long id;
    private String location;
    private Date created;
    private Date updated;
    
    public File toFile() {
        File file = new File();
        file.setId(id);
        file.setLocation(location);
        file.setCreated(new Date());
        file.setUpdated(new Date());
        
        return file;
    }
    
    public static FileDto fromFile(File file) {
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setLocation(file.getLocation());
        fileDto.setCreated(file.getCreated());
        fileDto.setUpdated(file.getUpdated());
        
        return fileDto;
    }
}
