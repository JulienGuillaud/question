package fr.gamedev.dto;

import java.util.Objects;

public final class HasPendingUserAnswerDTO {
    private final long userId;

    HasPendingUserAnswerDTO(long userId, String response) {
        this.userId = userId;
    }

    public long userId() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (HasPendingUserAnswerDTO) obj;
        return this.userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}
