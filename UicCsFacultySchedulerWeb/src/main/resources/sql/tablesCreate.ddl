

drop table csfaculty.SCHEDULED_SECTIONS;
drop table csfaculty.COURSES;

create table csfaculty.COURSES (
	NAME VARCHAR(100),
    NUMBER int(3),
    UNDERGRADHOURS int,
    GRADHOURS int,
    PRIMARY KEY (NUMBER)
);

create table csfaculty.SCHEDULED_SECTIONS (
	CourseNumber int(8),
	SectionNumber int(8),
    StartTime VARCHAR(20),
    StopTime VARCHAR(20),
    PRIMARY KEY (CourseNumber,SectionNumber),
    FOREIGN KEY (CourseNumber) REFERENCES COURSES(NUMBER)
);