# Analisi MyCash

Applicativo per la gestione dei conti di casa.

L'applicazione sarà costituita da:

- un modulo web (`mycash-web`) che contiene la componente di frontend, quindi
  tutte le viste dell'applicazione, la logica di navigazione. Idealmente avrà
  solo quello, e farà solo chiamate REST ai servizi opportuni (implementati nel
  modulo specifico), la gestione dell'output e la presentazione del risultato;

- un modulo jar dedicato ai servizi REST (`mycash-rest`), in cui verranno
  implementati tutti i servizi che l'applicazione dovrà chiamare;

- un modulo jar, dedicato alla persistenza dei dati.

Usando spring MVC, c'è già una mini implementazione di alcune risorse REST,
dato che nei controller vanno specificati. La differenza tra controller
Spring MVC e servizio REST è sottile ma c'è. In pratica, consiste nel
modo in cui la response HTTP viene creata:

- il controller MVC tipicamente redirige verso una view per poi eseguire
  altra logica server per presentare i dati;
- i servizi REST tipicamente popolano e ritornano un oggetto, che viene
  scritto nel body della response sotto forma di JSON.

Quindi, in sostanza, per la nostra applicazione, avremo l'utilizzo standard
via mvc, con tutti i mapping del caso (`login` in `get` e `post`, ...) e, a
fianco, sarà presente il rispettivo servizio REST per eventuali accessi da
altre app (client android, client pc, operazioni in generale). Da verificare
se si riesce ad esternalizzare tutta la logica, mantenendo semplici i
controller e chiamando funzioni generiche nei rest. Ad esempio, per capirsi,
per il login:

- controller MVC con:

		@RequestMapping( value = "/login", method = RequestMethod.POST )
		public String postLogin()
		{
			String user = ... , pass = ... ;
			LoginController lc = ...;
			Result r = lc.login();
			return r.isOk() ? "homepage" : "redirect:error";
		}

- servizio rest con:

		public JSON postLogin(...)
		{
			...
			LoginController lc = ... ;
			Result r = lc.login();
			return responseWithCode;
		}

- vero esecutore della logica:

		public class LoginController ... {
			...
			public Result login() {
				...
			}
		}

In questo modo, servizio rest e controller MVC utilizzano una interfaccia
terza, con un unico punto in cui andare a modificare il codice di gestione.
Magari usare una interfaccia, che sia controller mvc che servizio rest
dovranno implementare. Attenzione che i servizi REST dovranno infine
ritornare un JSON, mentre i controller potranno fare elaborazioni aggiuntive.

# Funzioni dell'applicazione

L'utente, dopo la prima fase di login, verrà rediretto verso la sua home page
in cui potrà monitorare la situazione finanziaria generale.

In home page sono riportati:

- i vari conti con i relativi dettagli (tipologia e saldo);
- un grafico dell'andamento finanziario generale e dei singoli conti, con
  possibilità di filtraggio;
- un form di aggiunta operazione, con possibilità di scelta tra una serie di
  operazioni predefinite;

Dalla home page dovrà essere raggiungibile una pagina di amministrazione
personale, in cui l'utente potrà:

- modificare la sua password;
- gestire le categorie di spesa (aggiunta, rinomina, rimozione);
- creare nuovi conti da gestire;
- gestire i tag da assegnare ad operazioni e conti;
- gestire le varie operazioni rapide predefinite.

E' possibile poi raggiungere la pagina di dettaglio di un singolo conto, in
cui verranno riportate le informazioni generali (saldo, tipologia e dettagli
specifici) e la lista di operazioni in ingresso ed in uscita su questo conto.

## Interfaccia web

Costituita da quattro pagine jsp:

- `login`    : con url definito dalla root;
- `homepage` : con url a `users/:id_user/homepage`;
- `portfolio`: con url a `users/:id_user/portfolio/:id_ptf`;
- `account`  : con url a `users/:id_user/portfolio/:id_ptf/accounts/:id_acn`;
- `mvmnts`   : con url a `users/:id_user/portfolio/:id_ptf/accounts/:id_acn/movmnts`;
- `admin`    : con url a `users/:id_user/admin`;

