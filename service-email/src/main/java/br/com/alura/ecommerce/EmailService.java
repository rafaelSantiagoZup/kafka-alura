package br.com.alura.ecommerce;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {
    public static void main(String[] args) throws InterruptedException {
        EmailService emailService = new EmailService();
        try(KafkaService service = new KafkaService("ECOMMERCE_SEND_EMAIL", EmailService.class.getName(), emailService::parse,Email.class, Map.of())){
            service.run();
        }
    }

    private void parse(ConsumerRecord<String,Email> record) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Processing new order,checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email processed");
    }
}
