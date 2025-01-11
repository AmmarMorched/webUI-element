from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse, StreamingResponse
from PIL import Image
import io
import cv2
from ultralytics import YOLO

app = FastAPI()

# Load the YOLOv8 model
model = YOLO("detect.pt")

@app.post("/infer")
async def infer(file: UploadFile = File(...)):
    try:
        # Read the uploaded image
        image_bytes = await file.read()
        image = Image.open(io.BytesIO(image_bytes))

        # Perform inference
        results = model.predict(source=image, save=False)

        # Annotate the image with bounding boxes and labels
        annotated_image = results[0].plot()

        # Convert the annotated image to bytes
        _, buffer = cv2.imencode(".png", annotated_image)
        annotated_image_bytes = io.BytesIO(buffer)

        # Extract predictions
        predictions = []
        for result in results:
            for box in result.boxes.data.tolist():
                x_min, y_min, x_max, y_max, confidence, class_id = box
                predictions.append({
                    "bbox": [x_min, y_min, x_max, y_max],
                    "confidence": confidence,
                    "class": model.names[int(class_id)]
                })

        # Return both JSON and image in a multipart response
        return StreamingResponse(annotated_image_bytes, media_type="image/png")

    except Exception as e:
        return JSONResponse(content={"status": "error", "message": str(e)})
