-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 06, 2021 at 01:03 AM
-- Server version: 8.0.22-0ubuntu0.20.04.3
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `IssueTracker`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`yadunandanbhat`@`localhost` PROCEDURE `ShowAll` (IN `actionPerformed` VARCHAR(255))  BEGIN
    SELECT
        *
    FROM
        history
    WHERE ACTION = actionPerformed ;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `attachments`
--

CREATE TABLE `attachments` (
  `uploader` int NOT NULL,
  `issueID` int NOT NULL,
  `fileLink` text,
  `dateUploaded` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `attachments`
--

INSERT INTO `attachments` (`uploader`, `issueID`, `fileLink`, `dateUploaded`) VALUES
(18057, 2, 'drive.google.com/someshit.php', '2020-11-26 21:00:27'),
(18045, 4, 'drive.google.com/somebugpicture.php', '2020-11-26 21:00:27'),
(18027, 6, 'drive.google.com/5thsemdbmsprojecttitles.php', '2020-11-26 21:00:27');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `commenter` int NOT NULL,
  `issueID` int NOT NULL,
  `message` text,
  `dateCommented` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`commenter`, `issueID`, `message`, `dateCommented`) VALUES
(18050, 3, 'Yea there was no need for the search function', '2020-11-26 21:03:26');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `auditID` int NOT NULL,
  `ACTION` varchar(255) NOT NULL,
  `issueID` int NOT NULL,
  `projectID` int NOT NULL,
  `issueSubmitter` int NOT NULL,
  `assignedDeveloper` int NOT NULL,
  `issueTitle` varchar(255) NOT NULL,
  `issueStatus` enum('Open','Closed') DEFAULT NULL,
  `issuePriority` enum('Low','Medium','High') DEFAULT NULL,
  `issueType` enum('Bug','Feature','Error','Other') DEFAULT NULL,
  `dateModified` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`auditID`, `ACTION`, `issueID`, `projectID`, `issueSubmitter`, `assignedDeveloper`, `issueTitle`, `issueStatus`, `issuePriority`, `issueType`, `dateModified`) VALUES
