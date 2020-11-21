# Classroom Management System
## Login
Only Admin, Prof, SAC,TAs, Committees can login
## Admin
1. Enter timetable details in database(Fixed for the sem)
2. If a particular classroom (which is not available)is required for some event, admin    has to reschedule the class to some other room according to capacity required and inform professor about the change
3. Grant or reject the requests by professors, SAC, Committees, TAs(by considering the purpose)
4. After granting, notify the prof/SAC/Committees with all details of the room. Also, notify the cleaning staff to clean the room before the class/event starts(If asked for while requesting for classroom). Also, Notify Security to open the room for cleaning and class/event as well.
5. If a request is rejected, notify Prof/SAC, committees that their request was rejected.
## Professor
1. View Classrooms available according to filters like building(Ramanujan, Aryabhatta), minimum capacity required, time slot  and purpose of asking for room request. 
2. They will have an option to notify the cleaning staff if the classroom is granted and if they want that room to be cleaned.
3. Purpose can be:
    1. Event ( Then show all rooms as rescheduling is an option)
    2. Exams( Show classrooms according to the fact that students capacity should be double the no. of students registered in course. )
    3. Extra class(Normal classroom availability)
    4. Meeting with SAC/Committees/TAs
## SAC, Committees, TAs
1. Request for rooms according to capacity required, purpose, time slot
2. Ask for cleaning the room before slot time.(Optional)
3. Purpose can be:
    1. Meeting within Committee
    2. Interaction with Students
    3. Interaction with prof. (In this case, the prof has to request for room.)

## Database Schema
![Database_Schema](src/main/webapp/images/cmsdb.png)

## Mysql ContainerSetup
### pull the mysql docker image from docker hub
docker pull mysql:5.7.29

### create cms-mysql container
docker run --name cms-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cms_db -e MYSQL_USER=cms_user -e MYSQL_PASSWORD=cms_password -d mysql:5.7.29

## Springboot ContainerSetup
### build cms-springboot docker image
docker build -t cms-springboot .

### create cms-springboot container from docker image
docker run -t -p 8082:8082 --name cms-springboot --link cms-mysql:mysql -d cms-springboot