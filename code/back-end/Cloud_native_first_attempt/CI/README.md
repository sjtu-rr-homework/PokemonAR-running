## May 20,2019,CI

### Step one:build jenkins

[Docker hub link](<https://hub.docker.com/r/macoredroid/jenkins>)

Create a new folder.<br>

Put your files in this kind of structure <br>

[![Capture2.png](https://i.postimg.cc/Y25y2Z5n/Capture2.png)](https://postimg.cc/mhSS8X2C)<br>

When it comes to the downloads folder, you have to download<br>

[apache-maven-3.5.0-bin.tar.gz](https://archive.apache.org/dist/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz)<br>

[jdk-7u76-linux-x64.tar](http://mirror.cnop.net/jdk/linux/jdk-7u76-linux-x64.tar.gz)<br>

[jdk-8u131-linux-x64.tar](http://mirror.cnop.net/jdk/linux/jdk-8u131-linux-x64.tar.gz)<br>

So fork this repo in the folder first, then head to the CI folder under the repo<br>

Then create a downloads folder and put three files which were mentioned above into the downloads floder.<br>

In this folder, open terminal and run<br>

```shell
sudo docker run -p 8080:8080  -v `pwd`/downloads:/var/jenkins_home/downloads -v `pwd`/jobs:/var/jenkins_home/jobs/ -v /var/run/docker.sock:/var/run/docker.sock --rm --name myjenkins macoredroid/jenkins:forth
```

Visit http://localhost:8080/ you will see all jobs right there<br>

[![Capture3.png](https://i.postimg.cc/wMhsg1Nk/Capture3.png)](https://postimg.cc/RJZ0TVX3)

Amazing!<br>

Then run the tools first to automatically install maven and jdk to the docker image.<br>

Then you can start the pipeline to run Job1,Job2,Job3 one after another thanks to the stream.<br>

We are following this [Guide](<https://dzone.com/articles/dockerizing-jenkins-2-setup-and-using-it-along-wit>)<br>

And this [Guide](<https://dzone.com/articles/dockerizing-jenkins-2-setup-and-using-it-along-wit>)



### Step two: make our service available to outer the Internet 

#### tools 

oray5<br>

#### Detailed Step

##### Download

download oray5 for ubuntu/linux in [downloadOrya5](<https://hsk.oray.com/download/>)

##### Install 
in the download directory
```shell
sudo dpkg -i phddns_3.0_x86_64.deb
```
##### Use 
visit [downloadOrya5](<https://b.oray.com/passport/login?url=https%3A%2F%2Fb.oray.com%2F>)<br>

register and login in the oray<br>

select the Inner 'net penetration' tag, add a mapping<br>

finish configure such as your ip address and your port.oray will distribute a domain name for this server and we can visit our service by this domain name through internet like this

 ![image](https://i.postimg.cc/RZ2ZBKxm/TIM-20190519170203.jpg)

We are following this [Guide](<http://service.oray.com/question/4287.html>)<br>