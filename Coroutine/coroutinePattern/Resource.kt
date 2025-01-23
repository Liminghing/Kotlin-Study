package com.jkweyu.coroutinepractice.coroutineEx

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileReader

fun main() = runBlocking {
//    launch {
//        val fileReader = FileReader("path")
//        /*
//         * - `try{…}` 블록
//         *
//         *     장기간의 입출력 기능 수행 (자원사용)
//         */
//        try {
//            delay(10000)
//            fileReader.read()
//        }
//        /*
//         * - `catch{…}` 블록
//         *
//         *     장기간의 입출력 기능수행과정에서 예외 발생 여부와 상관 없이 항상 실행 (자원해제)
//         */
//        finally {
//            fileReader.close()
//        }
//    }
}