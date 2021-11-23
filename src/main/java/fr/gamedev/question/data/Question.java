package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author djer1
 *
 */
@Entity
public class Question {

  @GeneratedValue
  @Id
  private long id;
  private String content;

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
    this.id = unId;
  }

  /**
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param unContent the content to set
   */
  public void setContent(final String unContent) {
    this.content = unContent;
  }

}
