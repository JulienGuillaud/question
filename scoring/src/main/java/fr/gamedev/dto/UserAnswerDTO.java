package fr.gamedev.dto;

import java.util.Objects;

public final class UserAnswerDTO {
    private final long questionId;
    private final long userId;
    private final String response;

    UserAnswerDTO(long questionId, long userId, String response) {
        this.questionId = questionId;
        this.userId = userId;
        this.response = response;
    }

    public long questionId() {
        return questionId;
    }

    public long userId() {
        return userId;
    }

    public String response() {
        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserAnswerDTO) obj;
        return this.questionId == that.questionId &&
                this.userId == that.userId &&
                Objects.equals(this.response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, userId, response);
    }

}
