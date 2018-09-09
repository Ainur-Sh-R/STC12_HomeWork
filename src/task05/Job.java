package task05;

import java.io.Serializable;

public enum Job implements Serializable {
    WORKER("worker"), PROGRAMMER("programmer"), BUILDER("builder");
    private final String name;

    Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
