------Create tables and constraints------
create table gym_event (id bigint not null auto_increment, event_name varchar(255), event_description varchar(255), date date, start_time time(6), end_time time(6), max_participants integer,  organizer_id bigint, event_type varchar(255), primary key (id)) engine=InnoDB;
create table participants (id bigint not null, profile_id bigint, event_id bigint not null, primary key (id)) engine=InnoDB;
create table workout_type (id bigint not null, workout_type_id bigint not null, primary key (id)) engine=InnoDB;
alter table participants add constraint FK74kt1iy6n0pd87x5mp2ithjqa foreign key (event_id) references gym_event (id);
alter table workout_type add constraint FKiug111l9uk240e0nedjvkke1c foreign key (workout_type_id) references gym_event (id);

------Initial Values------
INSERT INTO `gym_activities`.`gym_event`
(`id`, `event_name`, `event_description`, `date`, `start_time`, `end_time`, `max_participants`, `organizer_id`, `event_type`)
VALUES
(1, 'Workout Party', 'Exercise Together', '2024-05-25', '08:00:00', '09:00:00', 20, 101, 'The Force'),
(2, 'Strength Training', 'Intensive workout', '2024-05-26', '18:00:00', '19:30:00', 15, 102, 'Strength Training'),
(3, 'Chuck Norris Training', 'Try to match Chuck Norris', '2024-05-25', '10:30:00', '11:30:00', 25, 101, 'Chuck Norris');

INSERT INTO `gym_activities`.`participants`
(`id`, `profile_id`, `event_id`)
VALUES
(1, 2, 1),
(2, 3, 1),
(3, 4, 1),
(4, 5, 2),
(5, 6, 2),
(6, 7, 2),
(7, 8, 3),
(8, 9, 3),
(9, 10, 3);