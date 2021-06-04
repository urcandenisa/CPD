package broker;

import amqp.Queue;
import model.Topics;
import socket.client.Client;

import java.time.LocalDateTime;
import java.util.List;

public class TokenHandler {

    private static final String SERVER_URI = "amqps://vnbmuppp:fLpknlWNnz1wykzm9Q1v9SQIpIma2uuv@cow.rmq2.cloudamqp.com/vnbmuppp";
    private Queue queue;

    public TokenHandler() {
        queue = new Queue(SERVER_URI);
    }

    public void passToken(List<Client> clientList) {

        int randomClient = (int) ((clientList.size()) * Math.random());

        int i = randomClient;

        while (true) {
            clientList.get(i).setHasToken(true);

            queue.initialize();
            LocalDateTime receivedTime = LocalDateTime.now();
            LocalDateTime sendTime = receivedTime.plusSeconds(5);

            int numberOfPublishTopics = 0;
            int numberOfSubscribedTopics = 0;

            while (receivedTime.isBefore(sendTime)) {

                System.out.println(clientList.get(i).sendMessage("hello from client " + (i + 1)));
                if (i != 0) {
                    System.out.println(clientList.get(i - 1).sendMessage("hello from client " + i));
                }

                Topics topic = Topics.randomTopic();

                if (numberOfSubscribedTopics < 1) {
                    this.queue.subscribe(String.valueOf(clientList.get(i).getId()), topic.name());
                    numberOfSubscribedTopics++;
                }

                if (numberOfPublishTopics < 2) {
                    if (clientList.get(i).hasToken()) {
                        this.queue.publish(String.valueOf(clientList.get(i).getId()), topic.name());
                        numberOfPublishTopics++;
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                receivedTime = LocalDateTime.now();
            }

            clientList.get(i).setHasToken(false);

            if (i == clientList.size() - 1) {
                i = 0;
                clientList.get(0).setHasToken(true);
            } else {
                clientList.get(i + 1).setHasToken(true);
            }
            i++;

        }
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}