(1, 'Insert', 1, 1, 18057, 18058, 'Write a new stored procedure', 'Open', 'Low', 'Error', '2020-11-26 20:40:44'),
(2, 'Insert', 2, 1, 18057, 18034, 'Write a new trigger', 'Open', 'High', 'Error', '2020-11-26 20:48:22'),
(3, 'Insert', 3, 2, 18050, 18036, 'Error in the database schema', 'Open', 'Low', 'Feature', '2020-11-26 20:48:22'),
(4, 'Insert', 4, 3, 18005, 18045, 'Bug in kirani angdi', 'Open', 'High', 'Bug', '2020-11-26 20:48:22'),
(5, 'Insert', 5, 4, 18037, 18031, 'This was a mistake', 'Open', 'High', 'Other', '2020-11-26 20:48:23'),
(6, 'Insert', 6, 5, 18027, 18027, 'I dont now anything', 'Open', 'Medium', 'Other', '2020-11-26 20:48:23'),
(7, 'Update', 1, 1, 18057, 18058, 'Write a new stored procedure', 'Closed', 'Low', 'Error', '2020-11-26 21:05:06'),
(8, 'Update', 3, 2, 18050, 18036, 'Error in the database schema', 'Closed', 'Low', 'Feature', '2020-11-26 21:05:55'),
(9, 'Update', 6, 5, 18027, 18027, 'I dont now anything', 'Open', 'Low', 'Other', '2020-11-26 21:06:22'),
(10, 'Insert', 7, 1, 18057, 18058, 'sample', 'Open', 'Low', 'Bug', '2020-12-21 21:08:37'),
(11, 'Insert', 8, 1, 18057, 18058, 'sample', 'Closed', 'Low', 'Bug', '2020-12-21 21:09:06'),
(12, 'Insert', 9, 1, 18057, 18058, 'sample', 'Closed', 'Medium', 'Bug', '2020-12-21 21:09:10'),
(13, 'Insert', 10, 1, 18057, 18058, 'sample', 'Closed', 'High', 'Bug', '2020-12-21 21:09:14'),
(14, 'Insert', 11, 1, 18057, 18058, 'sample', 'Closed', 'High', 'Feature', '2020-12-21 21:09:16'),
(15, 'Insert', 12, 1, 18057, 18058, 'sample', 'Closed', 'High', 'Error', '2020-12-21 21:09:18'),
(16, 'Insert', 13, 1, 18057, 18058, 'sample', 'Closed', 'High', 'Other', '2020-12-21 21:09:20'),
(17, 'Update', 6, 5, 18057, 18027, 'I dont now anything', 'Open', 'High', 'Other', '2020-12-22 12:25:21'),
(18, 'Insert', 14, 1, 18057, 18058, 'Sample', 'Open', 'Medium', 'Error', '2020-12-22 12:26:58'),
(19, 'Update', 14, 1, 18057, 18058, 'Sample', 'Open', 'Medium', 'Other', '2020-12-22 12:27:33'),
(20, 'Insert', 15, 1, 18057, 18058, 'Saample', 'Open', 'Low', 'Bug', '2020-12-23 10:48:51'),
(21, 'Update', 6, 5, 18057, 18027, 'I dont now anything', 'Open', 'Low', 'Other', '2020-12-23 10:51:49'),
(22, 'Insert', 16, 1, 18057, 18058, 'sfkj', 'Open', 'High', 'Feature', '2020-12-23 18:51:04'),
(23, 'Update', 16, 1, 18057, 18058, 'sfkj', 'Closed', 'Low', 'Bug', '2020-12-23 18:51:11'),
(24, 'Update', 5, 4, 18057, 18031, 'This was a mistake', 'Closed', 'Low', 'Error', '2020-12-24 13:15:02'),
(25, 'Update', 4, 3, 18057, 18045, 'Bug in kirani angdi', 'Closed', 'Low', 'Other', '2021-01-02 09:02:59');

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE `issue` (
  `issueID` int NOT NULL,
  `projectID` int NOT NULL,
  `issueSubmitter` int NOT NULL,
  `assignedDeveloper` int NOT NULL,
  `issueTitle` varchar(255) NOT NULL,
  `issueDescription` text,
  `dateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
  `issueStatus` enum('Open','Closed') DEFAULT NULL,
  `issuePriority` enum('Low','Medium','High') DEFAULT NULL,
  `issueType` enum('Bug','Feature','Error','Other') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`issueID`, `projectID`, `issueSubmitter`, `assignedDeveloper`, `issueTitle`, `issueDescription`, `dateCreated`, `issueStatus`, `issuePriority`, `issueType`) VALUES
(1, 1, 18057, 18058, 'Write a new stored procedure', 'I need you to write a new stored procedure to show all the projects to which a user group belongs to', '2020-11-26 20:40:44', 'Closed', 'Low', 'Error'),
(2, 1, 18057, 18034, 'Write a new trigger', 'I need you to write a new trigger to update the history table whenever an issue gets deleted', '2020-11-26 20:48:22', 'Open', 'High', 'Error'),
(3, 2, 18050, 18036, 'Error in the database schema', 'There is no need to add the search function', '2020-11-26 20:48:22', 'Closed', 'Low', 'Feature'),
(4, 3, 18057, 18045, 'Bug in kirani angdi', 'There is a bug in the angdi, please kill it', '2020-11-26 20:48:22', 'Closed', 'Low', 'Other');

--
-- Triggers `issue`
--
DELIMITER $$
CREATE TRIGGER `AuditAfterInsert` AFTER INSERT ON `issue` FOR EACH ROW INSERT INTO history
SET ACTION
    = 'Insert',
    issueID = NEW.issueID,
    projectID = NEW.projectID,
    issueTitle = NEW.issueTitle,
    issueSubmitter = NEW.issueSubmitter,
    assignedDeveloper = NEW.assignedDeveloper,
    issueStatus = NEW.issueStatus,
    issuePriority = NEW.issuePriority,
    issueType = NEW.issueType,
    datemodified = NOW()
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AuditAfterUpdate` AFTER UPDATE ON `issue` FOR EACH ROW INSERT INTO history
SET ACTION
    = 'Update',
    issueID = NEW.issueID,
    projectID = NEW.projectID,
    issueTitle = NEW.issueTitle,
    issueSubmitter = NEW.issueSubmitter,
    assignedDeveloper = NEW.assignedDeveloper,
    issueStatus = NEW.issueStatus,
    issuePriority = NEW.issuePriority,
    issueType = NEW.issueType,
    datemodified = NOW()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `projectID` int NOT NULL,
  `projectMaintainer` int NOT NULL,
  `projectTitle` varchar(255) NOT NULL,
  `projectSummary` varchar(255) DEFAULT NULL,
  `projectCreated` datetime DEFAULT CURRENT_TIMESTAMP,
  `projectGroup` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`projectID`, `projectMaintainer`, `projectTitle`, `projectSummary`, `projectCreated`, `projectGroup`) VALUES
(1, 18057, 'Issue Tracker', 'An issue tracker', '2020-10-01 10:10:10', 1),
(2, 18050, 'Second Hand Book Management', 'A book lending application', '2020-10-02 11:10:10', 2),
(3, 18045, 'Kirani Angdi Management', 'A kirani angdi management application', '2020-10-03 12:10:10', 3),
(4, 18037, 'Spotify Ripoff', 'A spotify ripoff', '2020-10-04 13:10:10', 4);

-- --------------------------------------------------------

--
-- Table structure for table `userDetails`
--

