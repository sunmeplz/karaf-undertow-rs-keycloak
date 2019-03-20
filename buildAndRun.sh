docker-compose down
mvn clean install
docker-compose up -d

echo Waiting 10 sec until up
sleep 10
#get token
echo "Getting token"
TOKEN=$(curl -d 'client_id=ui' -d 'username=test-user' -d 'password=test-user' -d 'grant_type=password' 'http://localhost:8080/auth/realms/Test-realm/protocol/openid-connect/token' | jq -r '.access_token' )
echo "Token is $TOKEN"
echo Calling API
curl -X GET -H "Authorization: bearer $TOKEN" -H "Content-Type: application/json" http://localhost:8181/cxf/tasks/