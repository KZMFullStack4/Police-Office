
In the name of Allah
Author : Saeid Kazemi
 
 #PROJECT DESCRIPTION 
 
 First of all in order to understand about project Analysis , I suggest you to visit ER Diagram of this challenge located at 
 root path of the project in "utils" folder, additionally there is a postMan file there , you can import and check .
 
 If you look at Rest layer , you will see  provided CRUD operations for three model (Dossier, PoliceMan , Plaintiff)
 because they were necessary to achieve our requirements .
 
 #Test
 
 Unfortunately time limitation did not let me to write any test cases , but the JUnit dependency was added to the POM file and is ready to use.
 
 #APIs :
 
 #/dossier/add
 
 When you add a new dossier , if you choose policeMan to review this dossier for the first time ,
 dossier status for this specific model will change to
 CLOSED state (It means there is a policeMan that is working on ) , 
 and chosen policeMan status will change to BUSY state(It also means this specific policeMan is not free and is working on a dossier).
 There are two flags in Dossier and PoliceMan Models that will determine if a dossier is closed or not, and if a policeMan is busy or not.
 
 Noticeable : I'm explaining ambiguous points of project , majority of the APIs are clear and obvious.
 
 We can choose different strategies in frontend side of the application to use these APIs .
 
 
 #/dossier/get-all-closed-opened-dossiers/{opened}
 
 By this API you are gonna be able to retrieve OPENED or CLOSED dossiers by the flag you send at the end of URL 
 if it is true it'll give you list of OPENED dossiers and vice versa .
 
 
 #/dossier/close-dossier/{id}
 
 This can be used to closing specific dossier . it gets an id (dossier id) at the end of URL and it will alter the Dossier flag to CLOSED.
 
 
 
 #/dossier/allocate-dossier/{dossierId}/{policeManId}
 
 As its clear , this API can help you to allocate a specific dossier to specific policeMan.
 For example :here we can provide a modal at the frontend and by using that APIs ( Fetching FREE policeMan and Fetching OPENED dossiers ) 
 in order to do allocation actions ! 
 
 
 #/dossier/get-total-report
 
 This is an API that provides total report for all requirements .
 it gets a dto class to filter the response.
 
 #/police-man/change-police-status/{id}/{status}
 
 This can change policeMan status
 
 
 #/police-man/get-busy-or-free-polices/{isBusy}
 
 This is used to retrieve  busy or free polices by the boolean flag "isBusy".
 
 
 
 #TECHNICAL DESCRIPTION
 
 Technologies : 
 Java : 11,
 Spring Boot : 2.3.1
 JUnit :5
 JPA
 Hibernate 
 Postgre Sql
 To Run the application use : Go to directory of project and open a terminal and run " mvn spring-boot:run " maven command 
 
 
 
 
 