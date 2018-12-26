package com.thymeleaf.thymeleaf;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.listener.MyCustomListener;
import com.thymeleaf.thymeleaf.listener.MyStartedListener;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
@SpringBootApplication
@ServletComponentScan  //注解注册Servlet：1，注册过滤器：2
public class ThymeleafApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ThymeleafApplication.class);
    }

    /**
     * 随机抛出异常
     */
    private void randomException() throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new NotFound()};
        //发生概率
        double probability = 0.75;
        if (Math.random() < probability) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }

    }
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ThymeleafApplication.class);
        application.addListeners(new MyStartedListener(),new MyCustomListener());//自定义监听器：4，加入监听

        application.run( args);
    }
    @RequestMapping("/")
    public String index(Model model, HttpSession session) throws Exception{//自定义session监听器2：要引入session才能进入到MySessionListener监听器中
        Person single = new Person("aa",11);
        List<Person> people = new ArrayList<Person>();
        Person p1 = new Person("xx",11);
        Person p2 = new Person("yy",22);
        Person p3 = new Person("zz",33);
        Person p4 = new Person("kk",44 );
        Person p5 = new Person("ff",55 );
        Person p6 = new Person("ff",55 );
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        people.add(p5);
        people.add(p6);
        randomException();
        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);
        return "index";
    }

    /**
     * 返回json字符串操作--3
     * @param model
     * @return
     */
    @RequestMapping(value = "/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String json(Model model) throws Exception{
        Person single = new Person("aa",11);
        List<Person> list = new ArrayList<Person>();
        list.add(single);
        randomException();
        model.addAttribute("persons",list);
        model.addAttribute("person",single);
        return "jsonView";
    }

    /**
     * 返回json字符串操作--1
     * @return
     */
    @Bean
    public BeanNameViewResolver beanNameViewResolver(){
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        return resolver;
    }

    /**
     * 返回json字符串操作--2
     * @return
     */
    @Bean
    public MappingJackson2JsonView jsonView(){
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        return jsonView;
    }

    /**
     * 直接返回对象的json字符串
     * @return
     */
    @RequestMapping("/getSome")
    @ResponseBody//注解是返回数据，不需要经过页面渲染
    public Person getSome(){
        Person p = new Person("qwe",22);
        String jsonObject = JSONObject.toJSONString(p, SerializerFeature.WriteMapNullValue);
        return p;
    }

    /**
     * 直接返回字符串 "TestSome" ,并不是转到TestSome.html页面
     * @return
     */
    @RequestMapping("/getStr")
    @ResponseBody
    public String getStr(){
        return "TestSome";
    }

    /**
     * 代码注册Servlet：1
     * @return

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean<ServletConf>(new ServletConf(),"/testDemo/*");
    }
     */
}

