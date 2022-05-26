public class SpecialRoom extends Room {
    private Item key;

    public SpecialRoom(String description, Item key){
        super(description);
        this.key = key;
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
    }


}
