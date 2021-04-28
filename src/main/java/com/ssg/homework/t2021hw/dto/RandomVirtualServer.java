package com.ssg.homework.t2021hw.dto;

import java.util.Random;

public class RandomVirtualServer {

    Random random;

    public String connectVirtualSever(){
        this.random = new Random();
        //랜덤함수 0-9까지 생성
        int temp_value = random.nextInt(10);
        
        //10가지 경우의 수중 0/1 즉 2가지 경우에만 서버수행 실패
        if(temp_value == 0 || temp_value == 1){
            return "N";
        }
        return "Y";
    }

}
