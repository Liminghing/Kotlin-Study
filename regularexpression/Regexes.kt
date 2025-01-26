package com.jkweyu.regularexpression


fun main() {
    // toRegex() 확장 함수를 통해 새로운 인스턴스 생성
    val pattern = "^The".toRegex()

    // containsMatchIn 메서드로 일치 항목 찾기
    println("^The".toRegex().containsMatchIn("The"))

    // splite() 메서드로 숫자를 나타내는 \d 정규식기준을 분리하기
    println("""\d""".toRegex().split("abc2abc4abc"))

    // 대소문자를 구분하지 않고 매칭
    println("RegexOption.IGNORE_CASE")

    val igPattern = "hello"
    val igRegex = igPattern.toRegex(RegexOption.IGNORE_CASE)

    println(igRegex.matches("HELLO")) // true
    println(igRegex.matches("Hello")) // true


    // 정규식의 ^와 $ 문자가 문자열의 각 줄의 시작과 끝을 나타냄
    println("RegexOption.MULTILINE")

    val mPattern = "^hello$"
    val mRegex = mPattern.toRegex(RegexOption.MULTILINE)

    val mInput = """
        hello
        world hello world
        world
        hello
    """.trimIndent()

    println(mRegex.findAll(mInput).count()) // 2: 각 줄의 시작과 끝에서 매칭


    // 정규식을 문자 그대로 처리 (특수 기호와 메타 문자의 기능 무시)
    println("RegexOption.LITERAL")

    val lPattern = "a.b"
    val lRegex = lPattern.toRegex(RegexOption.LITERAL)

    println(lRegex.matches("a.b")) // true: '.'을 문자 그대로 인식
    println(lRegex.matches("acb")) // false


    // 줄바꿈 문자로 '\n'만 허용
    println("RegexOption.UNIX_LINES")

    val ulPattern = "hello$"
    val ulRegex = ulPattern.toRegex(setOf(RegexOption.UNIX_LINES, RegexOption.MULTILINE))

    val ulInput = "hello\r\nworld\nhello"

    println(ulRegex.findAll(ulInput).count()) // 1: '\n'에서만 줄바꿈 매칭


    // 정규식 내 주석을 허용하고, 공백(스페이스)을 무시
    println("RegexOption.COMMENTS")

    val cPattern = """
        a   # Match 'a'
        b   # Match 'b'
    """.trimIndent()

    val cRegex = cPattern.toRegex(RegexOption.COMMENTS)

    println(cRegex.matches("ab")) // true: 공백과 주석은 무시됨


    // . 문자가 모든 문자와 매칭되도록 설정
    println("RegexOption.DOT_MATCHES_ALL")

    val dmaPattern = "a.*b"
    val dmaRegex = dmaPattern.toRegex(RegexOption.DOT_MATCHES_ALL)

    val dmaInput = "a\nb"

    println(dmaRegex.matches(dmaInput)) // true: '\n'도 매칭


    // 유니코드 캐논 등가성(문자 조합과 단일 문자)을 고려하여 매칭
    println("RegexOption.CANON_EQ")

    val cePattern = "\u00E9" // é (단일 문자)
    val ceRegex = cePattern.toRegex(RegexOption.CANON_EQ)

    val ceInput = "e\u0301" // e + '́' (조합 문자)

    println(ceRegex.matches(ceInput)) // true: 단일 문자와 조합 문자를 등가로 처리

}
