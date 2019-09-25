/**
 * Created by jodiakyulas on 24/9/19.
 */
public class Authored {
    private String authorName;
    private String publicationKey;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublicationKey() {
        return publicationKey;
    }

    public void setPublicationKey(String publicationKey) {
        this.publicationKey = publicationKey;
    }

    @Override
    public String toString() {
        return "Authored{" +
                "authorName='" + authorName + '\'' +
                ", publicationKey='" + publicationKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authored)) return false;

        Authored authored = (Authored) o;

        if (!getAuthorName().equals(authored.getAuthorName())) return false;
        return getPublicationKey().equals(authored.getPublicationKey());
    }

    @Override
    public int hashCode() {
        int result = getAuthorName().hashCode();
        result = 31 * result + getPublicationKey().hashCode();
        return result;
    }
}
