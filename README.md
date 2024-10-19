# Devices Price Classification System

## Project Overview
The **Devices Price Classification System** is a machine learning project aimed at predicting the price category of electronic devices based on their specifications. The project utilizes algorithms such as SVM, KNN, and Random Forest to make predictions and integrates with both Flask and Spring Boot for a full-stack solution. 

### Project Phases:

1. **Adding All Necessary Libraries**  
   Add required libraries such as `pandas`, `numpy`, `sklearn`, and `flask`. These libraries provide essential tools for data processing and model training.

2. **Loading the Data**  
   Load both the training and test datasets. The training dataset is used for building the model, while the test dataset evaluates its performance.

3. **Data Cleaning**  
   Handle missing values in the training data and ensure that all data types are correct (e.g., integers, floats). This ensures the input data is of high quality before passing it to the model.

4. **Splitting the Data**  
   Split the training data into two sets: one for training and the other for evaluation, to avoid overfitting.

5. **Choosing the Algorithms**  
   The dataset contains multiple classes (0-1-2-3) and is relatively small. Thus, the following algorithms were chosen:
   - **SVM (Support Vector Machine)**
   - **KNN (K-Nearest Neighbors)**
   - **Random Forest Classifier**

6. **Training and Testing the Data**  
   Train the model on the training data, and test it with the evaluation data to assess performance.

7. **Selecting the Best Algorithm**  
   After testing, **SVM** proved to be the most suitable algorithm for this classification task.

8. **Loading the Model and Testing it on Test Data**  
   Save the trained model and test it on the test dataset to verify its prediction accuracy.

9. **Connecting with Flask**  
   Integrate the trained model into a Flask application, allowing us to create an interface for providing predictions via a web service.

10. **Building the Spring Boot Project and Connecting to the Database**  
    Create a Spring Boot project that manages the database and connects with Flask for model predictions. **2H Database** is used for its efficiency and speed.

11. **Connecting Flask with Spring Boot**  
    Connect Flask to Spring Boot to enable communication between the backend (Flask and machine learning model) and frontend (Spring Boot and APIs).

12. **Building Classes and APIs**  
    Develop the necessary classes and APIs to facilitate data exchange between various project components.

13. **Testing the APIs**  
    The following RESTful endpoints are provided for interaction:
    - **POST**: `http://localhost:5000/prediction` – Add a new device and predict its price.
    - **GET**: `http://localhost:8080/api/devices` – Retrieve all devices.
    - **GET**: `http://localhost:8080/api/devices/{id}` – Retrieve a device by its ID.
    - **POST**: `http://localhost:8080/api/devices/predict/{id}` – Predict the price of a device by its ID.

## How to Run the Project

### 1. Run Flask First:
```bash
cd python-flask
python app.py
```
### 2. Run Spring Boot:
```bash
cd devices-price-Mclassification
mvn spring-boot:run
```
