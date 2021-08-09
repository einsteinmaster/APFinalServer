In the Terminal run
```
git pull && sudo docker build -t apf . && sudo docker stop apfcont && sudo docker run -p 80:8080 -d --name apfcont apf
```
