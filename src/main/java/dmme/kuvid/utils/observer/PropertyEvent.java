package dmme.kuvid.utils.observer;

public class PropertyEvent {
    private final String propertyName;
    private final Object oldValue;
    private final Object newValue;
    private final Object source;

    @Override
    public String toString() {
        return "PropertyEvent{" +
                "propertyName='" + propertyName + '\'' +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                ", source=" + source +
                '}';
    }

    public PropertyEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.source = source;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Object getSource() {
        return source;
    }

}
