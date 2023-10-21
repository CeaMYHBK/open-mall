package com.ceam.web;

import com.ceam.common.utils.ApplicationContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 腾讯位置服务https://lbs.qq.com/getPoint/
 * https://www.showdoc.com.cn/
 *
 * @author CeaM
 * 2023/01/27 09:44
 **/
@SpringBootApplication(scanBasePackages = {"com.ceam"})
public class CeaMMallApp {

    public static void main(String[] args) {
        SpringApplication.run(CeaMMallApp.class, args);
        System.out.println(
                        "    __  ____   __     __     __     \n" +
                        " //    ||     //\\    //\\    //\\    \n" +
                        "||     ||--- //--\\  //  \\  //  \\   \n" +
                        "  \\ __ ||___//    \\//    \\//    \\  \n" +

                        "\n(♥◠‿◠)ﾉﾞCMall启动成功ლ(´ڡ`ლ)ﾞ  \n");
    }

    @Bean
    public ApplicationContextHolder springContextHolder() {
        return new ApplicationContextHolder();
    }
}
