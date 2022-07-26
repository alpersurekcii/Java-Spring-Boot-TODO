package com.alpersurekci.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "todo_table")
public class TodoEntity extends BaseEntity {

    @Column(name = "doName")
    private String doName;

    @Column(name = "completed", columnDefinition = "boolean default false")
    private boolean completed;


    @ManyToOne
    private UserEntity userEntity;

}
