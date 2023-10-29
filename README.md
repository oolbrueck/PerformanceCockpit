
## Performance Cockpit

### Get mongo Database:
docker pull mongo  
docker create mongo  
docker ps -a  
docker run --name mongoDB -d -p 27017:27017 mongo  

### Get mongo Test-Database:
docker pull mongo  
docker create mongo  
docker ps -a  
docker run --name mongoTestDB -d -p 27018:27017 mongo  