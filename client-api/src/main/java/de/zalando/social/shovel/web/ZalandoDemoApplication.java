package de.zalando.social.shovel.web;

import de.zalando.social.shovel.service.configuration.ServiceConfiguration;
import de.zalando.social.shovel.web.Fake.Customer;
import de.zalando.social.shovel.web.Fake.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan({"de.zalando.social.shovel.web", "de.zalando.social.shovel.web.configuration"})
//@EnableScheduling
@Import({ServiceConfiguration.class})
public class ZalandoDemoApplication
//{
        implements CommandLineRunner
 {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private JmsTemplate template;



    public static void main(String[] args) {
        SpringApplication.run(ZalandoDemoApplication.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
//        this.repository.insert(new Customer("soroosh","sarabadani"));
//        this.repository.findAll();
        this.template.convertAndSend("dest1","soroosh");
    }
}
