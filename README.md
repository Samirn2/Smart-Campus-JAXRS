# Smart Campus JAX-RS API
> **Role:** Lead Backend Architect  
> **Focus:** Resilience, Security, and Observability

---

## 🏛️ Project Overview
In this project, I took on the role of **Backend Architect** to build a centralised system for managing campus sensors. The primary objective was not just to ensure the API functioned correctly, but to guarantee it was reliable, secure, and easy to monitor in a production-like environment.

### 🛠️ The Tech Stack
* **IDE:** Apache NetBeans (Primary Workspace)
* **Build Tool:** Maven (Dependency Management & Build Automation)
* **Framework:** JAX-RS (Jakarta RESTful Web Services)
* **Server:** Apache Tomcat
* **Testing:** Postman
  

The API follows standard RESTful designs to keep the code organised and easy to manage, making sure it works smoothly with other common Java-based tools.


---

## 📖 Setup & Installation Guide

### 1. Clone the Repository
You can clone the project directly into **Apache NetBeans**.

#### **Option 1: Clone inside NetBeans (Recommended)**
1. Open **Apache NetBeans**.
2. Go to **Team** → **Git** → **Clone...**
3. In the **Repository URL** field, enter:
   ```text
   [https://github.com/Samirn2/Smart-Campus-JAXRS.git](https://github.com/Samirn2/Smart-Campus-JAXRS.git)
Click Next, select the main branch, and choose a local directory.

Click Finish. When prompted, click Yes to open the project.

Option 2: Open the Project Manually

Go to File → Open Project.

Navigate to the folder: Smart-Campus-JAXRS

Select the folder (NetBeans should recognise the Maven "m" icon) and click Open Project.

### **2. Server Environment Setup**
This API requires a servlet container to handle RESTful services natively.

* **Server Choice:** Use **Apache Tomcat** or **TomEE**.
1. Go to the **Services** tab (found via `Window` → `Services`).
2. Right-click on **Servers** and select **Add Server**.
3. Select **Apache Tomcat** or **TomEE**.
4. Follow the prompts to point NetBeans to your local installation folder.

---

### **3. Project Configuration**
1.  Right-click the project name in the **Projects** tab and select **Properties**.
2.  Select the **Run** category from the left-hand menu.
3.  In the **Server** dropdown, select Apache Tomcat or TomEE.
4.  Set the **Context Path** to: `/SmartCampusAPI`
5.  Click **OK**.

---

### **4. Building and Deploying**
1.  **Clean and Build:** Right-click the project and select **Clean and Build**. This ensures all dependencies are gathered and the project is compiled.
2.  **Run:** Right-click the project and select **Run**. This will start the server and deploy the API.
3.  **Access:** Once deployed, the API can be accessed at:
    `http://localhost:8080/SmartCampusAPI/api/v1/sensors`

Initialise Dependencies: Right-click the project and select Clean and Build. Maven will download the necessary JAX-RS and JSON libraries.

Deploy: Right-click the project and select Run.

---

## 📡 API Reference

**Base URL:** `http://localhost:8080/SmartCampusAPI/api/v1`

---

### **Rooms Resource**
Endpoints for managing the physical layout and capacity of the campus.

| Method | Endpoint | Description |
|:--- |:--- |:--- |
| **GET** | `/rooms` | Retrieve a list of all campus rooms. |
| **POST** | `/rooms` | Register a new room . |
| **GET** | `/rooms/{roomId}` | Get details and capacity for a specific room. |
| **DELETE** | `/rooms/{roomId}` | Remove a room from the system. |

---

### **Sensors Resource**
Endpoints for managing hardware deployment and monitoring status.

| Method | Endpoint | Description |
|:--- |:--- |:--- |
| **GET** | `/sensors` | List all sensors across the campus network. |
| **GET** | `/sensors?type=TEMP` | Filter sensors by category (e.g., Temperature, CO2). |
| **POST** | `/sensors` | Deploy and link a new sensor to a specific room. |
| **PUT** | `/sensors/{sensorId}` | Update sensor metadata or toggle status (`ACTIVE`, `MAINTENANCE`). |

---

### **Sensor Readings (Sub-Resource)**
Nested endpoints that handle the ingestion and retrieval of hardware metrics.

| Method | Endpoint | Description |
|:--- |:--- |:--- |
| **GET** | `/sensors/{sensorId}/readings` | Retrieve the full history of measurements for a specific sensor. |
| **POST** | `/sensors/{sensorId}/readings` | **Architecture Check:** Submits a new metric. Returns **403 Forbidden** if the sensor is in "MAINTENANCE" mode. |

---

### **Testing & Resilience Verification**
Specific routes designed to demonstrate the backend architecture.

