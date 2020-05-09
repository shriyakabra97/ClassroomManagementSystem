INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('4', 'manav', 'manav', 'admin');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('1', 'pilaniya', 'pilaniya', 'admin');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('2', 'pragati', 'pragati', 'admin');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('3', 'shriya', 'shriya', 'admin');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('5', 'prof1', 'prof1', 'professor');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('6', 'prof2', 'prof2', 'professor');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('7', 'ta1', 'ta1', 'ta');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('8', 'ta2', 'ta2', 'ta');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('9', 'sac1', 'sac1', 'sac');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('10', 'sac2', 'sac2', 'sac');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('11', 'committee1', 'committee1', 'committee');
INSERT INTO `cms_db`.`login` (`login_id`, `password`, `user_name`, `user_type`) VALUES ('12', 'committee2', 'committee2', 'committee');

INSERT INTO `cms_db`.`professor` (`professor_id`, `professor_email`, `professor_name`, `user_name`, `foreign_id`) VALUES ('1', 'prof1@iiitb.org', 'prof1 name', 'prof1', '5');
INSERT INTO `cms_db`.`professor` (`professor_id`, `professor_email`, `professor_name`, `user_name`, `foreign_id`) VALUES ('2', 'prof2@iiitb.org', 'prof2 name', 'prof2', '6');

INSERT INTO `cms_db`.`sac` (`sac_id`, `sac_email`, `sac_name`, `user_name`, `foreign_id`) VALUES ('1', 'sac1@iiitb.org', 'sac1 name', 'sac1', '9');
INSERT INTO `cms_db`.`sac` (`sac_id`, `sac_email`, `sac_name`, `user_name`, `foreign_id`) VALUES ('2', 'sac2@iiitb.org', 'sac2 name', 'sac2', '10');

INSERT INTO `cms_db`.`ta` (`ta_id`, `ta_email`, `ta_name`, `user_name`, `foreign_id`) VALUES ('1', 'ta1@iiitb.org', 'ta1', 'ta1 name', '7');
INSERT INTO `cms_db`.`ta` (`ta_id`, `ta_email`, `ta_name`, `user_name`, `foreign_id`) VALUES ('2', 'ta@iiitb.org', 'ta2', 'ta2 name', '8');

INSERT INTO `cms_db`.`committee` (`committee_id`, `committee_email`, `committee_name`, `user_name`, `foreign_id`) VALUES ('1', 'committee1@iiitb.org', 'committee1 name', 'committee1', '11');
INSERT INTO `cms_db`.`committee` (`committee_id`, `committee_email`, `committee_name`, `user_name`, `foreign_id`) VALUES ('2', 'committee12@iiitb.org', 'committee2 name', 'committee2', '12');

INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('1', '50', 'A101', '2', b'1');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('2', '45', 'A102', '3', b'1');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('3', '60', 'A103', '1', b'1');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('4', '50', 'A104', '2', b'0');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('5', '60', 'R101', '2', b'1');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('6', '50', 'R102', '1', b'0');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('7', '40', 'R103', '2', b'1');
INSERT INTO `cms_db`.`classroom` (`classroom_id`, `capacity`, `class_code`, `plugs`, `projector`) VALUES ('8', '30', 'R104', '2', b'1');

