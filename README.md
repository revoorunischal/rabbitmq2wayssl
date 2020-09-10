# rabbitmq2wayssl
This Document explains a way to enable your rabbitmq server in 2 way ssl using self signed keys without any Certifying authority(CA) keys 

**Version tested on 
RabbitMQ 3.8.8
Erlang 23.0.3**

rabbitmq application can be installed using any of the options mentioned in here 
https://www.rabbitmq.com/download.html

Once installed and started using 

rabbitmq-server 

Following management port will be enabled and can be tested at http://localhost:15672/ . Also non TLS port 5672 will be enaled. 


Now to enable 2 way ssl please stop the server. And do the following 
1. Enable truststore plugin using 

   rabbitmq-plugins enable  rabbitmq_trust_store
   
2. Create the keys required for rabbitmq and client using keyGen.txt 

3. Modify the conf file at /usr/local/etc/rabbitmq.conf  (In mac/ubuntu)(Check here for your conf file location https://www.rabbitmq.com/configure.html)

listeners.ssl.default = 5671 //Port on which ssl will be enabled on 
ssl_options.cacertfile = /Users/nischal/Documents/keys2/empty.pem   //Mention an empty file here because this is mandatory for rabbitmq ssl 
trust_store.directory        = /Users/nischal/Documents/keys2/rabbit-truststore // Directory where public keys of trusted servers exists.
ssl_options.certfile   = /Users/nischal/Documents/keys2/rabbit-public.pem //Public key generated above  
ssl_options.keyfile    = /Users/nischal/Documents/keys2/rabbit-private.pem  //Private key generated above 
ssl_options.verify     = verify_peer  // Informing rabbitmq to verify client keys
ssl_options.fail_if_no_peer_cert = true // Informing rabbitmq to stop accepting the request from client if keys are not matched.


I have attached a set of keys which can be tested in a tar file. Please raise an issue if you face any issue.


**P.S rabbitmq_trust_store fails if started as a brew services with eperm issue but will work fine by starting in command line **
