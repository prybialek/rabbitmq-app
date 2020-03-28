# Run RabbitMQ using docker
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5762:5762 rabbitmq:3-management
docker run -d -p 15672:15672 -p 5672:5672 image_id