## Servizi REST

Saranno da definire:

- `LoginService`, con chiamata `POST` all'url `/rest/login`. In output dovrà
  essere restituito un oggetto JSON del tipo:

        {
            "result": <HTML_CODE>,
            "data"  : {
                "message": <RESULT_MESSAGE>
            }
        }

- `UserData`, che eseguirà la gestione degli utenti. L'url generico assegnato è
  `/rest/users` e i metodi invocati dovranno essere differenziati per ruoli.
  In particolare, al solo amministratore sarà ammesso:

    - eseguire `GET` all'url di root, per recuperare i dati specifici di
      tutti gli utenti; l'output sarà un JSON tipo:

            {
                "result": <code>,
                "data"  : {
                    [
                        "user": { ... } ,
                        "user": { ... } ,
                        ...
                    ]
                }
            }

    - eseguire una `POST` all'url di root per l'aggiunta di un nuovo user; in
      output dovrà essere fornito il solito JSON con i dati dell'utente
      aggiornato nell'oggetto `data`;

    - eseguire `DELETE` e `PUT` all'url `/rest/users/:id` per la rimozione e
      l'aggiornamento dei dati dell'utente specifico; nel primo caso verrà
      restituito un JSON con solo un messaggio di avvenuta cancellazione,
      mentre con il `PUT` verrà restituito l'oggetto aggiornato.</br></br>

  Al normale utente sarà invece permesso di operare solo sul suo specifico
  sottourl, `/rest/users/:id`. Qui potrà eseguire una `PUT` per l'aggiornamento
  dei suoi dati personali.

- `Portfolio`, su cui si andrà a gestire le operazioni di testata sui vari
  portafoglio dell'utente. L'url di riferimento è `/rest/portfolios`.
  All'utente saranno consentite le seguenti operazioni:

  - `GET`, con parametri `userId` ed altri filtri, per recuperare tutti i
    portafoglio relativi all'utente con id specificato; il JSON di ritorno
    sarà simile a quello degli utenti indicato sopra;
  - `POST` su url di root per aggiunta nuovo portafoglio (json in output con
    il nuovo portfolio creato);
  - `DELETE` su url specifico, `/rest/portfolios/:id_ptf`, per rimozione
    portafoglio (JSON in output con il messaggio di avvenuta rimozione);
  - `PUT` su url specifico per aggiornamento dettagli (JSON in output con il
    portfolio aggiornato);


