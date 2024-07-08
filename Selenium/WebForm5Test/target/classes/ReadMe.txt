									READ ME

PUBLIC CLASS WEBTABLESTEST

Inizio metodo contaRigheTest:
    Trova l'elemento della tabella tramite la classe "rt-tbody"
    Estrai tutte le righe dalla tabella tramite la classe "rt-tr-group"
    Inizializza una variabile per contare le righe piene e impostala a 0
    
    Per ogni riga nella lista delle righe:
        Se il testo della riga (dopo aver rimosso gli spazi vuoti) non è vuoto:
            Incrementa il conteggio delle righe piene di 1
    
    Stampare il numero di righe piene nella tabella utilizzando un logger
_________________________________________________________________________________________________________________

InserimentoDatiCorrettoTest:
    TestInserimentoDatiCorretto:
        inserisciEDaiSubmitEVerifica()

inserisciEDaiSubmitEVerifica():
    CliccaSulPulsanteAggiungiNuovoRecord()
    InserisciDatiDaInserire()
    CliccaSuSubmit()
    VerificaCampiRiempitiCorrettamente()

CliccaSulPulsanteAggiungiNuovoRecord():
    AttendereEVisualizzareElementoECliccare(utilizzando l'identificatore "addNewRecordButton")

InserisciDatiDaInserire():
    PulisciEInviaTesto(utilizzando l'identificatore "firstName" con "Francesco")
    PulisciEInviaTesto(utilizzando l'identificatore "lastName" con "Nero")
    PulisciEInviaTesto(utilizzando l'identificatore "userEmail" con "NeroF@cicciolino.it")
    PulisciEInviaTesto(utilizzando l'identificatore "age" con "23")
    PulisciEInviaTesto(utilizzando l'identificatore "salary" con "13000")
    PulisciEInviaTesto(utilizzando l'identificatore "department" con "Informatica")

CliccaSuSubmit():
    Clicca(utilizzando l'identificatore "btn btn-primary")
_________________________________________________________________________________________________________________

VerificaCampiRiempitiCorrettamente():
    VerificaCampoTestoUguale(utilizzando l'identificatore "firstName" con "Francesco")
    VerificaCampoTestoUguale(utilizzando l'identificatore "lastName" con "Nero")
    VerificaCampoTestoUguale(utilizzando l'identificatore "userEmail" con "NeroF@cicciolino.it")
    VerificaCampoTestoUguale(utilizzando l'identificatore "age" con "23")
    VerificaCampoTestoUguale(utilizzando l'identificatore "salary" con "13000")
    VerificaCampoTestoUguale(utilizzando l'identificatore "department" con "Informatica")

VerificaCampoTestoUguale(elemento, testoPrevisto):
    campo = TrovaElemento(elemento)
    testoAttuale = OttenereAttributo(campo, "value")
    Assert(Equals(testoAttuale, testoPrevisto), "Il campo " + elemento + " non contiene il testo previsto.")
_________________________________________________________________________________________________________________

Funzione leggiTableColonna():
    Trova la tabella utilizzando un identificatore unico come "id_della_tabella"
    Per ogni riga nella tabella:
        Trova tutte le celle nella riga
        Per ogni cella nella riga:
            Stampa il contenuto della cella utilizzando un logger
_________________________________________________________________________________________________________________

Test: testEditAndCleanRiga
1. Inserire dati da modificare.
2. Fare clic sull'elemento con la classe "mr-2".
3. Pulire e inserire il testo "fattiMiei@liberato.it" nell'elemento con l'id "userEmail".
4. Pulire e inserire il testo "18" nell'elemento con l'id "age".
5. Fare clic sul pulsante con l'id "submit".
6. Verificare che la riga sia stata eliminata correttamente.
   6.1. Fare clic sull'elemento con l'id "delete-record-1".
   6.2. Verificare che la riga sia stata eliminata correttamente.
        - Se l'elemento con l'id "record-1" esiste ancora, loggare "La riga esiste ancora" e restituire falso.
        - Altrimenti, loggare "La riga è stata eliminata correttamente" e restituire vero.

Metodo: isRowDeleted()
1. Verificare se la riga è stata eliminata controllando se esiste ancora l'elemento che la rappresenta.
   1.1. Provare a trovare l'elemento con l'id "record-1".
        - Se l'elemento viene trovato, loggare "La riga esiste ancora" e restituire falso.
        - Altrimenti, gestire l'eccezione e loggare "La riga è stata eliminata correttamente", quindi restituire vero.

__________________________________________________________________________________________________________________________
Funzione ricercaElementoDopoInserimento():
    // Inserimento di una nuova riga
    inserisciNuovaRiga()

    // Esegui la ricerca dell'elemento nella tabella
    elementoRicerca = "elementoDaCercare"
    tabella = trovaTabellaConId("idTabella")
    righe = estraiRigheDallaTabella(tabella)

    Per ogni riga in righe:
        celle = trovaCelleInRiga(riga)
        Per ogni cella in celle:
            Se cella.contenuto == elementoRicerca:
                Stampa "Elemento trovato!"
                return vero
    
    Stampa "Elemento non trovato."
    return falso

_______________________________________________________________________________________________________________
Funzione ricercaElemento():
    Prova a trovare l'elemento utilizzando il selettore fornito.
    Se l'elemento viene trovato:
        Restituisci l'elemento.
    Altrimenti:
        Logga un messaggio di avviso indicando che l'elemento non è stato trovato.
        Restituisci null.
_______________________________________________________________________________________________________________

PUBLIC CLASS ALERTSTEST

### alertButtonTest

- Scopo: Verifica il comportamento del pulsante alert.
- Azione: Clicca sul pulsante alert.
- Verifica: Verifica che il testo dell'alert sia corretto.

### timerAlertButtonTest

- Scopo: Verifica il comportamento del pulsante timer alert.
- Azione: Clicca sul pulsante timer alert.
- Verifica: Verifica la presenza dell'alert e il suo testo dopo 5 secondi.

### confirmButtonTest

- Scopo: Verifica il comportamento del pulsante di conferma.
- Azione: Clicca sul pulsante di conferma.
- Verifica: Verifica che il testo dell'alert sia corretto e che l'azione di conferma sia stata eseguita correttamente.

### promtButtonTest

- Scopo: Verifica il comportamento del pulsante promt.
- Azione: Clicca sul pulsante promt e inserisce un testo nell'alert.
- Verifica: Verifica che il testo inserito nell'alert sia stato inserito correttamente.

__________________________________________________________________________________________________________________________

PUBLIC CLASS WIDGETSTEST

Metodo datePicker():
Questo metodo si occupa di testare il componente di selezione della data sulla pagina web.
Inizialmente, apre la pagina web relativa al date picker. Successivamente, individua l'elemento
corrispondente al campo data sulla pagina. Dopodiché, inserisce una nuova data e verifica che
la data inserita corrisponda a quella visualizzata nella UI. Infine, esegue un'asserzione per
confrontare la data inserita con quella visualizzata per verificare che il processo sia
avvenuto correttamente.

Metodo testSlider():
Questo metodo è responsabile del test del funzionamento di uno slider presente sulla pagina web.
 Dopo l'apertura della pagina web dello slider, individua l'elemento relativo allo slider.
  Successivamente, sposta lo slider al 20% della sua lunghezza e registra questa azione.
   L'obiettivo principale è verificare che lo slider sia funzionante e che la sua posizione possa 
   essere modificata correttamente attraverso l'interfaccia.

Metodo testProgressBar():
Questo metodo si occupa del test di una barra di progresso presente sulla pagina web.
 Inizialmente, apre la pagina web relativa alla barra di progresso e preme il pulsante per
  avviare il progresso. Successivamente, individua l'elemento relativo alla barra di progresso
   e attende il suo completamento. Una volta completata, trova e preme il pulsante di reset.
    L'obiettivo è verificare che la barra di progresso funzioni correttamente, completi il suo
     ciclo e che sia possibile resettarla al termine dell'operazione.