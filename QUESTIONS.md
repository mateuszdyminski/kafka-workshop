# Questions

List of doubts and questions:

* Język szkolenia - rozumiem, że polski? Zazwyczaj robię tak, że slajdy oraz opisy ćwiczeń są po angielsku, a samo szkolenie po polsku - dajcie znać czy to jest dla Was ok, czy może jednak macie obcokrajowca w zespole i samo szkolenie też miałoby być prowadzone po angielsku

```sh
Po polsku, slajdy i opisy jak wspomniałeś po angielsku
```

* Ile będzie osób na szkoleniu? Chciałbym wiedzieć ile będzie osób, bo w momencie odpalania jakiś przykładów potrzebowałbym więcej czasu jeśli tych osób miałoby być więcej

```sh
Planujemy 8-9
```

* Czy pasowałby Wam termin 3.07.2019 - środa ? Godziny dowolne, ale pewnie byłoby to 8-16:30 ewentualnie 9-17:30 - 8h szkolenia + 30 mins na przerwę na lunch - W sumie zostały już niecałe 2 tygodnie i jeśli z pytań technicznych, które zamieszczam poniżej wybralibyście wiele różnych opcji to chciałbym mieć też furtkę bezpieczeństwa i możliwość przerzucenia szkolenia na jeszcze kolejny tydzień tj. 8 lub 9 lipca 2019. Finalną decyzję chciałbym podjąć po udzieleniu odpowiedzi na poniższe pytania. Mam nadzieję, że rozumiesz, że jest to szkolenie "szyte na miarę” pod Waszą firmę, więc przygotowanie może zająć nieco więcej czasu.

```sh
Środa 3.07.2019 to jest bardzo dobry termin, pozostałe 8,9 gorsze ale możliwe
8.00 – 16.30 to chyba najlepsze godziny
```

Pytania techniczne:

* Jaki system operacyjny będą mieli uczestnicy? Pewnie część przykładów zrobie w oparciu o Kafke w Dockerze - i chciałbym mieć pewność, że każdy będzie mógł odpalić wszystkie przykłady

```sh
Windows/Mac - środowisko Docker jest ok (wszyscy uczestnicy go obsługują)
```

* Jaka jest domena Waszego projektu? Miałby to być system bankowy? Telekomunikacja? Etc. Jeśli podesłalibyście mi to w miarę szybko to wtedy mógłbym zrobić przykłady pod Was

```sh
System finansowy – prztewarzanie asychroniczne płatności może być dobrym przykładem
```

* Gdzie docelowo miałaby być zdeploywana Kafka? AWS? Dedykowane serwery? Kluster Kubernetes’a ? Ma to znaczenie przy konfiguracji samej Kafki

```sh
Kubernetes na GCP
```

* Gdzie chcielibyście przechowywać Backupy? Widziałem, że jest punkt odnośnie przywracania z zewnętrznych dysków, dajcie znać co zamierzacie tutaj użyć. Jakieś S3? Etc.

```sh
Google Cloud Storage, ale można ćwiczenie uprościć do z dysku lokalnego
```

* Czy zamierzacie monitorować Kafkę sami? Czy potrzebowalibyście też jakiś punktów odnośnie monitorowania samego klustra? Konfiguracja JMX etc.

```sh
sami, przydadzą się tipy dot. zbioru ‘dobrych’ metryk
```

* Jeśli tak to jakiego narzędzie zamierzacie używać do monitorowania klustra? Prometheusa? Czy czegoś innego?

```sh
Prometheus
```

* Którą wersję Kafki zamierzacie użyć? Czy jesteście zobligowani do użycia konkretnej wersji Kafki? Czy raczej planujecie użyć najnowszej możliwej wersji?

```sh
Używamy 1.1.0, ale raczej patrząc na zmiany z ostatniego roku przejdziemy na 2.2.x
```

* Security - czy zamierzacie używać jakiegoś szczególnego podejścia do bezpieczeństwa przy użyciu Kafki?

```sh
przydałby się przykład z autoryzowanym dostępem
```

* Czy zamierzacie używać Kafka Streams?

```sh
tak
```

* Czy zamierzacie używać Kafka Connect?

```sh
tak
```

* Czy zamierzacie używać KSQL?

```sh
tu już mniej, ale może kiedyś – chyba się nie wyrobimy czasowo?
```

* Rozumiem, że przykłady mają być w Java 8+

```sh
tak
```

## Questions to ask

* AVRO - czy zamierzacie uzywać?
* Czy ten system juz dziala? Jesli tak to jak bedzie wywgladala instalacja nowej Kafki? Kto tym zarzadza?
* Jak wygląda
