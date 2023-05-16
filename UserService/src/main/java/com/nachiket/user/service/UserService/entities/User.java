package com.nachiket.user.service.UserService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "micro_users")
public class User {
  @Id
  @Column(name = "ID")
  private String userId;
  @Column(name = "NAME", length = 20)
  private String name;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "ABOUT")
  private String about;
}