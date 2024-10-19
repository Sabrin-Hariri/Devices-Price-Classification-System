from flask import Flask, render_template, request
import pandas as pd
import logging
import joblib
import os
import sys
import requests

# Current directory
current_dir = os.path.dirname(__file__)

# Flask application
app = Flask(__name__, static_folder='static', template_folder='template')

# Set up logging
app.logger.addHandler(logging.StreamHandler(sys.stdout))
app.logger.setLevel(logging.ERROR)

# Load the model once when the application starts
model_name = 'model/SVM_model.pkl'
model_dir = os.path.join(current_dir, model_name)
loaded_model = joblib.load(open(model_dir, 'rb'))

# Function to predict the value
def ValuePredictor(data=pd.DataFrame):
    result = loaded_model.predict(data)
    return result[0]

# Home page
@app.route('/')
def home():
    return render_template('index.html')

# Prediction page
@app.route('/prediction', methods=['POST'])
def predict():
    if request.method == 'POST':
        try:
            # Get data from the JSON body
            data = request.json

            # Extract all fields from the JSON
            battery_power = data.get('battery_power', None)
            blue = data.get('blue', False)
            clock_speed = data.get('clock_speed', None)
            dual_sim = data.get('dual_sim', False)
            fc = data.get('fc', None)
            four_g = data.get('four_g', False)
            int_memory = data.get('int_memory', None)
            m_dep = data.get('m_dep', None)
            mobile_wt = data.get('mobile_wt', None)
            n_cores = data.get('n_cores', None)
            pc = data.get('pc', None)
            px_height = data.get('px_height', None)
            px_width = data.get('px_width', None)
            ram = data.get('ram', None)
            sc_h = data.get('sc_h', None)
            sc_w = data.get('sc_w', None)
            talk_time = data.get('talk_time', None)
            three_g = data.get('three_g', False)
            touch_screen = data.get('touch_screen', False)
            wifi = data.get('wifi', False)

            # Convert boolean values to integers
            blue = 1 if blue else 0
            dual_sim = 1 if dual_sim else 0
            four_g = 1 if four_g else 0
            three_g = 1 if three_g else 0
            touch_screen = 1 if touch_screen else 0
            wifi = 1 if wifi else 0

            # Update the data in the DataFrame
            schema_cols = {
                'battery_power': battery_power,
                'blue': blue,
                'clock_speed': clock_speed,
                'dual_sim': dual_sim,
                'fc': fc,
                'four_g': four_g,
                'int_memory': int_memory,
                'm_dep': m_dep,
                'mobile_wt': mobile_wt,
                'n_cores': n_cores,
                'pc': pc,
                'px_height': px_height,
                'px_width': px_width,
                'ram': ram,
                'sc_h': sc_h,
                'sc_w': sc_w,
                'talk_time': talk_time,
                'three_g': three_g,
                'touch_screen': touch_screen,
                'wifi': wifi
            }

            # Convert JSON to DataFrame
            df = pd.DataFrame(data={k: [v] for k, v in schema_cols.items()})

            # Get the prediction result
            result = ValuePredictor(data=df)

            # Determine the output based on the prediction result
            if int(result) == 1:
                prediction = 'Dear your device is classified as medium cost:1!'
            elif int(result) == 2:
                prediction = 'Dear your device is classified as high cost:2!'
            elif int(result) == 3:
                prediction = 'Dear your device is classified as very high cost:3!'
            else:
                prediction = 'Dear your device is classified as low cost:0!'

            # Store the data in the H2 database via Spring Boot
            spring_boot_url = 'http://localhost:8080/api/devices'  # Spring Boot API URL
            payload = {
                'battery_power': battery_power,
                'blue': blue,
                'clock_speed': clock_speed,
                'dual_sim': dual_sim,
                'fc': fc,
                'four_g': four_g,
                'int_memory': int_memory,
                'm_dep': m_dep,
                'mobile_wt': mobile_wt,
                'n_cores': n_cores,
                'pc': pc,
                'px_height': px_height,
                'px_width': px_width,
                'ram': ram,
                'sc_h': sc_h,
                'sc_w': sc_w,
                'talk_time': talk_time,
                'three_g': three_g,
                'touch_screen': touch_screen,
                'wifi': wifi,
                'price_range': int(result)  
            }
            
            # Log the data before sending it
            app.logger.info(f'Sending payload to Spring Boot: {payload}')

            # Send the data to Spring Boot
            response = requests.post(spring_boot_url, json=payload)

            if response.status_code == 201:  # If stored successfully
                app.logger.info('Data stored successfully in the database.')
            else:
                app.logger.error(f'Failed to store data in the database. Status Code: {response.status_code}, Response: {response.text}')

            # Return the prediction in an HTML template
            return render_template('prediction.html', prediction=prediction)

        except Exception as e:
            app.logger.error(f'Error during prediction or data storage: {e}')
            return render_template('error.html', error_message='An error occurred during processing.')

    else:
        return render_template('error.html')

if __name__ == '__main__':
    app.run(debug=True)
