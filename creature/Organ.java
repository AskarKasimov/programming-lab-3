package creature;

public record Organ(String name, String process) {
    public String doProcess() {
        return this.process;
    }

    @Override
    public String toString() {
        return "Орган: " + this.name + ", процесс: " + this.process;
    }
}
