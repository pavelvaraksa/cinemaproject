INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (220, 'Bob11', '$2a$10$JdDDr.uf4OKNCt.7l4fnFuehcvq9Ly6LJXjzlfvTjXMaAmpaiHazm', '2021-01-09 14:14:40.601000', '2021-01-09 14:14:40.601000', 'Bob', 'Surname111', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (221, 'string', 'string', '2021-01-09 14:42:05.206000', '2021-01-09 14:42:05.206000', 'string', 'string', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (222, 'string5', '$2a$10$m/ytm5HZEqV/IMj3Ev9p/enO7sHA9G7/Cj6vfpniOksXmEU51jRJ6', '2021-01-09 14:44:43.730000', '2021-01-09 14:44:43.730000', 'string', 'string', null);
INSERT INTO public.m_user (id, login, password, created, changed, name, surname, photo_link) VALUES (223, 'mike', '$2a$10$8beBfRnvJLszodbaDbKwOuIMzzAUzm6WCktvDEeS8sFNpVBNhMSxe', '2021-01-09 18:55:41.105000', '2021-01-09 18:55:41.105000', 'string', 'string', null);

INSERT INTO public.m_ticket (id, user_id, place_number, price, created, changed, event_id) VALUES (1, 222, 33, 5, '2021-01-10 16:14:50.000000', '2021-01-10 16:14:52.000000', 1);

INSERT INTO public.m_role (id, role_name, user_id) VALUES (109, 'ROLE_USER', 221);

INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (17, 'Fantasy island', 'Action, adventure, fantasy', 2020, 109, '2020-12-14 00:13:54.002000', '2020-12-14 00:13:54.002000', 59);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (10, 'Irishman', 'Biography, crime, drama', 2019, 209, '2020-12-14 00:03:53.558000', '2020-12-14 00:03:53.558000', 1);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (15, 'Underwater', 'Action, horror, sci-fi', 2020, 95, '2020-12-14 00:11:49.701000', '2020-12-14 00:11:49.701000', 57);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (8, 'I lost my body', 'Animation, drama, fantasy', 2019, 81, '2020-12-14 00:01:40.882000', '2020-12-14 00:01:40.882000', 59);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (3, '1917', 'Drama, war', 2019, 119, '2020-12-13 23:53:43.515000', '2020-12-13 23:53:43.515000', 54);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (23, 'Mulan', 'Action, adventure, drama', 2020, 115, '2020-12-14 00:19:47.104000', '2020-12-14 00:19:47.104000', 56);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (21, 'My spy', 'Action, comedy', 2020, 99, '2020-12-14 00:18:05.949000', '2020-12-14 00:18:05.949000', 54);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (6, 'Honey boy', 'Drama', 2019, 94, '2020-12-13 23:58:39.256000', '2020-12-13 23:58:39.256000', 57);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (19, 'Bloodshot', 'Action, drama, sci-fi', 2020, 109, '2020-12-14 00:15:45.507000', '2020-12-14 00:15:45.507000', 1);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (2, 'Ford vs Ferrari', 'Drama, biography', 2019, 152, '2020-12-13 23:49:03.995000', '2020-12-13 23:49:03.995000', 53);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (13, 'JoJo rabbit', 'Comedy, drama, war', 2019, 108, '2020-12-14 00:08:37.306000', '2020-12-14 00:08:37.306000', 55);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (14, 'Little women', 'Drama, romance', 2019, 135, '2020-12-14 00:10:02.292000', '2020-12-14 00:10:02.292000', 56);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (25, 'Secret garden', 'Drama, family, fantasy', 2020, 99, '2020-12-14 00:21:37.943000', '2020-12-14 00:21:37.943000', 58);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (18, 'Photograph', 'Drama, romance', 2020, 106, '2020-12-14 00:14:56.344000', '2020-12-14 00:14:56.345000', 60);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (16, 'Gentlemen', 'Action, comedy, crime', 2020, 113, '2020-12-14 00:12:57.003000', '2020-12-14 00:12:57.003000', 58);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (11, 'Knives out', 'Comedy, crime, drama', 2019, 130, '2020-12-14 00:05:12.273000', '2020-12-14 00:05:12.273000', 53);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (5, 'Parasite', 'Comedy, drama, thriller', 2019, 132, '2020-12-13 23:57:47.395000', '2020-12-13 23:57:47.395000', 56);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (24, 'Saint Maud', 'Drama, horror, mystery', 2019, 84, '2020-12-14 00:20:32.906000', '2020-12-14 00:20:32.906000', 57);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (4, 'Solstice', 'Drama, horror, mystery', 2019, 148, '2020-12-13 23:56:14.889000', '2020-12-13 23:56:14.889000', 55);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (20, 'The way back', 'Drama, sport', 2020, 108, '2020-12-14 00:16:44.185000', '2020-12-14 00:16:44.185000', 53);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (22, 'Inside the Rain ', 'Comedy, drama, romance', 2019, 90, '2020-12-14 00:18:50.617000', '2020-12-14 00:18:50.617000', 55);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (9, 'Lighthouse', 'Drama, fantasy, horror', 2019, 109, '2020-12-14 00:02:51.366000', '2020-12-14 00:02:51.366000', 60);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (12, 'Farewell', 'Comedy, drama', 2019, 100, '2020-12-14 00:07:46.891000', '2020-12-14 00:07:46.891000', 54);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (1, 'Joker', 'Thriller, drama', 2019, 122, '2020-12-13 23:45:48.060000', '2020-12-13 23:45:48.060000', 1);
INSERT INTO public.m_movie (id, title, genre, year, duration, created, changed, cinema_id) VALUES (7, 'Nightingale', 'Adventure, drama, thriller', 2018, 136, '2020-12-14 00:00:23.354000', '2020-12-14 00:00:23.354000', 58);

INSERT INTO public.m_location (id, location, created, changed) VALUES (1, 'Sovetsky district', '2020-12-14 00:29:19.907000', '2020-12-14 00:29:19.907000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (2, 'Centralny district', '2020-12-14 00:31:17.345000', '2020-12-14 00:32:38.530000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (3, 'Frunzensky district', '2020-12-14 00:33:36.263000', '2020-12-14 00:33:36.263000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (4, 'Moskovsky district', '2020-12-14 00:33:56.865000', '2020-12-14 00:33:56.865000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (5, 'Oktyabrsky district', '2020-12-14 00:34:23.004000', '2020-12-14 00:34:23.004000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (6, 'Leninsky district', '2020-12-14 00:34:48.647000', '2020-12-14 00:34:48.647000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (7, 'Zavodskoy district', '2020-12-14 00:35:08.650000', '2020-12-14 00:35:08.650000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (8, 'Partizansky district', '2020-12-14 00:35:41.115000', '2020-12-14 00:35:41.115000');
INSERT INTO public.m_location (id, location, created, changed) VALUES (9, 'Pervomaisky district', '2020-12-14 00:36:22.726000', '2020-12-14 00:36:22.726000');

INSERT INTO public.m_event (id, cinema_id, date, time, created, changed, tickets_count, ticket_id) VALUES (1, 55, '2021-01-10', '19:00:00', '2021-01-10 16:15:35.000000', '2021-01-10 16:15:36.000000', 100, 1);

INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (53, 'Aurora', 3, '+375 17 3838012', 'Cash, payment card', '2021-01-10 02:38:20.064000', '2021-01-10 02:38:20.064000', 'st. Pritytskogo, 153', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (54, 'Dom Kino', 9, '+375 17 2803526', 'Cash, payment card', '2021-01-10 02:40:18.867000', '2021-01-10 02:40:18.867000', 'st. Tolbukhina, 18', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (55, 'Oktyabr', 1, '+375 17 3999426', 'Cash, payment card', '2021-01-10 02:41:45.947000', '2021-01-10 02:41:45.947000', 'pt.Nezavisimosti, 73', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (56, 'Salut', 7, '+375 17 3674167', 'Cash, payment card', '2021-01-10 02:44:06.091000', '2021-01-10 02:44:06.091000', 'pt.Rokossovskogo, 150a', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (57, 'Komsomolets', 8, '+375 17 2954194', 'Cash, payment card', '2021-01-10 02:45:54.579000', '2021-01-10 02:45:54.579000', 'st.Zhilunovicha, 39', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (58, 'Berestie', 4, '+375 17 2079776', 'Cash, payment card', '2021-01-10 02:50:47.133000', '2021-01-10 02:50:47.133000', 'pt.Gazety Pranda, 25', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (59, 'Moskva', 5, '+375 17 2031448', 'Cash, payment card', '2021-01-10 02:52:38.605000', '2021-01-10 02:52:38.605000', 'pt.Pobediteley, 13', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (60, 'Raketa', 6, '+375 17 2982500', 'Cash, payment card', '2021-01-10 02:54:58.841000', '2021-01-10 02:54:58.841000', 'l.Rabochiy, 3', 1);
INSERT INTO public.m_cinema (id, name, location_id, phone_number, payment_method, created, changed, address, event_id) VALUES (1, 'Centralny', 2, '+375 17 3623416', 'Cash, payment card', '2020-12-14 01:22:13.540000', '2020-12-14 01:22:13.540000', 'pt.Nezavisimosti, 13', 1);