package com.example.user.infra.adapters;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.github.cdimascio.dotenv.Dotenv;

public class RabbitMQ implements Queue {
   Connection client;
    public RabbitMQ() throws Exception {
        Dotenv dotenv = Dotenv.load();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(dotenv.get("rabbitmq_uri"));
        this.client = factory.newConnection();
    }

    public void publisher(String name, byte[] data) throws Exception {
        Channel channel = this.client.createChannel();
        channel.queueDeclare(name, false, false, false, null);
        channel.basicPublish("", name, null, data);
        channel.close();
    }
}
