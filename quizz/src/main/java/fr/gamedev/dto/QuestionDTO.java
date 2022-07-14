package fr.gamedev.dto;

import java.util.Arrays;
import java.util.Objects;

public final class QuestionDTO {
    private final String content;
    private final String answer;
    private final long[] skillIds;

    QuestionDTO(String content, String answer, long[] skillIds) {
        this.content = content;
        this.answer = answer;
        this.skillIds = skillIds;
    }


    public String content() {
        return content;
    }

    public String answer() {
        return answer;
    }

    public long[] skillIds() {
        return skillIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (QuestionDTO) obj;
        return Objects.equals(this.content, that.content) &&
                Objects.equals(this.answer, that.answer) &&
                Arrays.equals(this.skillIds, that.skillIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, answer,skillIds);
    }

}
