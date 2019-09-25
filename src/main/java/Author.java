/**
 * Created by jodiakyulas on 24/9/19.
 */
public class Author {
    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        return getName().equals(author.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
