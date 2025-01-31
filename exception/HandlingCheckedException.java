package com.jkweyu.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

// Checked 예외
// RuntimeException 클래스를 상속받지 않은 예외 → 복구 가능성이 있는 예외
public class HandlingCheckedException {
    // try{...} catch{...} 블록을 사용해 Checked 예외 처리
    public void ControlException01() {
        try {
            new FileInputStream("invalid/path");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // throws{...} 블록을 사용해 Checked 예외 처리
    public void ControlException02() throws FileNotFoundException {
        new FileInputStream("invalid/path");
    }
}
