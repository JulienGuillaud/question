package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author djer1
 *
 */
@Entity
public class Question {

  /**.
   * id long
   */
  @GeneratedValue
  @Id
  private long id;
  /**.
   * content string
   */
  private String content;
  /**.
   * response string
   */
  private String answer;
  /**.
   * Tag tag
   */
  @ManyToMany
  private Set<Tag> tags;
  /**.
   * point
   */
  private int point;

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
  /**
   * @return Tag
   */
  public Set<Tag> getTags() {
    return tags;
  }
  /**
   * @param  newTags
   */
  public void setTags(final Set<Tag> newTags) {
    this.tags = newTags;
  }
  /**
   * @param newPoint the text to set
   */
  public void setPoint(final int newPoint) {
    this.point = newPoint;
  }
  /**
   * @return the point
   */
  public int getPoint() {
    return point;
  }
  /**
   * @param newResponse the text to set
   */
  public void setAnswer(final String newResponse) {
    this.answer = newResponse;
  }
  /**
   * @return the point
   */
  public String getAnswer() {
    return answer;
  }
}
