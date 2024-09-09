package com.yedam.common;

public class DebugUtil {
    
	 // ANSI 색상 코드
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    // 현재 파일명과 변수값을 출력하는 메서드
    public static void printcurrVal(String varName, Object value) {
        // 호출된 위치의 클래스명과 메서드명 가져오기
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[2].getFileName();
        String className = stackTrace[2].getClassName();
        
        System.out.println(ANSI_YELLOW + "[debug] [" + fileName + "] " + varName + " : " + value + " [debug]" + ANSI_RESET);
    }

    // 예외의 스택 트레이스를 출력하는 메서드 (디버깅용)
    public static void printException(Exception e) {
        e.printStackTrace();
    }
}