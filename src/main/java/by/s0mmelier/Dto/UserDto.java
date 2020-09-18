package by.s0mmelier.Dto;

import by.s0mmelier.models.User;

import java.util.Objects;
import java.util.Optional;

public class UserDto {
    private long id;
    public UserDto(Optional<User> user){
        this.id = user.get().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
