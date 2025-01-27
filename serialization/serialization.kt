package com.jkweyu.serialization.serialization



import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


import kotlinx.serialization.json.Json

//@Serializable 어노테이션을 사용해 클래스 직렬화
@Serializable
data class Person(
    var id : Int = 0,
    var first_name : String,
    var last_name : String,
    var phones : List<String> = listOf()
)
//@SerialName 어노테이션을 프로퍼티에 선언후 이름 설정
@Serializable
data class Person02(
    var id : Int = 0,
    @SerialName("FirstName") var first_name : String,
    @SerialName("LastName") var last_name : String,
    var phones : List<String> = listOf()
)


fun main(){
    val person = Person(
        id = 1,
        first_name = "John",
        last_name = "Doe",
        phones = listOf("123-456-7890", "987-654-3210")
    )

    /* 직렬화
     * Json 객체의 encodeToString() 메서드에 직렬화 클래스 인스턴스 제공
     */
    val json: String = Json.encodeToString(person)
    println(json)

    /* 역직렬
     * Json 객체의 decodeFromString() 메서드에 JSON 인스턴스 제공
     */
    val obj: Person = Json.decodeFromString<Person>(json)
    println(obj)

    /* 목록형 직렬화
     * Json 객체의 encodeToString() 메서드에 목록 제공
     */
    val peopleList = listOf(
        Person(id = 1, first_name = "John", last_name = "Doe", phones = listOf("123-456-7890", "987-654-3210")),
        Person(id = 2, first_name = "Jane", last_name = "Smith", phones = listOf("555-555-5555", "111-222-3333"))
    )
    val jsonList: String = Json.encodeToString(peopleList)
    println(jsonList)

    /* 클래식 스타일로 출력하기
     * Json 객체에 prettyPrint 옵션을 true 로 설정 후 해당 객체로 작업
     */
    var pJson = Json{
        prettyPrint = true
    }
    val jsonPretty: String = pJson.encodeToString(person)
    println(jsonPretty)

    // 프로퍼티명 SerialName 처리
    val person02 = Person02(
        id = 1,
        first_name = "John",
        last_name = "Doe",
        phones = listOf("123-456-7890", "987-654-3210")
    )

    val jsonPretty02: String = pJson.encodeToString(person02)
    println(jsonPretty02)

    // 사용자 정의 직렬화
    // 커스텀 직렬화
    val jsonString = pJson.encodeToString(PersonSerializer, person)
    println(jsonString)

    // 커스텀 역직렬화
    val deserializedPerson = pJson.decodeFromString(PersonSerializer, jsonString)
    println(deserializedPerson)
}
// 사용자 정의 직렬화기
/* object PersonSerializer : KSerializer<Person> {…} 구조
 * `KSerializer<Person>` : Kotlinx Serialization에서 제공하는 인터페이스
 *
 * → `Person` 클래스의 직렬화/역직렬화를 자체적으로 정의
 */
