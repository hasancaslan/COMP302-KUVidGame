package dmme.kuvid.lib.types;

public class Key {
	private final ObjectType type;
	private final Enum subType;
	
	public Key(ObjectType type, Enum subType) {
		this.type=type;
		this.subType=subType;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return this.type == key.type && this.subType == key.subType;
    }

    @Override
    public int hashCode() {
        return type.hashCode()+subType.hashCode();
    }

    @Override
    public String toString() {
        return "Key [" +
                "type=" + type +
                ", subType=" + subType +
                ']';
    }
}
