package dmme.kuvid.domain.database.dbtest;

public abstract class Members {
    String name;
    int id;

    public Members(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Members{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
