package models;

import java.util.HashMap;

public class Discipline {
    private String code;
    private String name;
    private double workload;

    private HashMap<String, Discipline> dependencies = new HashMap<>();

    public Discipline() {
    }

    public Discipline(String code, String name, double workload) {
        this.code = code;
        this.name = name;
        this.workload = workload;
    }

    public void addDependency(Discipline discipline) {
        if (!dependencies.containsKey(discipline.getCode()))
            dependencies.put(discipline.getCode(), discipline);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", workload=" + workload +
                '}';
    }
}
