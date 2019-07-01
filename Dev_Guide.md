# iHotel Sample Application Dev Guide
 
 The Project has 6 maven sub modules as follow: 
 
- **check:** The Credit Card Validation Service
- **common:** Common Logic Shared across the entire modules 
- **hotel:** The Hotel Portal
- **icheck:** Common Interface (RMI) used by **Payment** and **Check** Modules.
- **payment:** The Payment Service
- **stress:** The Load Stress Tool

## Requirements to run locally (For Development)
- JDK >= 8
- Intellij IDEA (Optional but recommended to use it as main IDE)
- Docker (Docker is used to run MySQL. If you do not want to use Docker then you should install MySQL locally in your device)

## Running The system locally
  - Clone the Repo [iHotel Repo on Github](https://github.com/JENNIFER-University/IHotel)
  - Open Intellij then select -> Open Project
  - Navigate to where you saved iHotel and open it.
  - After the project is open in intellij you should see the 6 modules of the project
  - Run `mvn install -P dev`
 
### 1. Starting the Database
- Startup the MySQL database before you run the system.
- From the project root, run the command
```docker
docker-compose up
```
- This should bring up MYSQL instance.
 
 
### 2. Running hotel 

- Make sure that the Database is up and running.
- If you are not going to use MySQL docker make sure to modify the [hotel/src/main/resources/context.xml](./hotel/src/main/resources/context.xml)
file to match your environment.

```xml
   <Resource name="jdbc/iHotelDS" auth="Container" type="javax.sql.DataSource"
        maxTotal="100" maxIdle="30" maxWaitMillis="10000"
        username="ihotel" password="password" driverClassName="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/ihoteldb" />
```
- Startup the embedded tomcat using the tomcat7 maven plugin `(tomcat7:run)`
- Navigate to (http://localhsot:8080/ihotel)[http://localhsot:8080/ihotel] and confirm that you can see the iHotel 
landing page.


### 3. Running payment
> Make sure to set the active profile as **dev**
- Since this is a SpringBoot Application, simply run the main class **edu.jennifer.payment.IpaymentApp**


### 4. Running check 
- Run the main class **edu.jennifer.check.launcher.RunCheck**


## Building
- Set the `ihotel.version` version in the the [root pom.xml](./pom.xml)
- execute the install goal. Each submodule has a build.xml ant file that is triggered whenever the package goal is executed
- The final build files will be created in the **build** dir in the project root. Example of the generated files
```
├── build
│   ├── check-v3.0.3.zip
│   ├── ihotel-v3.0.3.zip
│   ├── payment-v3.0.3.zip
│   └── stress-v3.0.3.zip
```