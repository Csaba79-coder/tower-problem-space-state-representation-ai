package com.csaba79coder.model;

public class Element {

    private String name;
    private int weight;
    private String position;

    public Element(String name, int weight, String position) {
        this.name = name;
        this.weight = weight;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", position='" + position + '\'' +
                '}';
    }
}
