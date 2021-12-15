package fr.gamedev.question.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Swan
 */
@Entity
public class UserAnswer {

  /**.
   * id long
   */
  @GeneratedValue
  @Id
  private long id;

  /**.
   * Question
   */
  @ManyToOne
  private Question question;
  /**.
   * User
   */
  @ManyToOne
  private User user;
  /**.
   * correct answer Boolean
   */
  private Boolean correct;
  /**.
   * Answer text String
   */
  private String text;

	/**
	 * . int point
	 */
	private int points;

	/**
	 * . LocalDatetime date
	 */
	private LocalDateTime date;

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
   * @return the correct
   */
  public Boolean getCorrect() {
    return correct;
  }

	/**
	 * @param newPoints to set
	 */
	public void setPoints(final int newPoints) {
		this.points = newPoints;
	}

	/**
	 * @return int Points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param newDate to set
	 */
	public void setDate(final LocalDateTime newDate) {
		this.date = newDate;
	}

	/**
	 * @return newDate date
	 */
	public LocalDateTime getDate() {
		return date;
	}

  /**
   * @param newCorrect the correct to set
   */
  public void setCorrect(final Boolean newCorrect) {
    this.correct = newCorrect;
  }
  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }
  /**
   * @param newUser the user to set
   */
  public void setUser(final User newUser) {
    this.user = newUser;
  }
  /**
   * @return the text
   */
  public String getText() {
    return text;
  }
  /**
   * @param newText the text to set
   */
  public void setText(final String newText) {
    this.text = newText;
  }

}
