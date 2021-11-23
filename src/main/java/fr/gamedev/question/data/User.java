package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author djer1
 *
 */
@Entity
public class User {

  @GeneratedValue
  @Id
  private long id;
  private String login;
  private String lastName;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param unId the id to set
   */
  public void setId(final long unId) {
    this.id = id;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param unLogin the login to set
   */
  public void setLogin(final String unLogin) {
    this.login = unLogin;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param unLastName the lastName to set
   */
  public void setLastName(final String unLastName) {
    this.lastName = unLastName;
  }

}
