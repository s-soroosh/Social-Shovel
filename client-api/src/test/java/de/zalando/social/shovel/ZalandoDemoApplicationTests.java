package de.zalando.social.shovel;

import de.zalando.social.shovel.web.ZalandoDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZalandoDemoApplication.class)
@WebAppConfiguration
public class ZalandoDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
