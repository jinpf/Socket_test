#Learn Socket programing in java step by step

###Transfer message using socket

**Pay attention in these editions you must first start the server and then start the client.**

* First editon with *HelloServer.java* and *HelloClient.java* in `src/message/` which simply send and receive data.The first one must work with **one server and one client**.
* Second editon with *EchoServer.java* and *EchoClient.java* in `src/message/` which would work with loop and echo. The second edition can work with one server and several client，**but not at the same time**.
* Third editon with *EchoThread.java* and *EchoThreadServer.java* as server,also inneed *EchoClient.java*  as client in `src/message/` ,which would work with multithreading.SO in this edition ,**we can start one server and several client at the same time**.


###Transfer file using socket

* First editon with *FServer.java* and *FClient.java* in `src/file/` which you can transfer file with writing the file path on command line using FClient, and then receive file using FServer. Also Server can run with multithreading. And **don`t be afraid to receive file with the same name**, because when receiving file with the same name ,Server can automatically add `.rec`.