- `Account`, relativo alla gestione dei conti. L'url di riferimento sarà
  `/rest/accounts/`, e i metodi saranno i seguenti:

  - `GET`, con eventuali parametri (in particolare l'id portafoglio), per il
    recupero dei conti. In output il solito oggetto JSON con l'array di dati;
    Sarà definito anche sui singoli id, `/rest/accounts/:id_acn`;

  - `POST`, per la creazione di un nuovo conto;

  - `PUT` e `DELETE` su id specifico, per l'aggiornamento o la rimozione. In
    output il JSON completo del conto o il messaggio di avvenuta cancellazione;

- `Transaction`, relativo alle operazioni eseguite. Url di riferimento è
  `/rest/transactions`, e da qui verranno gestite le varie operazioni sulle
  transazioni.


Esisterà una sequenza di invocazione, ad esempio per l'ingresso in home page:

1. login utente: se ko, ritorno a pagina login con messaggio errore;
2. recupero dati utente (chiamata a `/rest/users/:user_id`), che ritorna JSON
   con oggetto utente (rest);
3. recupero dei portafoglio (chiamata a `/rest/portfolios?user_id=:user_id`),
   che ritorna JSON con oggetto portfolio (rest);
4. recupero dei conti relativi ai portafoglio. Per ogni `ptf_id`, verrà invocato
   `/rest/accounts?ptf_id=:ptf_id`, e in output JSON con lista account;
5. per ogni account, verranno recuperate le ultime `N` operazioni, con chiamata
   `/rest/transactions?acn_id=:acn_id&max_num=N`

I dati così recuperati verranno aggregati nell'oggetto di business `UserBean`
che conterrà tutti i dati del caso.

<!--
    TODO: Da valutare l'inserimento di "alias" per i REST, del tipo:

    /rest/users/:user_id/portfolios -> /rest/portfolios?&user_id=:user_id
    /rest/users/:user_id/portfolios/:id_ptf/accounts/:acn_id  ->
            /rest/accounts?&acn_id=:acn_id

    questo per definire una sorta di "scope" dei vari bean a runtime.
-->

# Bean applicativi

## Oggetto utente `UserBean`

Lo `UserBean` sarà costituito da un id, un nome utente e una serie di
portafoglio. Avrà inoltre associati a sè una serie di tag, con cui potrà
etichettare i vari oggetti che dovrà gestire. Inoltre, ad ogni utente sarà
associato un insieme personalizzabile di operazioni predefinite, modificabile
a piacere (spese frequenti, ricorrenti).

## Oggetto portafoglio `PortfolioBean`

Ogni utente potrà avere associati diversi portafoglio (mappatura `1:N`), che
a loro volta, avranno una mappatura `N:M` con i conti (esistendo dei conti
condivisi). Ogni portafoglio porterà con sè le informazioni relative allo stato
generale (saldo totale di tutti i conti relazionati al portafoglio).

## Oggetto conto `AccountBean`

Ogni conto potrà essere associato ad uno o più portafoglio (che a loro volta
potranno appartenere ad uno o più utenti) e sarà definito, oltre che dal solito
id numerico, da una serie di attributi:

- tipologia del conto (corrente, di risparmio, generico);
- descrizione del conto;


## Oggetto transazione `TransactionBean`

Rappresenta una transazione tra due conti, e può essere di tre tipologie
predefinite:

- Incasso (saldo netto positivo);
- Spesa (saldo netto negativo);
- Trasferimento (saldo netto nullo).

In generale ogni transazione avrà come attributi (oltre all'immancabile id
numerico):

- tipologia;
- importo;
- data;
- descrizione;
- conto origine (obbligatorio se operazione di spesa o di trasferimento);
- conto destinazione (obbligatorio se operazione di incasso o di trasferimento).


<br/>


# Applicazione Web -- Navigazione


# Servizi Rest per accesso extra applicazione Web

Per l'implementazione dei servizi servono:

- i bean da gestire (che potrebbero coincidere con i bean di hibernate);
- le classi con i vari servizi da implementare.


## Fase di login
L'utente dovrà eseguire il log in all'applicazione tramite pagina dedicata
inviata dal server, in successione alla chiamata ad un qualsiasi endpoint
dell'applicazione se il cookie dedicato alla sessione non e' presente nella
cache del browser.
L'endpoint chiamato sarà `login`, in `GET` per la richiesta della pagina di
login ed in `post` per l'esecuzione del login stesso. In caso di successo,
l'utente dovra' essere reindirizzato (`GET`) alla home page, endpoint `home`.

La chiamata `post` alla login dovrà contenere username e password in chiaro
nel body della request e dovrà essere inviata sotto `https`.

Oggetti impattati nella fase:

- Bean `User`, con id, nome utente e password. Da registrare su tabella apposita

# Cosa studiare

- framework per il logging: java.util o jlog?
- Spring framework o qualcosa di piu' leggero?
- Gestione servizi rest: `resteasy`, `jersey`, anche qui spring?
- hibernate
- Rest e oggetti: con jersey è buona idea usare jaxb, vedi qui:
    http://docs.oracle.com/javaee/6/tutorial/doc/gkknj.html

# Riferimenti
Da
