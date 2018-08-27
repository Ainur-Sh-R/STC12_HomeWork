package task05;

public enum Job {
    WORKER("worker"), PROGRAMMER("programmer"), BUILDER("builder");
    private final String name;

    Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
