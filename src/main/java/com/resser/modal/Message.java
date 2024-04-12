package com.resser.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User user;

    // @ManyToOne
    // private User sender;
    // @ManyToOne
    // private User receiver;

    private String content;
    private Boolean multiMedia;
    private String image;
    private LocalDateTime createdAt ;

   

    

    
}
