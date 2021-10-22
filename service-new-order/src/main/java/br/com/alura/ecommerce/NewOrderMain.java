package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (KafkaDispatcher orderDispatcher = new KafkaDispatcher<Order>()) {
            try(var emailDispatcher = new KafkaDispatcher<Email>()){
                for (int i = 0; i < 10; i++) {
                    String userId = UUID.randomUUID().toString();
                    String orderId = UUID.randomUUID().toString();
                    BigDecimal amount = new BigDecimal(Math.random() * 5000 +1);
                    Order order = new Order(userId,orderId,amount);
                    var email = new Email("New Order","Thank you for your order");
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", email.getSubject(), email);
                }
            }
        }
    }
}
