package com.task_tracker.entities;


import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Size(min = 3, message = "Username length must be greater then 3")
	@NonNull
	@NotBlank(message = "Username cannot be can empty string!")
	private String username;
	@NotBlank(message = "firstname cannot be an empty string!")
	@NotNull
	private String firstName;
	@NotBlank(message = "lastname cannot be an empty string!")
	@NotNull
	private String lastName;
	@Email
	@NonNull
	@NotBlank
	private String email;
	@NonNull
	@NotBlank(message = "password cannot be an empty string")
	private String password;
	@OneToMany(mappedBy = "user")
	List<Project> projects = new ArrayList<>();
	
}