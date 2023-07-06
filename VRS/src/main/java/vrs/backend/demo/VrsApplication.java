package vrs.backend.demo;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VrsApplication {

	public static void main(String[] args) {
		MercadoPagoConfig.setAccessToken("TEST-5476971068198817-062719-e803d7431da3e762b7ef9734a87f762f-589247118");
		SpringApplication.run(VrsApplication.class, args);
	}

}
