package demo;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/18 17:07
 */

import com.alibaba.fastjson.JSON;
import com.xfh.bingo.model.JsonResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @param
 * @param
 * @return
 */
public class tesrrisk {

    public static void main(String[] args) {
        Boolean flag = false;
        try {
            stu stu = new stu();
            stu.setData(1);
            //flag = stu.getFlag();
            //flag = firstp2pApiService.checkUser(agentInfoModel).getData();
        }catch(Exception e){
           System.out.println("11");
        }
    System.out.println(flag);
    }


    static class stu<T>{

        public T flag;
        public stu<T> setData(T data) {
            this.flag = data;
            return this;
        }
        public  T getFlag(){
            return flag;
        }
    }
}
