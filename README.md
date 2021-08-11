# SignUp Test Framework

Hi ,

Thanks for providing the oppurnity to attempt this test, it is interesting one.
I really enjoyed working on this test

So I have created this framework using TestNg + Java + Selenium + Maven

And followed Page Object Model Technic , in this

I created Page/Action class in path

```src/main/java/com/domain/page```

And object repositories on the below mentioned directory in property file

```src/main/java/com/domain/page_object```

And the commands to run the test is 

```mvn clean test```  or      
```mvn clean test -Dbrowser=chrome/firefox```


if you want to provide browser from command line

Currently my framework supports Chrome and Firefox other browsers can easily implemented if required

or 

```mvn clean test -Dbrowser=chrome -DthreadCount=2``` 

if you want to run test in parallel and set the number of threads from commandline only

I have integrated Allure report with this framework so once tests are run we need to execute another command to generate allure report
 
 ```mvn io.qameta.allure:allure-maven:report```

The path for the allure report is ```/target/site/allure-maven-plugin/index.html```

Apart from report I have integrated Test fail Retry listner which will rerun our failed test 2 times.

Feel free to connect with me at sarthak2990@gmail.com incase of any questions or doubt
