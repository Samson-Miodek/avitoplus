package ru.sadykoff.avitoplus.entity;


import lombok.Data;

//посчитаная информация, не забыть в сервисе при добавление нового добавить фуннкционлал созранения старого
@Data
public class ClusterInfo {
    private Integer count;
    private Double mean;
    private Double std;
    private Double min;
    private Double max;

    @Override
    public String toString() {
        return "ClusterInfo{" +
                "count=" + count +
                ", mean=" + mean +
                ", std=" + std +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
