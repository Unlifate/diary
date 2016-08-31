package diary.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a diary entry.
 *
 */
public class Entry {

    private final StringProperty title;
    private final StringProperty content;

    /**
     * Default constructor.
     */
    public Entry() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param title
     * @param content
     */
    public Entry(String title, String content) {
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public StringProperty contentProperty() {
        return content;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }
    
}