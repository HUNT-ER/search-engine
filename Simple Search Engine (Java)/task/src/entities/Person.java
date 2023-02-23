package entities;

import java.util.Objects;

public class Person {
  private String firstName;
  private String lastName;
  private String email;

  private Person() {
    firstName = "unknown";
    lastName = "unknown";
    email = "unknown";
  }

  private Person(String firstName) {
    this.firstName = firstName;
    this.lastName = "";
    this.email = "";
  }

  private Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = "";
  }

  private Person(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public static Person getPerson(String ... personData) {
    switch (personData.length) {
      case 0:
        return new Person();
      case 1:
        return new Person(personData[0]);
      case 2:
        return new Person(personData[0], personData[1]);
      default:
        return new Person(personData[0], personData[1], personData[2]);
    }
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return (firstName + " " + lastName + " " + email).trim();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(firstName, person.firstName) && Objects.equals(lastName,
        person.lastName) && Objects.equals(email, person.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email);
  }
}
