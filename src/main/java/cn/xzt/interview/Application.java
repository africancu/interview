package cn.xzt.interview;

import cn.xzt.interview.common.utils.FileUploadUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@MapperScan("cn.xzt.interview.mapper")
@Controller
public class Application {

    public static void main(String[] args) {




        SpringApplication.run(Application.class, args);
    }
}
