package com.himedia;

import org.springframework.context.support.GenericXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //기존 자바 클래스 객체 생성 및 실행
        WalkClass wc = new WalkClass();
        wc.move();

        //스프링 프레임에서는 위와같이 new를 이용한 객체 생성은 많이하지 않는다.
        //프로그램 시작시에 객체를 미리 생성&보관하고 필요할때 꺼내쓰는 방법을 주로 사용.

        //미리 만들어 놓은 객체를 보관하는 곳을 "스프링 컨테이너"라고 부른다.
        //현재 프로젝트에서스프링 컨테이너는 resource폴더 안에 applicationContext.xml 파일을 만들고
        //그 파일을 스프링 컨테이너로 사용할 예정

        //스프링 컨테이너에 담겨있는 bean을 필요할때 꺼내 쓰려면 아래와 같이 컨테이너의 사용권한을 갖고 있는 객체를 생성하여 사용
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");

        //꺼내는 방법은 기존 new인스턴스를 레퍼런스 변수에 저장하듯, ctx로 꺼내 빈ㄴ을 레퍼런스 변수에 저장하여 사용
        WalkClass wc2 = ctx.getBean("cWalk", WalkClass.class);

        wc2.move();
        ctx.close();

        //아직까지 일반 자바 프로젝트에서 사용하는 new WalkClass() 와 사용상 차이점은 없어 보이기도 하고
        //오히려 더 불편해보이기도한다.
        //하지만 차이점이라면,  new 인스턴스를 사용하면 사용할때마다 새로운 인스턴스가 생성되는 반면
        //getBean() 은 싱글턴 방식 처럼 하나의 생성된 객체가 계속 사용된다는 점이 다르다.
        //이들의 1차적인 사용목적은 무분별한 new 사용을 차단하여 실행환경의 과부하를 줄이기위함이다.
        //또한 스프링 프레임워크의 스프링 컨테이너의 사용은 점차적으로 자동화되고 간결해질 예정
    }
}