package core;

// This class represents a key-value pair.
public class ComboItem {
    private int key;        // The key of the item
    private String value;   // The value of the item
    private String moreVelue;
    // Constructor for ComboItem
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem(int key, String value, String moreVelue) {
        this.key = key;
        this.value = value;
        this.moreVelue = moreVelue;
    }

    public String getMoreVelue() {
        return moreVelue;
    }

    public void setMoreVelue(String moreVelue) {
        this.moreVelue = moreVelue;
    }

    // toString() method returns the value of the item
    @Override
    public String toString() {
        return value;
    }

    // getKey() method returns the key value
    public int getKey() {
        return key;
    }

    // setKey() method sets the key value
    public void setKey(int key) {
        this.key = key;
    }

    // getValue() method returns the value field
    public String getValue() {
        return value;
    }

    // setValue() method sets the value field
    public void setValue(String value) {
        this.value = value;
    }
}
