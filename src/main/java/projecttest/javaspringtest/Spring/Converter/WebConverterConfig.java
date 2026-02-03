package projecttest.javaspringtest.Spring.Converter;


import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import projecttest.javaspringtest.Spring.Converter.formatter.MyNumberFormatter;

/**
 * 스프링은 내부에서 ConversionService 를 제공한다. 우리는 WebMvcConfigurer 가 제공하는
 * addFormatters() 를 사용해서 추가하고 싶은 컨버터를 등록하면 된다. 이렇게 하면 스프링은 내부에서
 * 사용하는 ConversionService 에 컨버터를 추가해준다.
 * 일반적으로 직접 추가한 컨버터가 우선순위가 높다
 * converter-view.html 에 타임리프에서 스프링 컨버터 적용함.
 * 타임리프에서 ${{  }} 로 컨버터 / 포멧터 사용가능
 */
@Configuration
public class WebConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IpPortToStringConverter());

        //추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
