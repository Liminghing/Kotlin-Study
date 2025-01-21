package com.jkweyu.designpatternstudy.BehavioralPatterns.StrategyPattern

/*
 * - **`ImageFormatter` 인터페이스**
 *
 *     사용하는 클래스들에서 공통적으로 구현해야하는 `format()` 정의
 *
 *     - `format()` 메서드
 *
 *         `Image` 타입의 `image` 객체를 받아 `Image` 타입 반환
 */
interface ImageFormatter{
    fun format(image : Image): Image
}

/*
 *- **`PngFormatter` 클래스**
 *
 *     `ImageFormatter` 을 상속받음
 *
 *     - `format()` 오버라이딩
 *
 *         `override fun format(image: Image) = Image()` 을 통해 `PngFormatter` 타입의 변환된 `Image()` 반환
 */
class PngFormatter: ImageFormatter {
    override fun format(image: Image) = Image()
}

/*
 * - **`JpegFormatter` 클래스**
 *
 *     `ImageFormatter` 을 상속받음
 *
 *     - `format()` 오버라이딩
 *
 *         `override fun format(image: Image) = Image()` 을 통해 `JpegFormatter` 타입의 변환된 `Image()` 반환
 */
class JpegFormatter: ImageFormatter {
    override fun format(image: Image) = Image()
}

/*
 * - **`Image` 클래스**
 *
 *     Image 객체를 표현하는 클래스
 *
 *     - `convert()` 메서드
 *         - `(formatter: ImageFormatter) : Image`
 *
 *             `ImageFormatter` 인터페이스 형태의 `formatter` 변수를 받아 `Image` 타입 반환
 *
 *         - `formatter.format(this)`
 *
 *             전달받은 `formatter` 객체의 `format()`를 실행 + 현재 이미지 객체(`Image`)를 전달함
 */
class Image {
    fun convert(formatter: ImageFormatter): Image = formatter.format(this)
}

/*
 * - **`main()` 블록**
 *     - `val image = Image()`
 *
 *         `Image()` 타입의 원본 객체 생성
 *
 *     - `val pngImage = image.convert(PngFormatter())`
 *
 *         원본 `image` 의 `convert()` 메서드를 통해 `PngFormatter` 를 전달 → `PngFormatter`변환된 `Image` 객체 반환받음
 *
 *     - `val jpegImage = image.convert(JpegFormatter())`
 *
 *         원본 `image` 의 `convert()` 메서드를 통해 `JpegFormatter` 를 전달 → `JpegFormatter`변환된 `Image` 객체 반환받음
 */
fun main(){
    val image = Image()
    val pngImage = image.convert(PngFormatter())
    val jpegImage = image.convert(JpegFormatter())
}