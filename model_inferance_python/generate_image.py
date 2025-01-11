from ultralytics import YOLO
import os
import shutil

# Load the trained model
model = YOLO("detect.pt")  # Replace with your model's path

# Define the output folder
output_folder = os.path.join(os.getcwd(), "results")
print(output_folder)


# Perform inference and save results
results = model.predict(source="Source/12.png", save=True)

# Since results is a list, access the first item in the list
result = results[0]

# Get the actual save directory from the result object
saved_dir = result.save_dir

# Move saved results to the desired directory
for file in os.listdir(saved_dir):
    shutil.move(os.path.join(saved_dir, file), os.path.join(output_folder, file))

shutil.rmtree(saved_dir)

print(f"Labeled images saved in: {os.path.abspath(output_folder)}") 