CREATE TABLE `userDetails` (
  `userID` int NOT NULL,
  `userFullName` varchar(255) NOT NULL,
  `userMailID` varchar(255) NOT NULL,
  `userGroupID` int DEFAULT NULL,
  `userRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `userDetails`
--

INSERT INTO `userDetails` (`userID`, `userFullName`, `userMailID`, `userGroupID`, `userRole`) VALUES
(18005, 'Anagha H R', 'anaghahr@gmail.com', 3, 'Project Maintainer'),
(18011, 'Hafsa Mariyum', 'hafsa@gmail.com', 2, 'Senior Developer'),
(18027, 'Nirmal Kumar', 'nirmalkumar@gmail.com', 5, 'Senior Developer'),
(18031, 'Phanishree Kommalapti', 'phanishree@gmail.com', 4, 'Assitant Maintainer'),
(18033, 'Pranav Rattehalli M', 'pranavrattehallim@gmail.com', 3, 'Junior Developer'),
(18034, 'Pratibha S', 'pratibhas@gmail.com', 1, 'Senior Developer'),
(18036, 'Rajagopal Dharani', 'dharani@gmail.com', 2, 'Junior Developer'),
(18037, 'Rachitha B R', 'rachithabr@gmail.com', 4, 'Project Maintainer'),
(18045, 'Shreesha Pradeep', 'pradeepshreesh@gmail.com', 3, 'Senior Developer'),
(18050, 'Tarun Teja', 'tarunteja@gmail.com', 2, 'Project Maintainer'),
(18051, 'Tushitha Arun', 'tushithaarun@gmail.com', 4, 'Junior Developer'),
(18057, 'Yadunandan Bhat', 'yadunandanbhat@gmail.com', 1, 'Junior Developer'),
(18058, 'Yuktha Raj S', 'yuktharaj@gmail.com', 1, 'Senior Developer');

-- --------------------------------------------------------

--
-- Table structure for table `userGroup`
--

CREATE TABLE `userGroup` (
  `userGroupID` int NOT NULL,
  `userGroupName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `userGroup`
--

INSERT INTO `userGroup` (`userGroupID`, `userGroupName`) VALUES
(1, 'YYP'),
(2, 'TRH'),
(3, 'SPA'),
(4, 'PRT'),
(5, 'NNS');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attachments`
--
ALTER TABLE `attachments`
  ADD KEY `issueID` (`issueID`),
  ADD KEY `uploader` (`uploader`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD KEY `issueID` (`issueID`),
  ADD KEY `commenter` (`commenter`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`auditID`);

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
  ADD PRIMARY KEY (`issueID`),
  ADD KEY `projectID` (`projectID`),
  ADD KEY `issueSubmitter` (`issueSubmitter`),
  ADD KEY `assignedDeveloper` (`assignedDeveloper`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`projectID`),
  ADD KEY `projectMaintainer` (`projectMaintainer`),
  ADD KEY `projectGroup` (`projectGroup`);

--
-- Indexes for table `userDetails`
--
ALTER TABLE `userDetails`
  ADD PRIMARY KEY (`userID`),
  ADD KEY `userGroupID` (`userGroupID`);

--
-- Indexes for table `userGroup`
--
ALTER TABLE `userGroup`
  ADD PRIMARY KEY (`userGroupID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `auditID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
  MODIFY `issueID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `projectID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `userDetails`
--
ALTER TABLE `userDetails`
  MODIFY `userID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18059;

--
-- AUTO_INCREMENT for table `userGroup`
--
ALTER TABLE `userGroup`
  MODIFY `userGroupID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attachments`
--
ALTER TABLE `attachments`
  ADD CONSTRAINT `attachments_ibfk_1` FOREIGN KEY (`issueID`) REFERENCES `issue` (`issueID`),
  ADD CONSTRAINT `attachments_ibfk_2` FOREIGN KEY (`uploader`) REFERENCES `userDetails` (`userID`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`issueID`) REFERENCES `issue` (`issueID`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`commenter`) REFERENCES `userDetails` (`userID`);

--
-- Constraints for table `issue`
--
ALTER TABLE `issue`
  ADD CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`),
  ADD CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`issueSubmitter`) REFERENCES `userDetails` (`userID`),
  ADD CONSTRAINT `issue_ibfk_3` FOREIGN KEY (`assignedDeveloper`) REFERENCES `userDetails` (`userID`);

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`projectMaintainer`) REFERENCES `userDetails` (`userID`),
  ADD CONSTRAINT `project_ibfk_2` FOREIGN KEY (`projectGroup`) REFERENCES `userGroup` (`userGroupID`);

--
-- Constraints for table `userDetails`
--
ALTER TABLE `userDetails`
  ADD CONSTRAINT `userDetails_ibfk_1` FOREIGN KEY (`userGroupID`) REFERENCES `userGroup` (`userGroupID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
