
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

### Tomcat
If the App is not stopped correctly, it can be necessary  
to stop the Tomcat Server manually to prevent a blockage of port 8080  
#### Windows Powershell:
netstat -ano | findstr :8080  
taskkill /F /PID <PID>