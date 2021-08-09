In the Terminal run
```
git pull && sudo docker build -t apf . && sudo docker rm -f apfcont && sudo docker run -p 80:8080 -d --name apfcont apf
```
