package core;

// This class represents a key-value pair.
public class ComboItem {
    private int key;        // The key of the item
    private String value;   // The value of the item
    private String moreValue;
    // Constructor for ComboItem
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem(int key, String value, String moreValue) {
        this.key = key;
        this.value = value;
        this.moreValue = moreValue;
    }

    public String getMoreValue() {
        return moreValue;
    }

    public void setMoreValue(String moreValue) {
        this.moreValue = moreValue;
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
