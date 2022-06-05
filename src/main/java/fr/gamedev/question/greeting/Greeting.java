package fr.gamedev.question.greeting;

/**
 * @author djer1
 *
 */
public class Greeting {

  /**.
   * id long
   */
  private final long id;
  /**.
   * string content
   */
  private final String content;

  public Greeting(final long unId, final String unContent) {
    this.id = unId;
    this.content = unContent;
  }

  /**
   * @return long
   */
  public long getId() {
    return id;
  }

  /**
   * @return String
   */
  public String getContent() {
    return content;
  }

}
