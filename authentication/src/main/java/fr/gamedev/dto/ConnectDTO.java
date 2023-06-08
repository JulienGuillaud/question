package fr.gamedev.dto;

import java.util.Objects;

public final class ConnectDTO {
    private final String lastName;
    private final String password;

    ConnectDTO(String lastName, String password) {
        this.lastName = lastName;
        this.password = password;
    }

    public String lastName() {
        return lastName;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ConnectDTO) obj;
        return Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, password);
    }

}