object PersonSerializer : KSerializer<Person> {
    /* 직렬화에 사용할 구조 정의
     * - 구조 정의 (`descriptor`)
     *
     *     `descriptor` 는 커스텀 데이터 구조를 정의
     *
     *     - `buildClassSerialDescriptor()`
     *
     *         `“Person”` 이라는 시리얼 이름으로 구조체 설정
     *
     *         - `element<Int>("id")` : “`id`”라는 요소를 Int형식으로 지정
     *         - `element<String>("name")` : “`name`”라는 요소를 String형식으로 지정
     *         - `element<List<String>>("phones")` : “`phones`”라는 요소를 List<String>형식으로 지정
     */
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Person") {
        element<Int>("id")
        element<String>("name")
        element<List<String>>("phones")
    }

    /* 직렬화 로직
     * - 직렬화 로직 (`serialize`)
     *
     *     `serialize` 재정의를 통해 커스텀 직렬화 로직
     *
     *     - encoder : 직렬화를 수행하는 `Encoder` 객체
     *     - value : 직렬화할 `Person` 객체
     *     - `val composite = encoder.beginStructure(descriptor)`
     *
     *         `Encoder` 객체의 `beginStructure()` 메서드에 앞에서 정의한 구조 전달
     *
     *         → `descriptor`에 정의된 구조에 따라 데이터를 직렬화를 할 `composite`
     *
     *     - `composite.encodeXXXElement()`
     *
     *         `encodeXXXElement` 함수를 사용해 각 필드를 직렬화
     *
     *         - `encodeIntElement(...)` 메서드
     *
     *             `descriptor` 에정의한 구조에 `Person` 객체의 `id`를 직렬화
     *
     *         - `encodeStringElement(...)` 메서드
     *
     *             `descriptor` 에정의한 구조에 `Person` 객체의 `first_name`과 `last_name`를 합쳐서 직렬화
     *
     *         - `encodeSerializableElement(...)` 메서드
     *
     *             `ListSerializer(String.serializer())`를 사용해 `Person` 객체의 `List<String>` 타입 직렬화
     *
     *         - `endStructure(descriptor)` 메서드
     *
     *             직렬화 작업을 종료
     */
    override fun serialize(encoder: Encoder, value: Person) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeIntElement(descriptor, 0, value.id)
        composite.encodeStringElement(descriptor, 1, "${value.first_name} ${value.last_name}")
        composite.encodeSerializableElement(descriptor, 2, ListSerializer(String.serializer()), value.phones)
        composite.endStructure(descriptor)
    }

    /* 역직렬화 로직
     * - 역직렬화 로직 (`deserialize`)
     *
     *     `deserialize` 재정의를 통해 커스텀 직렬화 로직
     *
     *     - decoder : 역직렬화를 수행하는 `Decoder` 객체
     *     - `val dec = decoder.beginStructure(descriptor)`
     *
     *         `Decoder` 객체의 `beginStructure()` 메서드에 앞에서 정의한 구조 전달
     *
     *         → `descriptor`에 정의된 구조에 따라 데이터를 직렬화를 할 `dec`
     *
     *     - `Person` 객체의 프로퍼티 정의
     *
     *         `id` , `name` , `phones`
     *
     *     - `loop@ while (true) {...}`
     *
     *         반복문을 돌면서 `dec` 의 `decodeElementIndex()` 에 `descriptor` 구조 전달 → 현재 읽을 데이터 필드의 인덱스를 반환
     *
     *         - `decodeXXXElement()` 메서드로 각 필드를  읽기
     *             - `id` : `decodeIntElement`로 읽음.
     *             - `name` : `decodeStringElement`로 읽음.
     *             - `phones` :  `decodeSerializableElement`로 리스트를 역직렬화해서 읽음
     *         - `CompositeDecoder.DECODE_DONE`
     *
     *             데이터 읽기가 끝나면 루프 탈출
     *
     *     - `dec.endStructure(descriptor)`
     *
     *         역직렬화 작업을 종료
     *
     *     - `val (firstName, lastName) = {...}`
     *
     *         역직렬에서 가져온 `name` 을 `name.split(" ")`로 `name`을 공백으로 나눔 → 첫 번째 단어는 `firstName`, 두 번째 단어는 `lastName`
     *
     *     - `Person` 객체 반환
     *
     *         `Person(id, firstName, lastName, phones)`를 반환
     */
    override fun deserialize(decoder: Decoder): Person {
        val dec = decoder.beginStructure(descriptor)
        var id = -1
        var name = ""
        var phones: List<String> = emptyList()

        loop@ while (true) {
            when (val index = dec.decodeElementIndex(descriptor)) {
                0 -> id = dec.decodeIntElement(descriptor, 0)
                1 -> name = dec.decodeStringElement(descriptor, 1)
                2 -> phones = dec.decodeSerializableElement(descriptor, 2, ListSerializer(String.serializer()))
                CompositeDecoder.DECODE_DONE -> break@loop
                else -> throw SerializationException("Unexpected index: $index")
            }
        }
        dec.endStructure(descriptor)

        val (firstName, lastName) = name.split(" ").let {
            it[0] to it.getOrElse(1) { "" }
        }

        return Person(id, firstName, lastName, phones)
    }
}
