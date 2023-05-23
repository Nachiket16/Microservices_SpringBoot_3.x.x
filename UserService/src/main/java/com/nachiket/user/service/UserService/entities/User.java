package com.nachiket.user.service.UserService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "micro_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

  @Transient    //This field will be ignored by the JPA
  private List<Rating> ratings = new ArrayList<>();

}
