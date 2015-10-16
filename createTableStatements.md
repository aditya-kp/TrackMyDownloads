# TrackMyDownloads

CREATE TABLE `file` (
  `fileId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique file id',
  `fileName` varchar(45) NOT NULL COMMENT 'name of the file',
  `path` varchar(60) NOT NULL COMMENT 'absolute path of the file',
  `frequency` int(11) NOT NULL DEFAULT '0' COMMENT 'number of times the file was opened (from this application)',
  PRIMARY KEY (`fileId`),
  UNIQUE KEY `fileId_UNIQUE` (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='table of files';

CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'unique tag id',
  `tagname` varchar(20) NOT NULL COMMENT 'name of the tag',
  PRIMARY KEY (`tagid`),
  UNIQUE KEY `idtags_UNIQUE` (`tagid`),
  UNIQUE KEY `tagname_UNIQUE` (`tagname`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='table containing tags';

CREATE TABLE `tagged_to` (
  `tagid` int(11) NOT NULL,
  `fileid` int(11) NOT NULL,
  PRIMARY KEY (`tagid`,`fileid`),
  KEY `fk_fileid_idx` (`fileid`),
  CONSTRAINT `fk_fileid` FOREIGN KEY (`fileid`) REFERENCES `file` (`fileId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tagid` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table to implement the file-tag relation';
