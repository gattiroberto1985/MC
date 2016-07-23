# Analisi MyCash 

Applicativo per la gestione dei conti di casa.

L'applicazione sarà costituita da:

- un modulo web (`mycash-web`) che contiene la componente di frontend, quindi tutte le
  viste dell'applicazione, la logica di navigazione. Idealmente avrà solo quello, e farà
  solo chiamate REST ai servizi opportuni (implementati nel modulo specifico), la 
  gestione dell'output e la presentazione del risultato;

- un modulo jar dedicato ai servizi REST (`mycash-rest`), in cui verranno implementati
  tutti i servizi che l'applicazione dovrà chiamare;

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
via mvc, con tutti i mapping del caso (`login` in `get` e `post`, ...) e, a fianco, sarà presente il rispettivo servizio REST per eventuali accessi da
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


# Applicazione Web -- Navigazione

L'applicazione

# Servizi Rest per accesso extra applicazione Web

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

# Riferimenti
Da 