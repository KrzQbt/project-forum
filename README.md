# project-forum
Projekt końcowy na zaliczenie:

Temat: Forum

Opis: Użytkownicy mogą zakładać wątki, dodawać wpisy do wątków. Administratorzy mogą wątki usuwać, usuwać wpisy lub banować userów. Projekt spełnia powyższy opis.

Dodatkowo zaimplementowałem stronicowanie wyników w wątkach z użyciem hibernate i servicu obliczajacego rozmiar i treść strony w zależności od ilości postów w wątku.

!Wypełnienie bazy danych pod adresem 172.0.0.1:8080/db (restowy serwis), dobrze to zrobić po uruchomieniu. Jest też kilka testów dla prostych metod.

Hibernate hbm2ddl jest ustawiony na create, więc po restarcie serwera baza się skasuje i można skorzystać od nowa z serwisu.

Po stworzeniu nowego postu controler przenosi na pierwszą stronę wątku, by zobaczyć nowy post trzeba przejść na ostatnią klikając na odpowiednią cyfrę.

W bazie jest kilku użytkowników, hasło takie jak login(Tylko admin ma rolę ADMIN, reszta USER, nikt nie jest zbanowany na początku):

admin, polska, banned, junit, nowy1

Polecam zalogować się na admin i zbannować w wątku np. banned, zobaczyc w drugiej karcie co widzi zbanowany i odblokować go i znowu wejsc w wątek. (routy są chronione i zbanowani są przekierowywani przy kazdej akcji na odpowiednia stronkę).
