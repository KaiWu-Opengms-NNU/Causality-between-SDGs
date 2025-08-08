# How to run the code for Opposing causality between Sustainable Development Goals across spatial scales hinders policy coherence



We recommend downloading the code and data package to your local machine for running. This is because the code requires both Java and Python environments, and it has a relatively long runtime. If you have any questions, you can contact the author at this email: kwu@nnu.edu.cn.



# 一 Environment Configuration

Before you run the code，you should ensure that your machine has following environments.

### Java 8 + Maven 3

You should set up Java 8 with Maven 3 and ensure all dependencies in ***pom.xml*** are installed.

### Python 3

You should set up Python 3 and pip install ***numpy*** and ***statsmodels***.

### MongoDB

You need install the MongoDB and create a database named ***SdgDataFromUN***.



# 二 Code Running

Then，you can run the file named ***GrangerMain.java*** ( src/test/java/GrangerMain.java ). This code file is designed to identify all SDG relationships at different scales. The execution may take one or two days to complete. All the results are located in this path (src\test\result)