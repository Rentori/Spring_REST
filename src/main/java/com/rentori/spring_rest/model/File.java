package com.rentori.spring_rest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Data
public class File extends BaseEntity {
}
