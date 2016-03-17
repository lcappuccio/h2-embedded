
#!/bin/bash 
COUNTER=0
while [  ${COUNTER} -lt 100 ]; do
	data=`openssl rand -base64 32`
    curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"dataValue":"'${data}'"}' http://localhost:8080/api/data --user admin:admin_pwd
    let COUNTER=COUNTER+1 
done

