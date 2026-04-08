package bg.zemyanadlan.domain.event;

import java.time.LocalDate;

public class Event {
    private final String name;
    private final LocalDate date;
    private final EventType type;

    public Event(String name, LocalDate date, EventType type) {
        this.name = name;
        this.date = date;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public EventType getType() {
        return type;
    }
}
