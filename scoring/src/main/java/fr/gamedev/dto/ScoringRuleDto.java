package fr.gamedev.dto;

import java.util.Objects;

public final class ScoringRuleDto {
    private final long questionId;
    private final int nbPoint;

    ScoringRuleDto(long questionId, int nbPoint) {
        this.questionId = questionId;
        this.nbPoint = nbPoint;
    }

    public long questionId() {
        return questionId;
    }

    public int nbPoint() {
        return nbPoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ScoringRuleDto) obj;
        return this.questionId == that.questionId &&
                this.nbPoint == that.nbPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, nbPoint);
    }

}
