import java.util.ArrayList;

public class Channel {
    protected String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }



    protected String link;
    protected String description;
    protected String atom;

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return " title : "+title+" link : "+link+" description : "+description+" atom : "+atom;
    }

    public Channel(String title, String link, String description, String atom, ArrayList<Item> item) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.atom = atom;
        this.item = item;
    }

    protected ArrayList<Item> item;


    public Channel() {
    }
}
