# WebUI Element Classification Model with FastAPI and Spring Boot

This project involves an **AI-powered web UI element classification model** developed using **FastAPI** for serving the model and **Spring Boot** for handling the backend logic. The goal is to create a seamless integration where a trained model classifies UI elements, and the classification results are served through a RESTful API.

## Project Overview

This project consists of two main parts:

1. **FastAPI**: The FastAPI application serves the trained model and provides an API endpoint to receive input data and return results.
2. **Spring Boot**: The Spring Boot application handles frontend requests, communicates with the FastAPI backend to get classification results, and integrates additional business logic.

The main objective is to classify web UI elements.

## Features

- **Model Inference**: FastAPI serves the model to classify web UI elements.
- **Integration with Spring Boot**: The Spring Boot application communicates with FastAPI to retrieve model inference results and serves them to the client.

### Software Requirements

- Python 3.x
- Java 11+ (for Spring Boot)
- Maven (for Spring Boot build)

### Libraries/Frameworks

- **FastAPI** for Python-based API development
- **Uvicorn** as the ASGI server for FastAPI
- **Spring Boot** for Java-based backend development
- **ultralytics** for running the model

### Hardware Requirements

- Suitable environment to run the model (e.g., a machine with a GPU if needed for model inference).

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/AmmarMorched/WebUI-element-classification-model-fastapi-springboot.git
cd WebUI-element-classification-model-fastapi-springboot

### 2.  FastAPI Application Setup
create a virtual enviroment
```bash
python -m venv venv

Install Python Dependencies
on windows:

```bash
venv\Scripts\activate

on linux:

```bash
source venv/bin/activate

the install requirement lib

```bash
pip install -r requirements.txt

run the application

```bash
uvicorn app:app --host 0.0.0.0 --port 8000


### 3. Springboot application setup
you just need to build and run the project


### Usage:
using a tool like postman copy and past the url from the controller
in the parameter choose body
set key to file and in the value choose the image you want to process.



