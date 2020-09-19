package by.s0mmelier.Dto;

import by.s0mmelier.models.User;

import java.util.Objects;
import java.util.Optional;

public class UserDto {
    private long id;
    private String name;
    private boolean blocked;
    private boolean checked;
    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getUsername();
        this.blocked = user.isBlocked();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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
