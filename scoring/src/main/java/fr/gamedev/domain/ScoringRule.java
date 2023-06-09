package fr.gamedev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Swan
 */
@Entity
public class ScoringRule {

    /**.
     * questionId long
     */
    @GeneratedValue
    @Id
    private long questionId;

    /**
     * . int point
     */
    private int nbPoints;


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
     * @param nbPoints to set
     */
    public void setNbPoints(final int nbPoints) {
        this.nbPoints = nbPoints;
    }

    /**
     * @return int Points
     */
    public int getNbPoints() {
        return nbPoints;
    }

}
