package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 4)
	private Integer id;
	@NotBlank(message = "Username is mandatory")
	@Column(length = 125)
	private String username;
	@NotBlank(message = "Password is mandatory")
	@Column(length = 125)
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,64}$", message = "password must contain Minimum eight characters, at least one uppercase letter, one lowercase letter and one number.")
	private String password;
	@NotBlank(message = "FullName is mandatory")
	@Column(length = 125)
	private String fullname;
	@NotBlank(message = "Role is mandatory")
	@Column(length = 125)
	private String role;

	/**
	 * Instantiates a new user.
	 *
	 * @param username the user name
	 * @param password the password
	 * @param fullname the full name
	 * @param role     the role
	 */
	public User(@NotBlank(message = "Username is mandatory") String username,
			@NotBlank(message = "Password is mandatory") @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,64}$", message = "password must contain special character, a uppercase and a lowercase at least.") String password,
			@NotBlank(message = "FullName is mandatory") String fullname,
			@NotBlank(message = "Role is mandatory") String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
