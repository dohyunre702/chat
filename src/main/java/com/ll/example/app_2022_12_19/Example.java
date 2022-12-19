package com.ll.example.app_2022_12_19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//IntStream, filter 설명
public class Example {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person(2, 22, "홍길순"));
        people.add(new Person(1, 23, "홍길동"));
        people.add(new Person(50, 50, "임꺽정"));

        int foundIdx = -1;

        //번호(id)가 2인 사람의 순서(index)는? : 반복문
        for(int i = 0; i < people.size(); i++) {
            if(people.get(i).id == 1)
                break;
        }
        System.out.printf("foundIdx : %d\n", foundIdx);

        //번호(id)가 2인 사람의 순서(index)는? : intStream
        IntStream.range(0,people.size())
                .filter(i -> i != 1) // i가 1이 아니면
                .forEach(i -> System.out.println(i)); //i가 호출된다

        //
        int index = IntStream.range(0,people.size())
                .filter(i -> i != 1) // 식을 만족하는 사람이 단 한명이라도 있을 때
                .findFirst() //첫 번째 값 반환
                .orElse(-1); //찾지 못하면 반환
    }
}

class Person {
    int id;
    int age;
    String name;

    public Person(int id, int age, String name) {
    }
}
