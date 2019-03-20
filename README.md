#This example demostrates how to run web service on apache karaf and secure it with keycloak

##Requirements
* Java 8
* Maven >= 3.3.6
* Docker >=  18.09.2
* docker-compose >= 1.17.1

## Run
```
./buildAndRun.sh
```

or 
```
#build
mvn clean install
#run
docker-compose up -d
#test
TOKEN=$(curl -d 'client_id=ui' -d 'username=test-user' -d 'password=test-user' -d 'grant_type=password' 'http://localhost:8080/auth/realms/Test-realm/protocol/openid-connect/token' | jq -r '.access_token' )
curl -X GET -H "Authorization: bearer $TOKEN" -H "Content-Type: application/json" http://localhost:8181/cxf/tasks/
```