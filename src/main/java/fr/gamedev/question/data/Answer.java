package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author djer1
 */
@Entity
public class Answer {

  /**.
   * id long
   */
  @GeneratedValue
  @Id
  private long id;

  /**.
   * Question
   */
  @OneToOne
  private Question question;
  /**.
   * correct answer Boolean
   */
  private Boolean correctAnswer;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param newId
   */
  public void setId(final long newId) {
    this.id = newId;
  }

  /**
   * @return the question
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * @param uneQuestion the question to set
   */
  public void setQuestion(final Question uneQuestion) {
    this.question = uneQuestion;
  }

  /**
   * @return the correctAnswer
   */
  public Boolean getCorrectAnswer() {
    return correctAnswer;
  }

  /**
   * @param uneCorrectAnswer the correctAnswer to set
   */
  public void setCorrectAnswer(final Boolean uneCorrectAnswer) {
    this.correctAnswer = uneCorrectAnswer;
  }

}
