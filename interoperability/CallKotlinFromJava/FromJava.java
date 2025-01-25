package com.jkweyu.interoperability.CallKotlinFromJava;

import java.util.List;

public class FromJava {
    /* 코틀린 기본 함수 호출
     * Kotlin파일명 + Kt 를 추가해 호출
     */
    public void callAddFunction() {
        CallKotlinKt.add(5,5);
        int result = CallKotlinKt.addAndReturn(5,5);
        System.out.print("From Kotlin " + result);
    }

    /* 코틀린 확장 함수 호출
     * Kotlin파일명 + Kt 를 추가해 호출
     */
    public void callExtensionFunction(){
        int i = 5;
        int result = CallKotlinKt.doubleTheValue(i);
        System.out.print("Kotlin doubleTheValueEx of "+ i +" = "+ result);
    }

    /* 코틀린 가변 컬레션이 있는 함수 호출
     * 코틀린은 원시 데이터 유형에 익숙하지 않음 → 코틀린(Int)는 자바에서 Integer 로 받는다
     */
    public void callGetMutableList() {
        System.out.print("Kotlin mutable list");
        List<Integer> listFromKotlin = CallKotlinKt.getMutableList();
        listFromKotlin.add(6);
        for (int i = 0; i < listFromKotlin.size(); i++) {
            System.out.print("Element " +  listFromKotlin.get(i));
        }
    }

    /* 코틀린 불변 컬레션이 있는 함수 호출
     *
     */
    public void callGetImmutableList() {
        System.out.println("Kotlin immutable list");
        List<Integer> listFromKotlin = CallKotlinKt.getImmutableList();
        for (int i = 0; i < listFromKotlin.size(); i++) {
            System.out.println("Element " +  listFromKotlin.get(i));
        }
    }

    /* 코틀린 JVM 어노테이션이 있는 함수1 : 코틀린 파일에 다른 이름 지정
     * 코틀린파일에서 지정한 KotlinUtil 어노테이션으로 파일에 접근
     */
    public void  WithJvmAnnotationInFile(){
        KotlinUtil.addition(4,4);
    }

    /* 코틀린 JVM 어노테이션이 있는 함수2 : 코틀린 함수에 다른 이름 지정
     * 코틀린파일에서 지정한 addDouble 어노테이션으로 함수에 접근
     */
    public void  WithJvmAnnotationInFuntion(){
        KotlinUtil.addDouble(5.0, 5.0);
    }

    /* 코틀린 클래스 호출
     * 자바 클래스의 인스턴스 생성법과 동일한 방법으로 코틀린 클새스 인스턴스 생성
     */
    class CallKotlinClass {
        public void callShpaeInstance() {
            Shape shape = new Shape(5,9,"Shape");
            shape.shapeMessage();
            System.out.println(shape.getShape() + " width " + shape.getWidth());
            System.out.println(shape.getShape() + " height " + shape.getHeight());
            System.out.println(shape.getShape() + " area " + shape.getArea());

            shape.draw();
        }
    }

    /* 코틀린 싱글톤 클래스 호출1 : 싱글톤 클래스의 메서드 어노테이션 미지정
     * INSTANCE 키워드를 통해 싱글톤 객체에 접근
     */
    public void callSingleton() {
        Singleton.INSTANCE.happy();
    }

    /* 코틀린 싱글톤 클래스 호출2 : 싱글톤 클래스의 메서드 어노테이션 지정
     * INSTANCE 키워드없이 접근가능
     */
    public void callSingletonWithAnnotation() {
        SingletonWithAnontation.excited();
    }


}
