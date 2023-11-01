
## Performance Cockpit

### Manage MongoDB image with Docker
docker pull mongo  
docker create mongo  
docker ps -a

#### Run MongoDB Database:
docker run --name mongoDB -d -p 27017:27017 mongo  

#### Run MongoDB Test-Database:
docker run --name mongoTestDB -d -p 27018:27017 mongo  

### Run Application
use maven plugin javafx:run to run the app