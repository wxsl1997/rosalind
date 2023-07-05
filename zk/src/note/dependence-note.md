
# zookeeper

## cluster
``` 
sudo bin/zkServer.sh start cluster/zk01/conf/zoo.cfg
sudo bin/zkServer.sh start cluster/zk02/conf/zoo.cfg
sudo bin/zkServer.sh start cluster/zk03/conf/zoo.cfg

bin/zkCli.sh -server 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
```
## docker


