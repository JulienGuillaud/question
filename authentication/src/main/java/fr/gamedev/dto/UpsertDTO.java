package fr.gamedev.dto;

import java.util.Objects;
import java.util.Set;

public final class UpsertDTO {
    private final String lastName;
    private final String password;
    private final Set<Long> skillIds;

    UpsertDTO(String lastName, String password, Set<Long> skillIds) {
        this.lastName = lastName;
        this.password = password;
        this.skillIds = skillIds;
    }

    public String lastName() {
        return lastName;
    }

    public String password() {
        return password;
    }

    public Set<Long> skills() {
        return skillIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UpsertDTO) obj;
        return Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.skillIds, this.skillIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, password);
    }

}
