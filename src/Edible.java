public class Edible extends Item{
    private int rating;

    public Edible(String name, String description, double weight, int rating){
        super(name, description, weight);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
