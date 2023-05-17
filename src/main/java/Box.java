import java.util.Objects;

public class Box {
    private final Long id;
    private final String name;

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Box(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ";" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(id, box.id) && Objects.equals(name, box.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
