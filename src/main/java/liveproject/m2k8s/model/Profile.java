package liveproject.m2k8s.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Entity(name = "emp_profile")
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @NotNull
  @Size(min = 5, max = 16, message = "{username.size}")
  private String username;

  @NotNull
  @Size(min = 5, max = 25, message = "{password.size}")
  private String password;

  @NotNull
  @Size(min = 2, max = 30, message = "{firstName.size}")
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Size(min = 2, max = 30, message = "{lastName.size}")
  @Column(name = "last_name")
  private String lastName;

  @NotNull
  private String email;

  // required by JPA
  public Profile() {
  }

  public Profile(final String username, final String password, final String firstName, final String lastName,
      final String email) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  // this is for testing only
  public Profile(final Long id, final String username, final String password, final String firstName,
      final String lastName, final String email) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

}
