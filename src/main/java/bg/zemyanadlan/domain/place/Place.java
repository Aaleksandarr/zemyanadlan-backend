package bg.zemyanadlan.domain.place;

public class Place {
    private String name;
    private PlaceType type;

    public Place(String name, PlaceType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PlaceType getType() {
        return type;
    }
}

