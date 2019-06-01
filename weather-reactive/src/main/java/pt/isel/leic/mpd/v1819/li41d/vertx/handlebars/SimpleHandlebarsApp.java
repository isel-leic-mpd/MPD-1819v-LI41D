package pt.isel.leic.mpd.v1819.li41d.vertx.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.time.LocalDate;


public class SimpleHandlebarsApp {


    public static void main(String[] args) throws IOException {
        WeatherInfo wi = new WeatherInfo(LocalDate.now(), 37, 28);


        Handlebars handlebars = new Handlebars();

        final Template template = handlebars.compile("mytemplate");

        final String result = template.apply(wi);


        System.out.println(result);




    }
}
