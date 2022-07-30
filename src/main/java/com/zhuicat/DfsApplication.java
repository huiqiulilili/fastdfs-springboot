package com.zhuicat;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author zhuicat
 * @since 2022/7/30 14:09
 */
@Import(FdfsClientConfig.class)
// 解决 jmx 重复注册 bean 的问题
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class DfsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DfsApplication.class,args);
    }
}
