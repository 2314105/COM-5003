-- Step 1: Create the database
CREATE DATABASE UniversityDB;

-- Step 2: Use the database
USE UniversityDB;

-- Step 3: Copy all code below
CREATE TABLE Courses (
                         CourseID INT AUTO_INCREMENT PRIMARY KEY,
                         CourseName VARCHAR(100) NOT NULL,
                         CourseCode VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE Modules (
                         ModuleID INT AUTO_INCREMENT PRIMARY KEY,
                         ModuleCode VARCHAR(10) UNIQUE NOT NULL,
                         ModuleName VARCHAR(100) NOT NULL,
                         Level INT NOT NULL CHECK (Level IN (4, 5, 6)), -- Level 4, 5, or 6
                         Credits INT NOT NULL
);

CREATE TABLE CourseModules (
                               CourseID INT NOT NULL,
                               ModuleID INT NOT NULL,
                               PRIMARY KEY (CourseID, ModuleID),
                               FOREIGN KEY (CourseID) REFERENCES Courses(CourseID) ON DELETE CASCADE,
                               FOREIGN KEY (ModuleID) REFERENCES Modules(ModuleID) ON DELETE CASCADE
);

-- Step 4: Populate the table with test data

-- Insert sample courses
INSERT INTO Courses (CourseName, CourseCode)
VALUES
    ('International Business', 'IB2024'),
    ('Computer Science', 'CS2024'),
    ('Education Studies', 'ES2024'),
    ('Journalism', 'J2024'),
    ('Sports Coaching', 'SC2024');

-- Insert sample modules
INSERT INTO Modules (ModuleCode, ModuleName, Level, Credits)
VALUES
    ('BMM5312', 'Principles of Marketing', 5, 20),
    ('BMM5402', 'Organizational Behavior', 5, 20),
    ('BMM5412', 'Business Finance', 5, 20),
    ('BMM5552', 'Operations Management', 5, 20),
    ('BMM5562', 'Strategic Management', 5, 20),
    ('BMM5582', 'Business Ethics', 5, 20),
    ('BMM6402', 'Global Business Strategy', 6, 20),
    ('BMM6422', 'Entrepreneurship and Innovation', 6, 20),
    ('BMM6452', 'Leadership and Change Management', 6, 20),
    ('BMM6472', 'Digital Marketing Strategies', 6, 20),
    ('BMM6502', 'Sustainability in Business', 6, 20),
    ('BMM6582', 'International Trade and Policy', 6, 20),
    ('COM5003', 'Advanced Programming Concepts', 5, 30),
    ('COM5013', 'Database Systems and Design', 5, 30),
    ('COM5023', 'Computer Networks', 5, 30),
    ('COM5033', 'Software Engineering Principles', 5, 30),
    ('COM6003', 'Artificial Intelligence and Machine Learning', 6, 30),
    ('COM6033', 'Big Data and Analytics', 6, 30),
    ('COM6013', 'Cybersecurity and Ethical Hacking', 6, 30),
    ('COM6023', 'Cloud Computing and Distributed Systems', 6, 30),
    ('CYP5005', 'Introduction to Curriculum Design', 5, 15),
    ('CYP5015', 'Child Development and Learning Theories', 5, 15),
    ('CYP5023', 'Educational Research Methods', 5, 30),
    ('CYP5053', 'Inclusive Education Practices', 5, 30),
    ('CYP5063', 'Technology in Education', 5, 30),
    ('CYP6005', 'Advanced Curriculum Design', 6, 15),
    ('CYP6015', 'Leadership in Education', 6, 15),
    ('CYP6033', 'Education Policy and Practice', 6, 30),
    ('CYP6073', 'Contemporary Issues in Education', 6, 30),
    ('CYP6083', 'Assessment and Evaluation Strategies', 6, 30),
    ('JOU6006', 'Advanced Sports Journalism', 6, 60),
    ('JOU6063', 'Digital Media in Sports Journalism', 6, 30),
    ('JOU6093', 'Sports Reporting and Investigative Journalism', 6, 30),
    ('SHN6033', 'Sports Coaching Theory and Practice', 6, 30),
    ('SHN6053', 'Advanced Sports Science in Coaching', 6, 30),
    ('SHN6073', 'Performance Analysis in Sports Coaching', 6, 30),
    ('SHN6143', 'Coaching Techniques and Methodologies', 6, 30);

-- Associate International Business course with modules
INSERT INTO CourseModules (CourseID, ModuleID)
VALUES
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM5312')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM5402')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM5412')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM5552')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM5562')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM5582')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM6402')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM6422')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM6452')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM6472')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM6502')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'IB2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'BMM6582'));

-- Associate Computer Science course with modules
INSERT INTO CourseModules (CourseID, ModuleID)
VALUES
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM5003')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM5013')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM5023')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM5033')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM6003')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM6033')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM6013')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'CS2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'COM6023'));

-- Associate Education Studies course with modules
INSERT INTO CourseModules (CourseID, ModuleID)
VALUES
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP5005')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP5015')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP5023')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP5053')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP5063')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP6005')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP6015')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP6033')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP6073')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'ES2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'CYP6083'));

-- Associate Sports Journalism course with modules
INSERT INTO CourseModules (CourseID, ModuleID)
VALUES
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'J2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'JOU6006')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'J2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'JOU6063')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'J2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'JOU6093'));

-- Associate Sports Coaching course with modules
INSERT INTO CourseModules (CourseID, ModuleID)
VALUES
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'SC2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'SHN6033')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'SC2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'SHN6053')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'SC2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'SHN6073')),
    ((SELECT CourseID FROM Courses WHERE CourseCode = 'SC2024'), (SELECT ModuleID FROM Modules WHERE ModuleCode = 'SHN6143'));