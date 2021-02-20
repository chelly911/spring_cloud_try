package cn.chen.myspringcloud;

import brave.sampler.Sampler;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableEurekaClient
public class ProductDataServiceApplication {
    public static void main(String[] args) {
        int port = 0;
        int defaultPort = 8001;
        Future<Integer> future = ThreadUtil.execAsync(() -> {
            int p = 0;
            System.out.println("请于5秒钟内输入端口号，推荐使用8001,8002,或者8003,超过" +
                    "5秒后将使用默认端口号" + defaultPort);
            Scanner scanner = new Scanner(System.in);
            while(true){
                String strPort = scanner.nextLine();
                System.out.println("strPort is :"+strPort);
                if(!NumberUtil.isInteger(strPort)) {
                    System.err.println("只能是数字");
                    continue;
                }else{
                    p = Convert.toInt(strPort);
                    scanner.close();
                    break;
                }
            }
            return p;
        });
        try{
            port = future.get(5, TimeUnit.SECONDS);
        }catch (InterruptedException | ExecutionException | TimeoutException e){
            port = defaultPort;
        }
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用，无法启动%n",port);
            System.exit(1);
        }
        new SpringApplicationBuilder(ProductDataServiceApplication.class).properties("server.port="+ port ).run(args);
    }
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
