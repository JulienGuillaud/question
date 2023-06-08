package fr.gamedev.dto;

import java.util.Arrays;
import java.util.Objects;

public final class NextQuestionDTO {
    private final long userId;
    private final long[] skillIds;

    NextQuestionDTO(long userId, long[] skillIds) {
        this.skillIds = skillIds;
        this.userId = userId;
    }


    public long userId() {
        return userId;
    }

    public long[] skillIds() {
        return skillIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (NextQuestionDTO) obj;
        return this.userId == that.userId &&
                Arrays.equals(this.skillIds, that.skillIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, skillIds);
    }

}
