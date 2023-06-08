package fr.gamedev.dto;

import java.util.Objects;

public final class PendingUserAnswerDTO {
    private final long questionId;
    private final long userId;

    PendingUserAnswerDTO(long questionId, long userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    public long questionId() {
        return questionId;
    }

    public long userId() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PendingUserAnswerDTO) obj;
        return this.questionId == that.questionId &&
                this.userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId);
    }

}