| Method | Endpoint | Description |
|:--- |:--- |:--- |
| **GET** | `/sensors?type=crash` | **Safety Net Test:** Triggers an intentional runtime exception to verify the `GlobalExceptionMapper` intercepts the error and returns a secure **500 Internal Error**. |

---

# Coursework Report Answers

---

### Q1.1: 
**In JAX-RS, resource classes are Request-Scoped by default.** This means the server creates a brand-new instance of the class for every single API request and destroys it once the response is sent. Because the class is disposed of after every request, I could not store sensor data in regular instance variables, as the data would reset to empty every time a request ended. To fix this, I used **static data structures** so the data remains in the server's memory even as the resource classes are created and destroyed. This approach keeps the API stateless and clean while ensuring that sensor readings are preserved in a centralised, thread-safe memory store.

---

### Q1.2: 
**Hypermedia makes an API self-descriptive by providing links that guide the client on what actions they can take next.** This transforms the API from a static data source into a dynamic system where the response drives the application state. It offers significant URL flexibility because clients follow relationship links rather than hardcoded paths, allowing the backend to evolve without breaking the client's code. It also makes navigation much easier, as the API provides a real-time roadmap of related resources—such as links to sensors within a room—which reduces the need for external documentation.

---

### Q2.1: 
**Choosing between returning only IDs or full room objects is a trade-off between network efficiency and the speed of the user interface.** Returning only IDs significantly minimises network bandwidth because the payload is extremely small, but it increases the burden of client-side processing as the client must perform additional API requests to retrieve basic details for each ID. Returning full objects consumes more bandwidth during the initial transfer but is much more efficient for the client, as all necessary information is available immediately for rendering without the need for further server calls.

---

### Q2.2: 
**The `DELETE` operation is idempotent in this implementation because multiple identical requests result in the same final state on the server.** When a client sends the first `DELETE` request for a specific ID, the server locates and removes that resource. If the client mistakenly sends the exact same request again, the server finds that the resource no longer exists and performs no further changes. Since the end result—the absence of the resource from the database—is the same whether the request is sent once or many times, the operation is idempotent.

---

### Q3.1: 
**The `@Consumes` annotation acts as a strict guard for API endpoints by specifying exactly which data formats the server is prepared to process.** If a client attempts to send data in an unsupported format, such as `text/plain` or `application/xml`, the JAX-RS framework identifies the conflict between the client's `Content-Type` header and the required media type. Instead of allowing the request to reach the method logic, the framework automatically intercepts the call and returns an `HTTP 415 Unsupported Media Type` status code to prevent the application from attempting to parse incompatible data.

---

### Q3.2: 
**Using a query parameter for filtering is generally better because it separates the identity of a resource from the criteria used to filter it.** In RESTful design, the URL path should represent the resource itself, while the query string is reserved for modifiers like searching or filtering. If the filter is put directly in the path, the URL becomes rigid and difficult to manage as more options are added. By using query parameters, you can effortlessly combine multiple filters, such as `/sensors?type=CO2&status=active`, without changing the fundamental structure of the endpoint.

---

### Q4.1: 
**The Sub-Resource Locator pattern is an effective way to keep code organised as an API expands by delegating logic to smaller, specialised classes.** Instead of building one massive class to manage every nested path, the `RoomResource` handles room-level data and then passes the request to a separate `SensorResource` for sensor-specific logic. This prevents the codebase from becoming cluttered and difficult to debug. Ultimately, this makes the API much easier to maintain because changes to sensor logic are contained within their own class rather than being buried in room-related code.

---

### Q5.2: 
**`HTTP 422` is often more semantically accurate than a `404` because it distinguishes between a missing endpoint and a logic error within the provided data.** A `404` status traditionally means the URL path itself could not be found by the server. If a client sends a valid JSON request to the correct URL, but includes a reference like a room ID that does not exist in the database, the endpoint was found but the content cannot be processed. Returning a `422` confirms the connection was successful but the data sent was logically invalid, which makes debugging much easier for the developer.

---

### Q5.4: 
**Exposing internal Java stack traces to external users is a major security risk because it provides a roadmap of the application's inner workings.** A stack trace reveals sensitive details like class names, method names, and exact line numbers where a failure occurred. This is invaluable to an attacker as it often leaks the specific versions of frameworks being used, such as Apache Tomcat. If those versions have known vulnerabilities, an attacker can launch a targeted exploit or identify exactly where validation checks might be missing, making it much easier to plan a successful attack.

---

### Q5.5: 
**Using JAX-RS filters for logging is more efficient because it centralises the logic and follows the principle of not repeating code.** Instead of manually adding log statements to every single method, a filter automatically applies the logic to all incoming requests in one place. This keeps resource classes clean and focused on their primary tasks rather than being cluttered with repetitive boilerplate code. It also ensures total consistency across the API, as developers do not have to worry about forgetting to log a new endpoint.
