package fr.gamedev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
     * QuestionId
     */
    private long questionId;

    /**.
     * UserId
     */
    private long userId;

    /**.
     * correct answer Boolean
     */
    private Boolean correct;

    /**.
     * Answer text String
     */
    private String response;

    /**
     * . int nbPoint
     */
    private Integer nbPoints;

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
     * @return the questionId
     */
    public long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId to set
     */
    public void setQuestionId(final long questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the correct
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * @param nbPoints to set
     */
    public void setNbPoints(final Integer nbPoints) {
        this.nbPoints = nbPoints;
    }

    /**
     * @return int Points
     */
    public Integer getNbPoints() {
        return nbPoints;
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
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the user to set
     */
    public void setUserId(final long userId) {
        this.userId = userId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return response;
    }

    /**
     * @param response the text to set
     */
    public void setResponse(final String response) {
        this.response = response;
    }

}
