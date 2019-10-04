/**
 * Created by jodiakyulas on 24/9/19.
 */
public class Publication {

    private String mdate;
    private String title;
    private String type;
    private String pubKey;
    private String journal;
    private String booktitle;
    private String year;
    private String month;
    private String crossRef;

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCrossRef() {
        return crossRef;
    }

    public void setCrossRef(String crossRef) {
        this.crossRef = crossRef;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "mdate='" + mdate + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", pubKey='" + pubKey + '\'' +
                ", journal='" + journal + '\'' +
                ", booktitle='" + booktitle + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

}
