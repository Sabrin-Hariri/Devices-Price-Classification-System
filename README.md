# Devices-Price-Classification-System

Project Phases:
Adding All Necessary Libraries: Begin by adding all the required libraries for the project, such as pandas, numpy, sklearn, and flask. These libraries provide the essential tools for data analysis and model building.

Loading the Data: Training and Test Data: Load both datasets—one for training and another for testing. The training data is used to train the model, while the test data is used to evaluate its performance.

Data Cleaning: We need to handle any missing values in the training data and ensure all data types are correct (such as integers, floats, etc.). This ensures the input data is of high quality for the model.

Splitting the Training Data into Training and Evaluation Sets: The training data is split into two sets: one for training the model and another for evaluating its performance. This prevents overfitting.

Choosing the Algorithms: Since the dataset is relatively small and contains multiple classes (0-1-2-3), I chose several algorithms that I believe will perform best:

a. SVM (Support Vector Machine) Algorithm
b. KNN (K-Nearest Neighbors) Algorithm
c. Random Forest Classifier Algorithm
Training and Testing the Data: We train the model using the training set and then test it with the evaluation set to measure its performance.

Selecting the Best Algorithm: After running the tests, the SVM algorithm proved to be the most suitable among the chosen options.

Loading the Model and Testing it on Test Data: After training, we save the model and then test it on the test dataset to verify its prediction accuracy.

Connecting with Flask: We integrate the model with a Flask application, which allows us to create an interface for providing predictions online. (I've already done this part, and I'll connect it with Spring Boot.)

Building the Spring Boot Project and Connecting it to the Database: Create a Spring Boot project that manages the project's database, and I've chosen 2H Database for its speed and efficiency.

Connecting Flask with Spring Boot: We link Flask to Spring Boot, enabling seamless communication between the frontend and backend.

Building Classes and APIs: We develop the necessary classes and APIs to facilitate data exchange between the various project components.

Testing the APIs:

POST: http://localhost:5000/prediction: Add a new device and predict its price.
GET: http://localhost:8080/api/devices: Get all devices.
GET: http://localhost:8080/api/devices/{id}: Get a device by its ID.
POST: http://localhost:8080/api/devices/predict/{id}: Predict the price of a device by its ID.
How to Run the Project:
Run Flask First:

bash
Copy code
cd python-flask
python app.py
Run Spring Boot:

bash
Copy code
cd devices-price-Mclassification
mvn spring-boot:run
Configure the Database: Adjust the database settings in the application.properties file.
