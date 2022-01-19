INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (228, 'login1', '$2a$10$QsRfg44lXosFjOeLNxEnvO1LnhyIzWOiGDLL2x4Mstmio7nWi6W8S', '2021-01-11 00:55:24.721000', '2021-01-11 00:55:24.721000', 'Jack', 'Surname', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (230, 'login2', '$2a$10$t1IFxP3d9x.cRHu79HQhjOhEaCqUD0gS2F2yN2vsugMoYHHF.xp3u', '2021-01-11 01:11:57.757000', '2021-01-11 01:11:57.757000', 'John', 'Surname2', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (231, 'login3', '$2a$10$evkGH4sHSgyGtzRauSNxZuc09sWYqxEgHNbe.ehoaGbyUdgELCtHy', '2021-01-11 01:12:12.800000', '2021-01-11 01:12:12.800000', 'Billy', 'Surname3', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (232, 'login4', '$2a$10$Xs4m/8VM5D0133XVdDrLfuud8yKm3vVvLVz8XyOlw4yB6hgTzduuC', '2021-01-11 01:12:34.661000', '2021-01-11 01:12:34.661000', 'Jerry', 'Surname4', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (234, 'login8', '$2a$10$GAiskJw33Ed2CM6IdYzrwOgvWXO2vk0JlN0VBbic1YcgesdyHhVJi', '2021-01-11 01:22:44.948000', '2021-01-11 01:22:44.948000', 'Johnny', 'Surname8', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (235, 'login9', '$2a$10$1mvQpO.Z5Sa/e0kfMpcfUu5Evu7ukriCr/cj8gT8qNP3qCgWbiNdC', '2021-01-11 01:22:58.915000', '2021-01-11 01:22:58.915000', 'JohnnyB', 'Surname9', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (236, 'login10', '$2a$10$fL3yQ/uU5BnW2lMcEx3iu.CDnVMBuR0oiLIpDn9dY5QTqeU5uW5mq', '2021-01-11 01:23:16.050000', '2021-01-11 01:23:16.050000', 'Mark', 'Surname10', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (237, 'login11', '$2a$10$cXRabjjym7PQ8zB.jiyNDu4x.Ft7OSLzaye5Rew3DGGviV125IcAG', '2021-01-11 01:23:24.997000', '2021-01-11 01:23:24.997000', 'Markus', 'Surname11', null);

INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (1, 228, 49, 5, '2021-01-11 01:06:26.000000', '2021-01-11 01:06:27.000000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (2, 230, 33, 4, '2021-01-11 01:19:14.000000', '2021-01-11 01:19:15.000000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (39, 230, 44, 5, '2021-01-11 01:21:28.619000', '2021-01-11 01:21:28.619000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (40, 231, 20, 5, '2021-01-11 01:21:45.743000', '2021-01-11 01:21:45.743000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (41, 232, 3, 5, '2021-01-11 01:21:56.696000', '2021-01-11 01:21:56.696000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (42, 234, 66, 4, '2021-01-11 01:23:51.222000', '2021-01-11 01:23:51.222000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (43, 235, 78, 7, '2021-01-11 01:24:06.904000', '2021-01-11 01:24:06.904000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (44, 236, 13, 4, '2021-01-11 01:24:20.463000', '2021-01-11 01:24:20.463000');
INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed) VALUES (45, 237, 77, 3, '2021-01-11 01:24:31.600000', '2021-01-11 01:24:31.600000');

INSERT INTO public.m_role (id, role_name, user_id) VALUES (114, 'ROLE_USER', 228);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (116, 'ROLE_USER', 230);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (117, 'ROLE_USER', 231);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (118, 'ROLE_USER', 232);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (120, 'ROLE_USER', 234);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (121, 'ROLE_USER', 235);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (122, 'ROLE_USER', 236);
INSERT INTO public.m_role (id, role_name, user_id) VALUES (123, 'ROLE_USER', 237);

INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (17, 'Fantasy island', 'Action, adventure, fantasy', 2020, 109, '2020-12-14 00:13:54.002000', '2020-12-14 00:13:54.002000', 59);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (10, 'Irishman', 'Biography, crime, drama', 2019, 209, '2020-12-14 00:03:53.558000', '2020-12-14 00:03:53.558000', 1);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (23, 'Mulan', 'Action, adventure, drama', 2020, 115, '2020-12-14 00:19:47.104000', '2020-12-14 00:19:47.104000', 56);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (24, 'Saint Maud', 'Drama, horror, mystery', 2019, 84, '2020-12-14 00:20:32.906000', '2020-12-14 00:20:32.906000', 57);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (20, 'The way back', 'Drama, sport', 2020, 108, '2020-12-14 00:16:44.185000', '2020-12-14 00:16:44.185000', 53);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (9, 'Lighthouse', 'Drama, fantasy, horror', 2019, 109, '2020-12-14 00:02:51.366000', '2020-12-14 00:02:51.366000', 60);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (2, 'Ford vs Ferrari', 'Drama, biography', 2019, 152, '2020-12-13 23:49:03.995000', '2020-12-13 23:49:03.995000', 54);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (11, 'Knives out', 'Comedy, crime, drama', 2019, 130, '2020-12-14 00:05:12.273000', '2020-12-14 00:05:12.273000', 55);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (4, 'Solstice', 'Drama, horror, mystery', 2019, 148, '2020-12-13 23:56:14.889000', '2020-12-13 23:56:14.889000', 58);

INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (1, 'Sovetsky district', '2020-12-14 00:29:19.907000', '2020-12-14 00:29:19.907000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (2, 'Centralny district', '2020-12-14 00:31:17.345000', '2020-12-14 00:32:38.530000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (3, 'Frunzensky district', '2020-12-14 00:33:36.263000', '2020-12-14 00:33:36.263000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (4, 'Moskovsky district', '2020-12-14 00:33:56.865000', '2020-12-14 00:33:56.865000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (5, 'Oktyabrsky district', '2020-12-14 00:34:23.004000', '2020-12-14 00:34:23.004000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (6, 'Leninsky district', '2020-12-14 00:34:48.647000', '2020-12-14 00:34:48.647000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (7, 'Zavodskoy district', '2020-12-14 00:35:08.650000', '2020-12-14 00:35:08.650000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (8, 'Partizansky district', '2020-12-14 00:35:41.115000', '2020-12-14 00:35:41.115000', null);
INSERT INTO public.m_location (id, location, created, changed, event_id) VALUES (9, 'Pervomaisky district', '2020-12-14 00:36:22.726000', '2020-12-14 00:36:22.726000', null);

INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (8, '05/01/2021', '14:30', '2021-01-11 00:47:00.000000', '2021-01-11 00:47:01.000000', 90, 44);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (2, '07/01/2021', '16:00', '2021-01-11 00:45:36.000000', '2021-01-11 00:45:38.000000', 100, 2);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (9, '04/01/2021', '21:30', '2021-01-11 00:47:12.000000', '2021-01-11 00:47:14.000000', 100, 45);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (1, '02/01/2021', '19:00', '2021-01-11 00:44:41.000000', '2021-01-11 00:44:52.000000', 100, 1);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (7, '14/01/2021', '18:00', '2021-01-11 00:46:48.000000', '2021-01-11 00:46:49.000000', 100, 43);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (6, '12/01/2021', '17:00', '2021-01-11 00:46:33.000000', '2021-01-11 00:46:34.000000', 100, 42);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (5, '11/01/2021', '20:30', '2021-01-11 00:46:21.000000', '2021-01-11 00:46:22.000000', 110, 41);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (3, '14/01/2021', '20:00', '2021-01-11 00:45:50.000000', '2021-01-11 00:45:52.000000', 80, 39);
INSERT INTO public.m_event (id, date, time, created, changed, tickets_count, ticket_id) VALUES (4, '09/01/2021', '15:00', '2021-01-11 00:46:02.000000', '2021-01-11 00:46:03.000000', 90, 40);

INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (53, 'Aurora', 3, '+375 17 3838012', 'Cash, payment card', '2021-01-10 02:38:20.064000', '2021-01-10 02:38:20.064000', 'st. Pritytskogo, 153');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (54, 'Dom Kino', 9, '+375 17 2803526', 'Cash, payment card', '2021-01-10 02:40:18.867000', '2021-01-10 02:40:18.867000', 'st. Tolbukhina, 18');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (55, 'Oktyabr', 1, '+375 17 3999426', 'Cash, payment card', '2021-01-10 02:41:45.947000', '2021-01-10 02:41:45.947000', 'pt.Nezavisimosti, 73');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (56, 'Salut', 7, '+375 17 3674167', 'Cash, payment card', '2021-01-10 02:44:06.091000', '2021-01-10 02:44:06.091000', 'pt.Rokossovskogo, 150a');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (57, 'Komsomolets', 8, '+375 17 2954194', 'Cash, payment card', '2021-01-10 02:45:54.579000', '2021-01-10 02:45:54.579000', 'st.Zhilunovicha, 39');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (58, 'Berestie', 4, '+375 17 2079776', 'Cash, payment card', '2021-01-10 02:50:47.133000', '2021-01-10 02:50:47.133000', 'pt.Gazety Pranda, 25');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (59, 'Moskva', 5, '+375 17 2031448', 'Cash, payment card', '2021-01-10 02:52:38.605000', '2021-01-10 02:52:38.605000', 'pt.Pobediteley, 13');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (60, 'Raketa', 6, '+375 17 2982500', 'Cash, payment card', '2021-01-10 02:54:58.841000', '2021-01-10 02:54:58.841000', 'l.Rabochiy, 3');
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address) VALUES (1, 'Centralny', 2, '+375 17 3623416', 'Cash, payment card', '2020-12-14 01:22:13.540000', '2020-12-14 01:22:13.540000', 'pt.Nezavisimosti, 13');

