until $(curl -X GET http://localhost:9200/_cluster/health?wait_for_status=green > /dev/null); do
    printf 'WAITING FOR THE ELASTICSEARCH ENDPOINT BE AVAILABLE, trying again in 5 seconds \n'
    sleep 5
done

curl -s -X PUT -H "Content-Type: application/json" http://localhost:9200/domain/_doc/1 -d "{\"name\": \"gmail.com\"}"
curl -s -X PUT -H "Content-Type: application/json" http://localhost:9200/domain/_doc/2 -d "{\"name\": \"mail.ru\"}"
curl -s -X PUT -H "Content-Type: application/json" http://localhost:9200/domain/_doc/3 -d "{\"name\": \"yandex.ru\"}"