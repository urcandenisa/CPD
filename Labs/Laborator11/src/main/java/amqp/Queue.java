package amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Queue {
    private String serverURI;
    ConnectionFactory factory;
    Connection connection;
    private Channel channel;

    public Queue(String serverURI) {
        this.serverURI = serverURI;
    }

    public void initialize() {
        factory = new ConnectionFactory();
        try {
            factory.setUri(this.serverURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void publish(String queue, String topic) {

        try {
            channel.queueDeclare(topic, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String message = "";
            channel.basicPublish("", topic, null, message.getBytes("UTF-8"));
            System.out.println("Client " + queue + " published to " + topic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String queue, String topic) {
        System.out.println("Client " + queue + " subscribed to " + topic);

        DeliverCallback deliverCallback = (consumer, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Client " + queue + " received a new message on topic " + topic);
        };

        try {
            channel.basicConsume(topic, true, deliverCallback, consumer -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}